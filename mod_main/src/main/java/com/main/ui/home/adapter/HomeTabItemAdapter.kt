package com.main.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.common.model.ProjectSubInfo
import com.frame.adapter.BaseBindViewHolder
import com.frame.adapter.BaseRecyclerViewAdapter
import com.frame.ext.onClick
import com.frame.utils.ViewUtils
import com.frame.utils.dpToPx
import com.glide.setUrl
import com.sum.main.databinding.LayoutHomeTabItemBinding
import com.main.ui.ImagePreviewActivity

/**
 * @author Easin
 * @date   2022/3/13 17:53
 * @desc   首页列表信息
 */
class HomeTabItemAdapter : BaseRecyclerViewAdapter<ProjectSubInfo, LayoutHomeTabItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutHomeTabItemBinding {
        return LayoutHomeTabItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutHomeTabItemBinding>,
        item: ProjectSubInfo?,
        position: Int
    ) {
        if (item == null) return
        holder.binding.apply {
            ivMainIcon.setUrl(item.envelopePic)
            tvTitle.text = item.title
            tvSubTitle.text = item.desc
            tvAuthorName.text = item.author
            tvTime.text = item.niceDate
            ivMainIcon.onClick {
                ImagePreviewActivity.start(it.context, item.envelopePic)
            }
            ViewUtils.setClipViewCornerRadius(holder.itemView, dpToPx(8))
        }
    }
}