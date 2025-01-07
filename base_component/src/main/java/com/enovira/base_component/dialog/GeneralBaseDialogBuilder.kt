package com.enovira.base_component.dialog

import android.view.View
import android.view.WindowManager
import com.enovira.base_component.BaseDialogBuilder

typealias CancelClickListener = View.OnClickListener
typealias ConfirmClickListener = View.OnClickListener

/**
 * @date 2024/12/5 17:07
 * @author xiaozy
 * @description
 */
abstract class GeneralBaseDialogBuilder : BaseDialogBuilder {
    var cancelable: Boolean = true
        private set
    var cancelOnTouchOutside: Boolean = false
        private set
    var width: Int = WindowManager.LayoutParams.WRAP_CONTENT
        private set
    var height: Int = WindowManager.LayoutParams.WRAP_CONTENT
        private set
    var showTitle: Boolean = true
        private set
    var title: String? = null
        private set
    var content: String? = null
        private set
    var cancelText: String? = "取消"
        private set
    var confirmText: String? = "确定"
        private set
    var clickCancelListener: CancelClickListener? = null
        private set
    var confirmClickListener: ConfirmClickListener? = null
        private set
    var showCancelButton: Boolean = true
        private set
    var showConfirmButton: Boolean = true
        private set

    /**
     * 设置弹窗可被取消操作取消，例如按下返回键、虚拟返回事件
     */
    fun setCancelable(cancelable: Boolean): GeneralBaseDialogBuilder {
        this.cancelable = cancelable
        return this
    }

    /**
     * 触碰弹窗外部取消弹窗，用于快捷取消
     */
    fun setCancelOnTouchOutside(cancelOnTouchOutside: Boolean): GeneralBaseDialogBuilder {
        this.cancelOnTouchOutside = cancelOnTouchOutside
        return this
    }

    /**
     * 设置弹窗的宽度，默认占满整个屏幕
     */
    fun swtWidth(width: Int): GeneralBaseDialogBuilder {
        this.width = width
        return this
    }

    /**
     * 设置弹窗的高度，默认占满整个屏幕
     */
    fun setHeight(height: Int): GeneralBaseDialogBuilder {
        this.height = height
        return this
    }

    /**
     * 是否显示弹窗标题，默认显示
     */
    fun isShowTitle(isShow: Boolean): GeneralBaseDialogBuilder {
        this.showTitle = isShow
        return this
    }

    /**
     * 设置弹窗标题，showTitle为true状态下有效
     */
    fun setTitle(title: String): GeneralBaseDialogBuilder {
        this.title = title
        return this
    }

    /**
     * 设置弹窗内容，必要!!！
     */
    fun setContent(content: String): GeneralBaseDialogBuilder {
        this.content = content
        return this
    }

    /**
     * 设置取消按钮文本，showCancelButton为true状态下有效
     */
    fun setCancelText(cancelText: String?): GeneralBaseDialogBuilder {
        return this.apply { cancelText?.let { this.cancelText = it } }
    }

    /**
     * 设置确认按钮文本，showConfirmButton为true状态下有效
     */
    fun setConfirmText(confirmText: String?): GeneralBaseDialogBuilder {
        return this.apply { confirmText?.let { this.confirmText = it } }
    }

    /**
     * 设置取消按钮点击事件，showCancelButton为true状态下有效
     */
    fun setClickCancelListener(listener: CancelClickListener?): GeneralBaseDialogBuilder {
        this.clickCancelListener = listener
        return this
    }

    /**
     * 设置确认按钮点击事件，showConfirmButton为true状态下有效
     */
    fun setConfirmClickListener(listener: ConfirmClickListener?): GeneralBaseDialogBuilder {
        this.confirmClickListener = listener
        return this
    }

    /**
     * 设置取消按钮是否显示，会影响取消按钮文本及点击事件是否生效
     */
    fun isShowCancelButton(isShow: Boolean): GeneralBaseDialogBuilder {
        this.showCancelButton = isShow
        return this
    }

    /**
     * 设置确认按钮是否显示，会影响取消按钮文本及点击事件是否生效
     */
    fun isShowConfirmButton(isShow: Boolean): GeneralBaseDialogBuilder {
        this.showConfirmButton = isShow
        return this
    }


}