package com.enovira.base_component.dialog

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.enovira.base_component.BaseDialogFragment
import com.enovira.base_component.databinding.DialogFullScreenBinding

/**
 * @date 2024/12/5 17:11
 * @author xiaozy
 * @description 确认对话框
 */
class GeneralDialog private constructor(private val builder: GeneralBaseDialogBuilder) :
    BaseDialogFragment(builder) {

    private lateinit var binding: DialogFullScreenBinding

    override fun initView(inflater: LayoutInflater): View {
        binding = DialogFullScreenBinding.inflate(inflater)
        if (!builder.title.isNullOrEmpty() && builder.showTitle) {
            binding.tvTitle.text = builder.title
        } else {
            binding.tvTitle.visibility = View.GONE
        }
        binding.tvContent.text = builder.content
        if (builder.showCancelButton && !builder.cancelText.isNullOrEmpty()) {
            binding.btnCancel.text = builder.cancelText
            binding.btnCancel.setOnClickListener {
                builder.clickCancelListener?.onClick(it)
                dismiss()
            }
        } else {
            binding.btnCancel.visibility = View.GONE
        }
        if (builder.showConfirmButton && !builder.confirmText.isNullOrEmpty()) {
            binding.btnConfirm.text = builder.confirmText
            binding.btnConfirm.setOnClickListener {
                builder.confirmClickListener?.onClick(it)
                dismiss()
            }
        } else {
            binding.btnConfirm.visibility = View.GONE
        }

        return binding.root
    }


    companion object {
        @JvmStatic
        fun getBuilder(): GeneralBaseDialogBuilder {
            return GeneralBuilderBase()
        }
    }

    private class GeneralBuilderBase : GeneralBaseDialogBuilder() {
        override fun build(): DialogFragment {
            return GeneralDialog(this)
        }
    }
}