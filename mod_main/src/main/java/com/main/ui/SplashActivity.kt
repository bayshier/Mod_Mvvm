package com.main.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.common.provider.MainServiceProvider
import com.frame.base.BaseDataBindActivity
import com.frame.ext.countDownCoroutines
import com.frame.ext.onClick
import com.frame.utils.StatusBarSettingHelper
import com.sum.main.R
import com.sum.main.databinding.ActivitySplashBinding

/**
 * @author Easin
 * @date   2022/3/29 14:25
 * @desc   启动页
 */
class SplashActivity : BaseDataBindActivity<ActivitySplashBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarSettingHelper.setStatusBarTranslucent(this)
        mBinding.tvSkip.onClick {
            MainServiceProvider.toMain(this)
        }
        //倒计时
        countDownCoroutines(2, lifecycleScope, onTick = {
            mBinding.tvSkip.text = getString(R.string.splash_time, it.plus(1).toString())
        }) {
            MainServiceProvider.toMain(this)
            finish()
        }
    }
}