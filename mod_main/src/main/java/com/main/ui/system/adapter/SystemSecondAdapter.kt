package com.main.ui.system.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.common.model.SystemSecondList
import com.frame.adapter.BaseBindViewHolder
import com.frame.adapter.BaseRecyclerViewAdapter
import com.sum.main.databinding.LayoutSystemSecondItemBinding

/**
 * @author Easin
 * @date   2022/3/21 8:49
 * @desc   体系adapter
 */
class SystemSecondAdapter : BaseRecyclerViewAdapter<SystemSecondList, LayoutSystemSecondItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutSystemSecondItemBinding {
        return LayoutSystemSecondItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutSystemSecondItemBinding>,
        item: SystemSecondList?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            tvName.text = item.name
        }
    }

}