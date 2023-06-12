package com.example.adaptivelayouts.listFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adaptivelayouts.api.Resource
import com.example.graphql.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.adaptivelayouts.listFragment.postAdapter.ListAdapter
import com.example.adaptivelayouts.model.SingleCountry
import com.example.adaptivelayouts.utils.SportsListOnBackPressedCallback

@AndroidEntryPoint
class ListFragment : Fragment(), ListAdapter.CallBack {
    private val viewModel: ListViewModel by activityViewModels()
    private val listAdapter = ListAdapter(emptyList(), this)
    private val listData = arrayListOf<SingleCountry>()

    private lateinit var _binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)

        getPostList()
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(_binding.slidingPaneLayout)
        )
        _binding.rvTemp.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
        _binding.etFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! > 0) {
                    filter(p0.toString())
                } else {
                    setDataToAdaptor(listData)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        return _binding.root
    }


    fun getPostList() {
        viewModel.countriesList.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {

                }

                Resource.Status.SUCCESS -> {

                    it.data?.let {
                        listData.addAll(it.singleCountry)
                        setDataToAdaptor(listData)
                    }

                }

                Resource.Status.ERROR -> {

                }
            }
        }
    }

    private fun setDataToAdaptor(list: List<SingleCountry>) {
        listAdapter.setData(list)
        listAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(item: SingleCountry) {
        viewModel.updateData(item)
        _binding.slidingPaneLayout.openPane()
    }

    fun filter(query: String) {
        var filteredList =
            listData.filter { it.name?.contains(query, ignoreCase = true)!! }
        setDataToAdaptor(filteredList)
    }

}

