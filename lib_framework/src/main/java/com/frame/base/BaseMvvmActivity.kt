package com.frame.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author Easin
 * @date   2022/2/27 12:18
 * @desc   DataBinding+ViewModel基类
 */
abstract class BaseMvvmActivity<DB : ViewBinding, VM : ViewModel> : BaseDataBindActivity<DB>() {
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()
        super.onCreate(savedInstanceState)
    }

    private fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this).get(argument[1] as Class<VM>)

        //兼容性优化
//        val superclass = javaClass.superclass//超类
//        if (superclass is ParameterizedType) {//参数泛型类型
//            val arguments = superclass.actualTypeArguments//泛型参数集合
//            for (argument in arguments) {
//                //是否为class并且ViewModel是其超类
//                if (argument is Class<*> && ViewModel::class.java.isAssignableFrom(argument)) {
//                    kotlin.runCatching {
//                        //通过反射构建ViewHolder实例
//                        mViewModel = ViewModelProvider(this).get(argument as Class<VM>)
//                    }.onFailure {
//                        it.printStackTrace()
//                    }
//                }
//            }
//        }
    }
}