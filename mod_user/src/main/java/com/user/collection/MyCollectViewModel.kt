package com.user.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.common.model.ArticleInfo
import com.frame.toast.TipsToast
import com.network.callback.IApiErrorCallback
import com.network.flow.requestFlow
import com.network.manager.ApiManager
import com.network.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * @author Easin
 * @date   2022/3/24 18:29
 * @desc   我的收藏
 */
class MyCollectViewModel : BaseViewModel() {
    var collectListLiveData = MutableLiveData<MutableList<ArticleInfo>?>()

    /**
     * 我的收藏列表
     * @param page  页码
     */
    fun getMyCollectList(page: Int) {
//        launchUIWithResult(errorCall = object : IApiErrorCallback {
//            override fun onError(code: Int?, error: String?) {
//                TipsToast.showTips(error)
//                collectListLiveData.value = null
//            }
//        }, responseBlock = {
//            ApiManager.api.getCollectList(page)
//        }) {
//            collectListLiveData.value = it?.datas
//        }

        viewModelScope.launch {
            val data = requestFlow(requestCall = {
                ApiManager.api.getCollectList(page)
            }, errorBlock = { code, error ->
                TipsToast.showTips(error)
                collectListLiveData.value = null
            })
            collectListLiveData.value = data?.datas
        }
    }

    /**
     * 收藏站内文章
     * @param id  文章id
     * @param originId 收藏之前的那篇文章本身的id
     */
    fun collectArticle(id: Int, originId: Int, showLoading: (Boolean) -> Unit): LiveData<Boolean?> {
//        launchUIWithResult(responseBlock = {
//            ApiManager.api.cancelMyCollect(id, originId)
//        }, errorCall = object : IApiErrorCallback {
//            override fun onError(code: Int?, error: String?) {
//                super.onError(code, error)
//                collectLiveData.value = null
//            }
//
//            override fun onLoginFail(code: Int?, error: String?) {
//                super.onLoginFail(code, error)
//                collectLiveData.value = null
//                LoginServiceProvider.login(context)
//            }
//        }) {
//            collectLiveData.value = true
//        }
//        return collectLiveData
        val collectLiveData: MutableLiveData<Boolean?> = MutableLiveData()

        launchFlow(errorCall = object : IApiErrorCallback {
            override fun onError(code: Int?, error: String?) {
                super.onError(code, error)
                collectLiveData.value = null
            }

            override fun onLoginFail(code: Int?, error: String?) {
                super.onLoginFail(code, error)
                collectLiveData.value = false
            }
        }, requestCall = {
            ApiManager.api.cancelMyCollect(id, originId)
        }, showLoading = showLoading) {
            collectLiveData.value = true
        }
        return collectLiveData
    }
}