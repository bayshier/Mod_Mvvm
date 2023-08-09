package com.main.ui.system.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.common.model.SystemList
import com.frame.adapter.BaseBindViewHolder
import com.frame.adapter.BaseRecyclerViewAdapter
import com.frame.ext.toJson
import com.sum.main.databinding.LayoutSystemItemBinding
import com.main.ui.system.ArticleTabActivity

/**
 * @author Easin
 * @date   2022/3/21 10:49
 * @desc   体系adapter
 */
class SystemAdapter : BaseRecyclerViewAdapter<SystemList, LayoutSystemItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutSystemItemBinding {
        return LayoutSystemItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutSystemItemBinding>,
        item: SystemList?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            tvTitle.text = item.name

            val layoutManager = FlexboxLayoutManager(recyclerViewItem.context)
            layoutManager.flexWrap = FlexWrap.WRAP // 按正常方向换行
            layoutManager.flexDirection = FlexDirection.ROW // 水平方向
            layoutManager.justifyContent = JustifyContent.FLEX_START // 左对齐
            recyclerViewItem.layoutManager = layoutManager

            val adapter = SystemSecondAdapter()
            recyclerViewItem.adapter = adapter
            adapter.setData(item.children)
//            recyclerViewItem.setOnTouchListener { v, event ->
//                root.onTouchEvent(event)
//            }
            adapter.onItemClickListener = { _: View, _: Int ->
                ArticleTabActivity.startIntent(root.context, item?.toJson(true))
            }
        }
    }

}