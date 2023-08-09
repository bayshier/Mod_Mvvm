package com.main.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.frame.adapter.BaseBindViewHolder
import com.frame.adapter.BaseRecyclerViewAdapter
import com.glide.setUrl
import com.sum.main.databinding.LayoutHomeVideoItemBinding
import com.room.entity.VideoInfo

/**
 * @author Easin
 * @date   2022/3/8 23:10
 * @desc   首页视频列表适配器
 */
class HomeVideoItemAdapter(val context: Context) : BaseRecyclerViewAdapter<VideoInfo, LayoutHomeVideoItemBinding>() {

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutHomeVideoItemBinding {
        return LayoutHomeVideoItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutHomeVideoItemBinding>,
        item: VideoInfo?,
        position: Int
    ) {
        item?.apply {
            holder.binding.tvTitle.text = title
            holder.binding.ivVideoCover.setUrl(imageUrl)
            holder.binding.tvCollectCount.text = collectionCount
        }
    }
}