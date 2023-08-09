package com.mod

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.frame.manager.AppFrontBack
import com.frame.manager.AppFrontBackListener
import com.frame.log.LogUtil
import com.frame.manager.ActivityManager
import com.frame.toast.TipsToast
import com.stater.dispatcher.TaskDispatcher
import com.mod.task.InitMmkvTask
import com.mod.task.InitAppManagerTask
import com.mod.task.InitRefreshLayoutTask
import com.mod.task.InitArouterTask
import com.mod.task.InitHelperTask

/**
 * @author Easin
 * @date   2022/2/9 23:19
 * @desc   应用类
 */
class ModApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()

        //注册APP前后台切换监听
        appFrontBackRegister()
        // App启动立即注册监听
        registerActivityLifecycle()
        TipsToast.init(this)

        //1.启动器：TaskDispatcher初始化
        TaskDispatcher.init(this)
        //2.创建dispatcher实例
        val dispatcher: TaskDispatcher = TaskDispatcher.createInstance()

        //3.添加任务并且启动任务
        dispatcher.addTask(InitHelperTask(this))
                .addTask(InitMmkvTask())
                .addTask(InitAppManagerTask())
                .addTask(InitRefreshLayoutTask())
                .addTask(InitArouterTask())
                .start()

        //4.等待，需要等待的方法执行完才可以往下执行
        dispatcher.await()
    }

    /**
     * 注册APP前后台切换监听
     */
    private fun appFrontBackRegister() {
        AppFrontBack.register(this, object : AppFrontBackListener {
            override fun onBack(activity: Activity?) {
                LogUtil.d("onBack")
            }

            override fun onFront(activity: Activity?) {
                LogUtil.d("onFront")
            }
        })
    }

    /**
     * 注册Activity生命周期监听
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.pop(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                ActivityManager.push(activity)
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }
}