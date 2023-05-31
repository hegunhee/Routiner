package com.example.main.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.main.R
import com.example.main.databinding.DialogGuideBinding
import com.hegunhee.common.base.BaseDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GuideDialogFragment() : BaseDialog<DialogGuideBinding>(layoutResId = R.layout.dialog_guide,widthPercent = 0.8,heightPercent = 0.4) {

    private val viewModel : GuideViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.navigateDismissDialog.collect{
                    Toast.makeText(requireActivity(), getString(R.string.notification_setting,if(viewModel.isAllowNotification.value) "승인" else "해제"), Toast.LENGTH_SHORT).show()
                    dismissAllowingStateLoss()
                }
            }
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