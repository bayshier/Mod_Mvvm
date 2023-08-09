package com.user.setting

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.constant.USER_ACTIVITY_SETTING
import com.common.dialog.MessageDialog
import com.common.provider.LoginServiceProvider
import com.common.provider.SearchServiceProvider
import com.common.provider.UserServiceProvider
import com.frame.base.BaseDataBindActivity
import com.frame.ext.gone
import com.frame.ext.onClick
import com.frame.ext.visible
import com.frame.toast.TipsToast
import com.frame.manager.AppManager
import com.frame.utils.ViewUtils
import com.frame.utils.dpToPx
import com.frame.utils.getColorFromResource
import com.frame.utils.getStringFromResource
import com.network.manager.CookiesManager
import com.sum.user.R
import com.user.about.AboutUsActivity
import com.sum.user.databinding.ActivitySettingBinding
import com.user.dialog.LogoutTipsDialog
import com.user.info.UserInfoActivity
import com.common.manager.FileManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Easin
 * @date   2022/3/23 12:43
 * @desc   设置
 */
@Route(path = USER_ACTIVITY_SETTING)
class SettingActivity : BaseDataBindActivity<ActivitySettingBinding>() {
    var allCacheDir = arrayOfNulls<String>(2)

    override fun initView(savedInstanceState: Bundle?) {
        ViewUtils.setClipViewCornerRadius(mBinding.tvLogout, dpToPx(8))
        mBinding.tvCurrentVersion.text = String.format(
            getString(
                R.string.setting_current_version
            ), AppManager.getAppVersionName(this)
        )
        if (UserServiceProvider.isLogin()) {
            mBinding.tvLogout.visible()
        } else {
            mBinding.tvLogout.gone()
        }
        val rootDir = FileManager.getAppRootDir()
        val imageDir = FileManager.getImageDirectory(this)
        allCacheDir = arrayOf(rootDir, imageDir.absolutePath)
        lifecycleScope.launch(Dispatchers.IO) {
            updateCacheSize()
        }
        initListener()
    }

    /**
     * 更新缓存大小
     */
    private fun updateCacheSize() {
        val size = FileManager.getTotalCacheSize(this, *allCacheDir)
        mBinding.tvCache.text = size
    }

    private fun initListener() {
        mBinding.clUserInfo.onClick {
            UserInfoActivity.start(this)
        }
        mBinding.clAccountSafe.onClick {
            TipsToast.showWarningTips(com.common.R.string.default_developing)
        }
        mBinding.clCurrentVersion.onClick {
            TipsToast.showWarningTips(R.string.setting_newest_version)
        }
        mBinding.clPrivacyPolicy.onClick {
            LoginServiceProvider.readPolicy(this)
        }
        mBinding.clClearCache.onClick {
            showClearCacheDialog()
        }
        mBinding.clAboutUs.onClick {
            AboutUsActivity.start(this)
        }
        mBinding.tvLogout.onClick {
            LogoutTipsDialog.Builder(this, mButtonClickListener = {
                showLoading()
                LoginServiceProvider.logout(context = this, lifecycleOwner = this) {
                    CookiesManager.clearCookies()
                    UserServiceProvider.clearUserInfo()
                    SearchServiceProvider.clearSearchHistoryCache()
                    dismissLoading()
                }
            }).show()
        }
    }

    /**
     * 清理缓存弹框
     */
    private fun showClearCacheDialog() {
        MessageDialog.Builder(this).setTitle(getStringFromResource(com.common.R.string.dialog_tips_title))
                .setMessage(getStringFromResource(R.string.setting_clear_cache_tips))
                .setConfirm(getStringFromResource(com.common.R.string.default_confirm))
                .setConfirmTxtColor(getColorFromResource(com.common.R.color.color_0165b8))
                .setCancel(getString(com.common.R.string.default_cancel))
                .setonCancelListener {
                    it?.dismiss()
                }
                .setonConfirmListener {
                    clearCache()
                    it?.dismiss()
                }.create().show()
    }

    /**
     * 清理缓存
     */
    private fun clearCache() {
        showLoading("正在清理...")
        lifecycleScope.launch {
            allCacheDir.forEach { filesDir ->
                filesDir?.let { FileManager.delAllFile(it) }
            }
            delay(500)
            dismissLoading()
            updateCacheSize()
        }
    }
}