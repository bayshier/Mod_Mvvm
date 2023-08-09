package com.main.ui.system

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.frame.base.BaseMvvmFragment
import com.frame.decoration.NormalItemDecoration
import com.frame.ext.toJson
import com.frame.ext.visible
import com.frame.utils.dpToPx
import com.sum.main.databinding.FragmentSystemBinding
import com.main.ui.system.adapter.SystemAdapter
import com.main.ui.system.viewmodel.SystemViewModel

/**
 * @author Easin
 * @date   2022/3/3 8:18
 * @desc   体系
 */
class SystemFragment : BaseMvvmFragment<FragmentSystemBinding, SystemViewModel>() {
    private lateinit var mAdapter: SystemAdapter

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mAdapter = SystemAdapter()
        mBinding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(NormalItemDecoration().apply {
                setBounds(left = dpToPx(8), top = dpToPx(10), right = dpToPx(8), bottom = dpToPx(10))
                setLastBottom(true)
            })
        }
        mAdapter.onItemClickListener = { view: View, position: Int ->
            val item = mAdapter.getItem(position)
            ArticleTabActivity.startIntent(requireContext(), item?.toJson(true))
        }
    }

    override fun initData() {
        showLoading()
        mViewModel.getSystemList().observe(this) {
            mAdapter.setData(it)
            dismissLoading()
        }
        mViewModel.errorListLiveData.observe(this) {
            //空数据视图
            mBinding?.viewEmptyData?.visible()
        }
    }
}