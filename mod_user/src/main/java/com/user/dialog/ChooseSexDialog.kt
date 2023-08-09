package com.user.dialog

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.frame.base.BaseDialog
import com.frame.base.BaseDialogFragment
import com.frame.ext.onClick
import com.frame.utils.getStringFromResource
import com.sum.user.R
import com.sum.user.databinding.DialogSexChooseBinding

/**
 * @author Easin
 * @date   2022/4/25 14:35
 * @desc   性别选择弹框
 */
class ChooseSexDialog {
    class Builder(activity: FragmentActivity) : BaseDialogFragment.Builder<Builder>(activity) {
        private var mOnSexChooseCall: ((String) -> Unit)? = null

        private val mBinding: DialogSexChooseBinding = DialogSexChooseBinding.inflate(LayoutInflater.from(activity))

        init {
            initView()
        }

        private fun initView() {
            setContentView(mBinding.root)
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
            gravity = Gravity.BOTTOM

            mBinding.clSexBoy.onClick {
                mOnSexChooseCall?.invoke(getStringFromResource(R.string.user_sex_boy))
                dismiss()
            }
            mBinding.clSexGirl.onClick {
                mOnSexChooseCall?.invoke(getStringFromResource(R.string.user_sex_girl))
                dismiss()
            }
            mBinding.tvCancel.onClick {
                dismiss()
            }
        }

        fun setOnSexChooseCall(onSexChooseCall: ((String) -> Unit)): Builder {
            mOnSexChooseCall = onSexChooseCall
            return this
        }
    }
}