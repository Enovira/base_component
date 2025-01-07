package com.enovira.base_component.dialog

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.enovira.base_component.BaseLoadingDialogFragment
import com.enovira.base_component.databinding.DialogProgressbarBinding

/**
 * @date 2025/1/2 14:50
 * @author xiaozy
 * @description 加载弹窗
 */
class LoadingDialog private constructor(private val builder: LoadingBuilderBase): BaseLoadingDialogFragment(builder) {

    companion object {
        @JvmStatic
        fun getBuilder(): LoadingBaseDialogBuilder {
            return LoadingBuilderBase()
        }
    }

    private class LoadingBuilderBase: LoadingBaseDialogBuilder() {
        override fun build(): DialogFragment {
            return LoadingDialog(this)
        }
    }

    override fun initView(inflater: LayoutInflater): View {
        val binding = DialogProgressbarBinding.inflate(inflater)
        builder.content?.let {
            binding.progressText.visibility = View.VISIBLE
            binding.progressText.text = it
        }
        return binding.root
    }

}