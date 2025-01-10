package com.enovira.base_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/**
 * @date 2024/11/14 16:15
 * @author xiaozy
 * @description Activity基础框架
 */
abstract class BaseActivity<VM: ViewModel, VB: ViewDataBinding>: AppCompatActivity() {

    /** viewModel：存放布局数据及进行数据处理 */
    protected lateinit var mViewModel: VM
    /** dataViewBinding：操作布局 */
    protected val mBinding: VB get() = binding!!
    private var binding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
        binding = getViewDataBindingClass()
        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    /**
     * 初始化UI界面，生命周期:onCreate
     * 若要实现mvvm，需将dataViewBinding和viewModel进行双向绑定
     * 举例: 在布局中使用layout作为根布局，在layout-data-variable中引入ViewModel的类型
     * 最后进行绑定mBinding.viewModel = mViewModel
     */
    abstract fun initView()

    /**
     * 依据传入的ViewModel对象类型创建实例
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVMClass()]
    }

    @Suppress("UNCHECKED_CAST")
    private fun <VM> getVMClass(): VM {
        return (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    /**
     * 依据传入的ViewDataBinding对象类型创建实例
     */
    @Suppress("UNCHECKED_CAST")
    private fun getViewDataBindingClass(): VB {
        val clazz = getViewDataBindingImplClazz(this)
        val method = getInflateMethod(clazz)
        val binding = method.invoke(null, layoutInflater, window.decorView, false)
        return binding as VB
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewDataBindingImplClazz(obj: Any): Class<VB> {
        val paramClazz = (obj::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return paramClazz as Class<VB>
    }

    private fun getInflateMethod(clazz: Class<*>): Method {
        return clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    }
}