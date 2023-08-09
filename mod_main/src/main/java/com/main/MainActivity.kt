package com.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.constant.KEY_INDEX
import com.common.constant.MAIN_ACTIVITY_HOME
import com.frame.base.BaseDataBindActivity
import com.frame.log.LogUtil
import com.frame.toast.TipsToast
import com.frame.utils.AppExit
import com.frame.utils.StatusBarSettingHelper
import com.sum.main.databinding.ActivityMainBinding
import com.main.navigator.SumFragmentNavigator
import com.sum.main.R

/**
 * @author Easin
 * @time   2022/3/3 8:41
 * @desc   主页
 */
@Route(path = MAIN_ACTIVITY_HOME)
class MainActivity : BaseDataBindActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    companion object {
        fun start(context: Context, index: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_INDEX, index)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        val navView = mBinding.navView
        //1.寻找出路由控制器对象，它是路由跳转的唯一入口，找到宿主NavHostFragment
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        //2.自定义FragmentNavigator，mobile_navigation.xml文件中的fragment标识改为SumFragmentNavigator的sumFragment
        val fragmentNavigator = SumFragmentNavigator(this, navHostFragment.childFragmentManager, navHostFragment.id)
        //3.注册到Navigator里面，这样才找得到
        navController.navigatorProvider.addNavigator(fragmentNavigator)
        //4.设置Graph，需要将activity_main.xml文件中的app:navGraph="@navigation/mobile_navigation"移除
        navController.setGraph(R.navigation.mobile_navigation)
        //5.将NavController和BottomNavigationView绑定，形成联动效果
        navView.setupWithNavController(navController)
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        StatusBarSettingHelper.statusBarLightMode(this@MainActivity, true)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val index = intent.getIntExtra(KEY_INDEX, 0)
//            navController.navigate(R.id.navi_home)
            LogUtil.e("onNewIntent:index:$index", tag = "smy")
        }
    }

    //延迟初始化执行的任务 此处的时机应该是在页面渲染首帧后执行 这里暂时放在onWindowFocusChanged()
    //利用 IDLEHandler 特性，主线程空闲时机执行
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //延迟执行启动器
//        DelayInitDispatcher().addTask(InitTaskA())
//                .addTask(InitTaskB())
//                .start()
    }

    override fun onBackPressed() {
//        if (mCurFragment?.onBackPressed() == true) {
//            return
//        }
        AppExit.onBackPressed(
            this,
            { TipsToast.showTips(getString(R.string.app_exit_one_more_press)) }
        )
    }


}