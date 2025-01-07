package com.enovira.base_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @date 2024/11/14 16:15
 * @author xiaozy
 * @description
 */
abstract class BaseFragment<VM: ViewModel, VB: ViewDataBinding>: Fragment() {

    protected lateinit var mViewModel: VM
    private var binding: VB? = null
    protected var mBinding: VB = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView()
        mViewModel = createViewModel()
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    /**
     * 传入布局资源生成DataViewBinding
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVMClass().javaClass]
    }

    @Suppress("UNCHECKED_CAST")
    private fun getVMClass(): VM {
        return (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    /**
     * 初始化UI界面，生命周期:onCreate
     * 若要实现mvvm，需将dataViewBinding和viewModel进行绑定
     */
    abstract fun initView()
}