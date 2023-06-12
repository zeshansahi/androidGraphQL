package com.example.adaptivelayouts.listFragment.postAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adaptivelayouts.base.BaseRecyclerViewAdapter
import com.example.graphql.databinding.ItemTempBinding
import com.example.adaptivelayouts.model.SingleCountry

class ListAdapter(detailList: List<SingleCountry>, val callBack: CallBack) :
    BaseRecyclerViewAdapter<SingleCountry, ListAdapter.TempItemViewHolder>(detailList.toMutableList()) {


    class TempItemViewHolder(val binding: ItemTempBinding, val callBack: CallBack) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listItem: SingleCountry) {
            binding.items = "${listItem.emoji} ${listItem.name}"
            binding.cvItem.setOnClickListener { callBack.onItemClick(listItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempItemViewHolder {
        val binding: ItemTempBinding = ItemTempBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TempItemViewHolder(binding, callBack);
    }


    override fun onBindViewHolder(holder: TempItemViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun setData(list: List<SingleCountry>) {
        mList.clear()
        mList.addAll(list)
    }

    interface CallBack {
        fun onItemClick(item:SingleCountry)
    }
}