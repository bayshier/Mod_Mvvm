package com.main.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.constant.MAIN_SERVICE_HOME
import com.common.service.IMainService
import com.main.MainActivity
import com.main.ui.ArticleDetailActivity

/**
 * @author Easin
 * @date   2022/3/26 18:23
 * @desc   主页服务
 * 提供对IMainService接口的具体实现
 */
@Route(path = MAIN_SERVICE_HOME)
class MainService : IMainService {
    /**
     * 跳转主页
     * @param context
     * @param index tab位置
     */
    override fun toMain(context: Context, index: Int) {
        MainActivity.start(context, index)
    }

    /**
     * 跳转主页
     * @param context
     * @param url
     * @param title 标题
     */
    override fun toArticleDetail(context: Context, url: String, title: String) {
        ArticleDetailActivity.start(context, url, title)
    }

    override fun init(context: Context?) {
    }
}