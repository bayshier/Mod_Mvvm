package com.network.callback

import com.frame.toast.TipsToast

/**
 * @author Easin
 * @date   2022/3/1 12:05
 * @desc   接口请求错误回调
 */
interface IApiErrorCallback {
    /**
     * 错误回调处理
     */
    fun onError(code: Int?, error: String?) {
        TipsToast.showTips(error)
    }

    /**
     * 登录失效处理
     */
    fun onLoginFail(code: Int?, error: String?) {
        TipsToast.showTips(error)
    }
}