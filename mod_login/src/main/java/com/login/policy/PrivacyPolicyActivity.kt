package com.login.policy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.constant.Login_ACTIVITY_POLICY
import com.frame.base.BaseDataBindActivity
import com.sum.login.databinding.ActivityPrivacyPolicyBinding

/**
 * @author Easin
 * @date   2022/4/26 12:57
 * @desc   隐私协议
 */
@Route(path = Login_ACTIVITY_POLICY)
class PrivacyPolicyActivity : BaseDataBindActivity<ActivityPrivacyPolicyBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PrivacyPolicyActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}