package com.demo.viewmodel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sum.demo.databinding.FragmentViewmodelVideoBinding
import com.frame.base.BaseDataBindFragment
import com.frame.ext.gone

/**
 * @author Easin
 * @date   2022/6/17 08:28
 * @desc
 */
class UserFragment : BaseDataBindFragment<FragmentViewmodelVideoBinding>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.tvTitle?.text = "UserFragment"
        mBinding?.tvSaveInfo?.gone()
        mBinding?.rlRoot?.setBackgroundResource(com.common.R.color.colorAccent)
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        // 获取ViewModel，注意传入的是宿主Activity
        viewModel.shareLiveData.observe(this) {
            mBinding?.tvTitle?.text = "UserFragment \n\n\n $it"
        }
    }
}