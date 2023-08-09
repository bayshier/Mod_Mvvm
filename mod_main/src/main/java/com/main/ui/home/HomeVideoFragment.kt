package com.main.ui.home

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.common.constant.KEY_VIDEO_PLAY_LIST
import com.common.constant.VIDEO_ACTIVITY_PLAYER
import com.frame.decoration.StaggeredItemDecoration
import com.frame.base.BaseMvvmFragment
import com.frame.ext.gone
import com.frame.ext.visible
import com.frame.toast.TipsToast
import com.frame.utils.dpToPx
import com.sum.main.databinding.FragmentHomeVideoBinding
import com.main.ui.home.adapter.HomeVideoItemAdapter
import com.main.ui.home.viewmodel.HomeViewModel
import com.room.entity.VideoInfo
import com.tbruyelle.rxpermissions3.RxPermissions
import java.util.ArrayList

/**
 * @author Easin
 * @date   2022/3/5 20:11
 * @desc   首页视频列表
 */
class HomeVideoFragment : BaseMvvmFragment<FragmentHomeVideoBinding, HomeViewModel>() {
    lateinit var videoAdapter: HomeVideoItemAdapter
    override fun initView(view: View, savedInstanceState: Bundle?) {

        val spanCount = 2
        val manager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        videoAdapter = HomeVideoItemAdapter(requireContext())
        mBinding?.recyclerView?.apply {
            layoutManager = manager
            addItemDecoration(StaggeredItemDecoration(dpToPx(10)))
            adapter = videoAdapter
        }

        videoAdapter.onItemClickListener = { view: View, position: Int ->
            RxPermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).subscribe { granted ->
                if (granted) {
                    ARouter.getInstance().build(VIDEO_ACTIVITY_PLAYER)
                            .withParcelableArrayList(KEY_VIDEO_PLAY_LIST, videoAdapter.getData() as ArrayList<VideoInfo>)
                            .navigation()
                } else {
                    TipsToast.showTips(com.common.R.string.default_agree_permission)
                }
            }
        }
    }

    override fun initData() {
        showLoading()
        mViewModel.getVideoList(requireContext().assets).observe(this) {
            dismissLoading()
            if (it.isNullOrEmpty()) {
                mBinding?.viewEmptyData?.visible()
            } else {
                mBinding?.viewEmptyData?.gone()
                videoAdapter.setData(it)
            }
        }
    }
}