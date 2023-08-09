package com.frame.helper

import android.app.Application

/**
 * @author Easin
 * @date   2022/3/2 16:10
 * @desc   提供应用环境
 */
object AppHelper {
    private lateinit var app: Application
    private var isDebug = false

    fun init(application: Application, isDebug: Boolean) {
        app = application
        AppHelper.isDebug = isDebug
    }

    /**
     * 获取全局应用
     */
    fun getApplication() = app

    /**
     * 是否为debug环境
     */
    fun isDebug() = isDebug
}