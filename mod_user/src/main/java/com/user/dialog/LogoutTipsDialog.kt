package com.user.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.frame.base.BaseDialog
import com.frame.base.BaseDialogFragment
import com.frame.ext.onClick
import com.frame.manager.AppManager
import com.sum.user.databinding.DialogLogoutBinding

/**
 * @Author Easin
 * @Date   2022/4/14 11:20
 * @Desc   申请售后过期提示dialog
 */
class LogoutTipsDialog {
    class Builder(
        activity: FragmentActivity,
        private var mButtonClickListener: (() -> Unit)? = null
    ) : BaseDialogFragment.Builder<Builder>(activity) {

        private val mBinding: DialogLogoutBinding =
            DialogLogoutBinding.inflate(LayoutInflater.from(context))

        init {
            initView()
        }

        private fun initView() {
            setContentView(mBinding.root)
            setWidth((AppManager.getScreenWidthPx() * 0.8).toInt())
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            setAnimStyle(BaseDialog.AnimStyle.TOAST)
            setCanceledOnTouchOutside(true)

            mBinding.tvApplyReturn.onClick {
                mButtonClickListener?.invoke()
                dismiss()
            }
            mBinding.tvCancel.onClick {
                dismiss()
            }
        }
    }
}