package com.main.ui.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.common.constant.KEY_LIST
import com.common.model.CategorySecondItem
import com.common.provider.MainServiceProvider
import com.frame.base.BaseMvvmFragment
import com.frame.ext.dividerGridSpace
import com.frame.ext.gone
import com.frame.ext.toBeanOrNull
import com.frame.ext.visible
import com.sum.main.databinding.FragmentCategorySecondBinding
import com.main.ui.category.adapter.CategorySecondItemAdapter
import com.main.ui.category.viewmodel.CategoryViewModel

/**
 * @author Easin
 * @date   2022/3/19 22:31
 * @desc   分类item
 */
class CategorySecondFragment : BaseMvvmFragment<FragmentCategorySecondBinding, CategoryViewModel>() {
    private lateinit var mAdapter: CategorySecondItemAdapter

    companion object {
        fun newInstance(jsonStr: String): CategorySecondFragment {
            val fragment = CategorySecondFragment()
            val bundle = Bundle()
            bundle.putString(KEY_LIST, jsonStr)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mAdapter = CategorySecondItemAdapter()
        mBinding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
            dividerGridSpace(2, 8.0f, true)
        }
        mAdapter.onItemClickListener = { _: View, position: Int ->
            val item = mAdapter.getItem(position)
            if (item != null && !item.link.isNullOrEmpty()) {
                MainServiceProvider.toArticleDetail(
                    context = requireContext(),
                    url = item.link!!,
                    title = item.title ?: ""
                )
            }
        }
    }

    override fun initData() {
        val listJson = arguments?.getString(KEY_LIST, "")
        val list = listJson?.toBeanOrNull<MutableList<CategorySecondItem>>()
        mAdapter.setData(list)
        if (list.isNullOrEmpty()) {
            mBinding?.viewEmptyData?.visible()
        } else {
            mBinding?.viewEmptyData?.gone()
        }
    }
}