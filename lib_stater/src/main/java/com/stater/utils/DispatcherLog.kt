package com.stater.utils

import com.frame.helper.AppHelper
import com.frame.log.LogUtil

object DispatcherLog {
    var isDebug = AppHelper.isDebug()

    @JvmStatic
    fun i(msg: String?) {
        if (msg == null) {
            return
        }
        LogUtil.i(msg, tag = "StartTask")
    }
}