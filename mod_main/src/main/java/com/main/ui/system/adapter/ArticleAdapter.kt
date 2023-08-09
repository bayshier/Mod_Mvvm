package com.main.ui.system.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.common.model.ArticleInfo
import com.frame.adapter.BaseBindViewHolder
import com.frame.adapter.BaseRecyclerViewAdapter
import com.frame.ext.onClick
import com.frame.utils.getStringFromResource
import com.sum.main.databinding.LayoutArticleItemBinding
import java.text.SimpleDateFormat
import java.util.Locale
import com.common.R
import com.frame.ext.Bold
import com.frame.ext.gone
import com.frame.ext.visible

/**
 * @author Easin
 * @date   2022/3/21 22:50
 * @desc   文章列表Item
 */
class ArticleAdapter : BaseRecyclerViewAdapter<ArticleInfo, LayoutArticleItemBinding>() {
    var onItemCollectListener: ((view: View, position: Int) -> Unit?)? = null
    private val format = SimpleDateFormat("yyyy-MM-dd:HH:mm", Locale.CHINA)

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutArticleItemBinding {
        return LayoutArticleItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun onBindDefViewHolder(
        holder: BaseBindViewHolder<LayoutArticleItemBinding>,
        item: ArticleInfo?,
        position: Int
    ) {
        if (item == null) return
        val name = if (item.author.isNullOrEmpty()) item.shareUser else item.author
        val authorName = String.format(getStringFromResource(R.string.author_name), name)
        holder.binding.apply {
            tvTitle.text = item.title
            tvTitle.Bold()
            tvDesc.text = item.desc
            if (item.desc.isNullOrEmpty()) {
                tvDesc.gone()
            } else {
                tvDesc.visible()
            }
            tvTime.text = format.format(item.publishTime)
            tvFrom.text = "${item.superChapterName}/${item.chapterName}"
            tvAuthorName.text = authorName
            tvZan.text = "${item.zan ?: "0"}"
            ivCollect.onClick {
                onItemCollectListener?.invoke(it, position)
            }
            ivCollect.isSelected = item.collect ?: false
        }
    }


}