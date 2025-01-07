package com.enovira.base_component

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @date 2024/11/14 16:15
 * @author xiaozy
 * @description
 */
abstract class BaseActivity<VM: ViewModel, VB: ViewDataBinding>: AppCompatActivity() {

    protected lateinit var mViewModel: VM
    private var binding: VB? = null
    protected var mBinding: VB = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        mViewModel = createViewModel()
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
        initView()
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