package com.enovira.base_component.dialog

import com.enovira.base_component.BaseDialogBuilder

/**
 * @date 2025/1/2 14:56
 * @author xiaozy
 * @description
 */
abstract class LoadingBaseDialogBuilder : BaseDialogBuilder {

    var content: String? = null
        private set

    var cancelable: Boolean = true
        private set

    var cancelOnTouchOutside: Boolean = false
        private set

    fun setContent(content: String?): LoadingBaseDialogBuilder {
        return this.apply { this.content = content }
    }

    fun setCancelable(cancelable: Boolean): LoadingBaseDialogBuilder {
        return this.apply { this.cancelable = cancelable }
    }

    fun setCancelOnTouchOutside(cancelOnTouchOutside: Boolean): LoadingBaseDialogBuilder {
        return this.apply { this.cancelOnTouchOutside = cancelOnTouchOutside }
    }

}