package com.example.adaptivelayouts.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.adaptivelayouts.listFragment.ListViewModel
import com.example.graphql.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)
        viewModel.sharedData.observe(viewLifecycleOwner) {
            _binding.apply {
                tvName.text = it?.name
                tvDetail.text = "${it?.name}- ${it.emoji}"


                clData.isVisible = true
                clNoData.isVisible = false

            }
        }

        return _binding.root
    }


}