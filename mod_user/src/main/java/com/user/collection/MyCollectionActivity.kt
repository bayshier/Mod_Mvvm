package com.user.collection

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.common.R
import com.common.constant.USER_ACTIVITY_COLLECTION
import com.common.provider.LoginServiceProvider
import com.common.provider.MainServiceProvider
import com.frame.base.BaseMvvmActivity
import com.frame.decoration.NormalItemDecoration
import com.frame.ext.gone
import com.frame.ext.visible
import com.frame.toast.TipsToast
import com.frame.utils.dpToPx
import com.sum.user.databinding.ActivityMyCollectListBinding

/**
 * @author Easin
 * @date   2022/3/24 18:26
 * @desc   我的收藏
 */
@Route(path = USER_ACTIVITY_COLLECTION)
class MyCollectionActivity : BaseMvvmActivity<ActivityMyCollectListBinding, MyCollectViewModel>(),
    OnRefreshListener, OnLoadMoreListener {
    private var mPage = 0
    private lateinit var mAdapter: MyCollectListAdapter
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.refreshLayout.apply {
            setEnableRefresh(true)
            setEnableLoadMore(true)
            setOnRefreshListener(this@MyCollectionActivity)
            setOnLoadMoreListener(this@MyCollectionActivity)
            autoRefresh()
        }
        mAdapter = MyCollectListAdapter()
        val dp12 = dpToPx(12)
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MyCollectionActivity)
            adapter = mAdapter
            addItemDecoration(NormalItemDecoration().apply {
                setBounds(left = dp12, top = dp12, right = dp12, bottom = dp12)
                setLastBottom(true)
            })
        }
        mAdapter.onItemClickListener = { _: View, position: Int ->
            val item = mAdapter.getItem(position)
            if (item != null && !item.link.isNullOrEmpty()) {
                MainServiceProvider.toArticleDetail(
                    context = this,
                    url = item.link!!,
                    title = item.title ?: ""
                )
            }
        }

        mAdapter.onItemCancelCollectListener = { view: View, position: Int ->
            if (LoginServiceProvider.isLogin()) {
                cancelCollectArticle(position)
            } else {
                LoginServiceProvider.login(this)
            }
        }
    }

    /**
     * 取消收藏
     * @param position
     */
    private fun cancelCollectArticle(position: Int) {
        val item = mAdapter.getItem(position)
        item?.let {
            mViewModel.collectArticle(it.id, it.originId ?: -1) { showLoading ->
                if (showLoading) {
                    showLoading()
                } else {
                    dismissLoading()
                }
            }.observe(this) { result ->
                if (result == true) {
                    mAdapter.removeAt(position)
                    TipsToast.showTips(R.string.collect_cancel)
                } else if (result == false) {
                    LoginServiceProvider.login(this)
                }
            }
        }
    }

    override fun initData() {
        getMyCollectList()
        mViewModel.collectListLiveData.observe(this) {
            if (mPage == 0) {
                mAdapter.setData(it)
                if (it.isNullOrEmpty()) {
                    //空视图
                    mBinding.viewEmptyData.visible()
                } else {
                    mBinding.viewEmptyData.gone()
                }
                mBinding.refreshLayout.finishRefresh()
            } else {
                mAdapter.addAll(it)
                mBinding.refreshLayout.finishLoadMore()
            }
        }
    }

    private fun getMyCollectList() {
        mViewModel.getMyCollectList(mPage)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPage = 0
        getMyCollectList()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mPage++
        getMyCollectList()
    }

}