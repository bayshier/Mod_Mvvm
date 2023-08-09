package com.main.ui.category.viewmodel

import androidx.lifecycle.MutableLiveData
import com.common.model.CategoryItem
import com.frame.toast.TipsToast
import com.network.callback.IApiErrorCallback
import com.network.manager.ApiManager
import com.network.viewmodel.BaseViewModel

/**
 * @author Easin
 * @date   2022/3/3 8:12
 * @desc   分类ViewModel
 */
class CategoryViewModel : BaseViewModel() {
    val categoryItemLiveData = MutableLiveData<MutableList<CategoryItem>?>()

    /**
     * 获取分类信息
     * 不依赖repository,错误回调实现IApiErrorCallback
     */
    fun getCategoryData() {
        launchUIWithResult(responseBlock = {
            ApiManager.api.getCategoryData()
        }, errorCall = object : IApiErrorCallback {
            override fun onError(code: Int?, error: String?) {
                super.onError(code, error)
                TipsToast.showTips(error)
                categoryItemLiveData.value = null
            }
        }) {
            categoryItemLiveData.value = it
        }
    }
}