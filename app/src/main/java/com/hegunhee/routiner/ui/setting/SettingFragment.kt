package com.hegunhee.routiner.ui.setting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentSettingBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.mainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel : SettingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "설정"
        binding.apply {
            viewmodel = viewModel
            notiSwitchButton.isChecked = viewModel.getNotiSendValue()
        }
        initListener()

    }
    private fun initListener() = with(binding){
        notiSwitchButton.setOnCheckedChangeListener { _, switch ->
            viewModel.setNotiSendValue(switch)
            if(switch){
                Toast.makeText(requireContext(), "알람 수신이 설정되었습니다.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "알람 수신이 해제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}