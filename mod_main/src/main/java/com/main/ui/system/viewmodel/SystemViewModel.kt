package com.main.ui.system.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.common.model.SystemList
import com.frame.toast.TipsToast
import com.network.callback.IApiErrorCallback
import com.network.manager.ApiManager
import com.network.viewmodel.BaseViewModel

/**
 * @author Easin
 * @date   2022/3/3 8:19
 * @desc   体系ViewModel
 */
class SystemViewModel : BaseViewModel() {

    //错误无数据回调
    val errorListLiveData: MutableLiveData<String> = MutableLiveData()

    /**
     * 获取体系列表
     */
    fun getSystemList(): LiveData<MutableList<SystemList>> {
        return liveData {
            val data = safeApiCallWithResult(errorCall = object : IApiErrorCallback {
                override fun onError(code: Int?, error: String?) {
                    TipsToast.showTips(error)
                    errorListLiveData.value = error
                }
            }) {
                ApiManager.api.getSystemList()
            }
            data?.let {
                emit(it)
            } ?: kotlin.run {
                errorListLiveData.value = ""
            }
        }
    }

}