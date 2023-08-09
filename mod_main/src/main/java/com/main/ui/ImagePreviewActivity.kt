package com.main.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.common.constant.KEY_URL
import com.common.manager.FileManager
import com.frame.base.BaseDataBindActivity
import com.frame.ext.onClick
import com.frame.toast.TipsToast
import com.frame.utils.getStringFromResource
import com.glide.setUrl
import com.sum.main.databinding.ActivityImagePreviewBinding
import com.main.utils.ImageUtil
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.coroutines.launch
import java.io.File

/**
 * @author Easin
 * @date   2022/4/25 22:58
 * @desc   图片预览view
 */
class ImagePreviewActivity : BaseDataBindActivity<ActivityImagePreviewBinding>() {

    companion object {
        fun start(context: Context, url: String?) {
            val intent = Intent(context, ImagePreviewActivity::class.java)
            url?.let {
                intent.putExtra(KEY_URL, it)
            }
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        val url = intent?.getStringExtra(KEY_URL)
        mBinding.ivPreview.setUrl(url)
        mBinding.titleBar.getRightTextView().onClick {
            //获取权限
            requestPermission()
        }
    }

    /**
     * 获取权限
     */
    private fun requestPermission() {
        RxPermissions(this).request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe { granted ->
            if (granted) {
                saveImage()
            } else {
                TipsToast.showTips(com.common.R.string.default_agree_permission)
            }
        }
    }

    /**
     * 保存图片
     */
    private fun saveImage() {
        lifecycleScope.launch {
            val bitmap = ImageUtil.viewToBitmap(mBinding.ivPreview)
            val path = FileManager.getImageDirectory(this@ImagePreviewActivity)
            val file = File(path, "${System.currentTimeMillis()}${FileManager.JPG_SUFFIX}")
            val success = ImageUtil.save(bitmap, file, Bitmap.CompressFormat.JPEG, false)
            val res =
                if (success) com.common.R.string.default_save_success else com.common.R.string.default_save_fail
            TipsToast.showTips(getStringFromResource(res))
        }
    }
}