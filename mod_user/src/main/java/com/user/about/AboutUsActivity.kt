package com.user.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.common.constant.AUTHOR_GITHUB_LINK
import com.common.provider.MainServiceProvider
import com.frame.base.BaseDataBindActivity
import com.frame.ext.onClick
import com.sum.user.databinding.ActivityAboutUsBinding

/**
 * @author Easin
 * @date   2022/3/24 08:21
 * @desc   关于我们
 */
class AboutUsActivity : BaseDataBindActivity<ActivityAboutUsBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AboutUsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.tvGithub.text = AUTHOR_GITHUB_LINK

        mBinding.clGithub.onClick {
            MainServiceProvider.toArticleDetail(
                context = this,
                url = AUTHOR_GITHUB_LINK,
                title = mBinding.tvGithubTitle.text.toString()
            )
        }
    }
}