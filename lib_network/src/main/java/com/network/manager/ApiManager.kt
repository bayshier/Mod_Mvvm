package com.network.manager

import com.network.api.ApiInterface

/**
 * @author Easin
 * @date   2022/2/27 21:14
 * @desc   API管理器
 */
object ApiManager {
    val api by lazy { HttpManager.create(ApiInterface::class.java) }
}