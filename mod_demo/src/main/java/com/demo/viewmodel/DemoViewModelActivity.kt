package com.demo.viewmodel

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.constant.DEMO_ACTIVITY_VIEWMODEL
import com.sum.demo.databinding.ActivityViewmodelBinding
import com.demo.livedata.LiveDataBus
import com.frame.base.BaseDataBindActivity
import com.frame.ext.onClick
import com.frame.log.LogUtil

/**
 * @author Easin
 * @date   2022/6/14 06:39
 * @desc
 */
@Route(path = DEMO_ACTIVITY_VIEWMODEL)
class DemoViewModelActivity : BaseDataBindActivity<ActivityViewmodelBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        LogUtil.d("onCreate()")
        //val v :HomeViewModel by viewModels()
        //通过ViewModelProvider来获取ViewModel对象
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //注册监听，监听数据的回调
        viewModel.userLiveData.observe(this, Observer {
            //接收到数据
            dismissLoading()
            mBinding.tvUserInfo.text = it
            // 发送事件总线
            LiveDataBus.with<String?>("eventName").setStickData(it)
        })

        mBinding.tvRequestUserInfo.onClick {
            // 请求数据
            showLoading()
            viewModel.getUserInfo()
        }

        // 获取 SavedState 保存的数据
        val saveViewModel = ViewModelProvider(this).get(MainSaveViewModel::class.java)
        saveViewModel.savedStateLiveData.observe(this) {
            mBinding.tvUserInfo.text = it
        }
        mBinding.tvRequestSavedStateInfo.onClick {
            saveViewModel.getUserInfo()
        }
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("onDestroy()")
    }
}
