package com.user.dialog

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.frame.base.BaseDialog
import com.frame.base.BaseDialogFragment
import com.frame.ext.onClick
import com.sum.user.databinding.DialogPhotoChooseBinding

/**
 * @author Easin
 * @date   2022/4/24 14:35
 * @desc   拍照、相册选择弹框
 */
class ChoosePhotoDialog {
    class Builder(activity: FragmentActivity) : BaseDialogFragment.Builder<Builder>(activity) {
        private var mOnTakePicturesCall: (() -> Unit)? = null
        private var mOnPhotoAlbumCall: (() -> Unit)? = null

        private val mBinding: DialogPhotoChooseBinding = DialogPhotoChooseBinding.inflate(LayoutInflater.from(activity))

        init {

            initView()
        }

        private fun initView() {
            setContentView(mBinding.root)
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
            gravity = Gravity.BOTTOM

            mBinding.clPhotoAlbum.onClick {
                mOnPhotoAlbumCall?.invoke()
                dismiss()
            }
            mBinding.clTakePictures.onClick {
                mOnTakePicturesCall?.invoke()
                dismiss()
            }
            mBinding.tvCancel.onClick {
                dismiss()
            }
        }

        fun setTakePicturesCall(onTakePicturesCall: (() -> Unit)): Builder {
            mOnTakePicturesCall = onTakePicturesCall
            return this
        }

        fun setPhotoAlbumCall(onPhotoAlbumCall: (() -> Unit)): Builder {
            mOnPhotoAlbumCall = onPhotoAlbumCall
            return this
        }
    }
}