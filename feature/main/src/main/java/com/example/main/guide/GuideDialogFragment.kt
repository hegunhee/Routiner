package com.example.main.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.main.R
import com.example.main.databinding.DialogGuideBinding
import com.hegunhee.common.base.BaseDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideDialogFragment() : BaseDialog<DialogGuideBinding>(layoutResId = R.layout.dialog_guide,widthPercent = 0.8,heightPercent = 0.4) {

    private val viewModel : GuideViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
//        binding.alertAcceptButton.setOnClickListener {
//            dismissAllowingStateLoss()
//        }
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