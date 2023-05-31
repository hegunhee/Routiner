package com.hegunhee.setting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.hegunhee.common.base.BaseFragment
import com.example.main.MainActivity
import com.hegunhee.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel : SettingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            notiSwitchButton.isChecked = viewModel.getNotiSendValue()
        }
        setActionBarTitle()
        initListener()
    }

    private fun setActionBarTitle(){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "설정"
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