package com.example.main.guide

import android.os.Bundle
import android.view.View
import com.example.main.R
import com.example.main.databinding.DialogGuideBinding
import com.hegunhee.common.base.BaseDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideDialogFragment() : BaseDialog<DialogGuideBinding>(layoutResId = R.layout.dialog_guide,widthPercent = 0.8,heightPercent = 0.4) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.alertAcceptButton.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }



    companion object{
        const val TAG = "GuideDialogFragment"
        fun getInstance() : GuideDialogFragment{
            return GuideDialogFragment().apply {
                isCancelable = false
            }
        }
    }
}