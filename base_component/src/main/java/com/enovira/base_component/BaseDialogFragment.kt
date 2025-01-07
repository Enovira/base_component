package com.enovira.base_component

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.enovira.base_component.dialog.GeneralBaseDialogBuilder

/**
 * @date 2024/12/5 16:36
 * @author xiaozy
 * @description
 */
abstract class BaseDialogFragment(private val builder: GeneralBaseDialogBuilder) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return initView(inflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        //是否可被(物理/手势/虚拟)回退功能取消
        dialog.setCancelable(builder.cancelable)
        //是否点击dialog外部取消
        dialog.setCanceledOnTouchOutside(builder.cancelOnTouchOutside)
        dialog.window?.let { window ->
            window.decorView.let { decorView ->
                //设置window背景透明
                decorView.background = ColorDrawable(Color.TRANSPARENT)
                //不显示导航栏及状态栏
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }
            //显示dialog时不获取焦点(防止弹出导航栏及状态栏)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
            //dialog显示之后清除不获取焦点标记，使dialog可正常交互
            dialog.setOnShowListener {
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            }
        }
        setLayout(dialog)
        return dialog
    }

    //初始化dialog布局内容
    abstract fun initView(inflater: LayoutInflater): View

    open fun setLayout(dialogInstance: Dialog) {
        resources.displayMetrics.let {
            dialogInstance.window?.setLayout(builder.width, builder.height)
        }
    }
}