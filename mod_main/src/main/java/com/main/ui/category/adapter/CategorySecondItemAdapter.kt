package com.main.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.common.model.CategorySecondItem
import com.frame.adapter.BaseBindViewHolder
import com.frame.adapter.BaseRecyclerViewAdapter
import com.frame.utils.ViewUtils
import com.frame.utils.dpToPx
import com.sum.main.databinding.LayoutCategorySecondItemBinding

/**
 * @author Easin
 * @date   2022/3/19 22:45
 * @desc   分类二级Adapter
 */
class CategorySecondItemAdapter : BaseRecyclerViewAdapter<CategorySecondItem, LayoutCategorySecondItemBinding>() {
    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutCategorySecondItemBinding {
        return LayoutCategorySecondItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutCategorySecondItemBinding>,
        item: CategorySecondItem?,
        position: Int
    ) {
        holder.binding?.apply {
            tvTitle.text = item?.title
            ViewUtils.setClipViewCornerRadius(tvTitle, dpToPx(8))
        }

    }
}