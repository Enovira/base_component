package com.enovira.base_component.dialog

import androidx.fragment.app.DialogFragment

/**
 * @date 2024/12/11 14:17
 * @author xiaozy
 * @description
 */
object DialogFactory {
    const val GENERAL_DIALOG = 1
    const val LOADING_DIALOG = 2

    @JvmStatic
    fun getNoTitleDialog(
        msg: String,
        cancelText: String? = null,
        confirmText: String? = null,
        cancelClickListener: CancelClickListener? = null,
        confirmClickListener: ConfirmClickListener? = null,
    ): DialogFragment {
        return GeneralDialog.getBuilder()
            .setContent(msg)
            .isShowTitle(false)
            .setCancelOnTouchOutside(true)
            .isShowCancelButton(false)
            .setCancelText(cancelText)
            .setConfirmText(confirmText)
            .setConfirmClickListener(confirmClickListener)
            .setClickCancelListener(cancelClickListener)
            .build()
    }

    @JvmStatic
    fun getConfirmDialog(
        title: String,
        msg: String,
        listener: ConfirmClickListener,
    ): DialogFragment {
        return GeneralDialog.getBuilder()
            .setTitle(title)
            .setContent(msg)
            .setCancelOnTouchOutside(true)
            .setConfirmClickListener(listener)
            .isShowCancelButton(false)
            .build()
    }

    @JvmStatic
    fun getLoadingDialog(msg: String? = null): DialogFragment {
        return LoadingDialog.getBuilder()
            .setContent(msg)
            .setCancelable(true)
            .setCancelOnTouchOutside(false)
            .build()
    }
}