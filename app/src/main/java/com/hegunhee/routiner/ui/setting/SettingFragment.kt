package com.hegunhee.routiner.ui.setting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentSettingBinding
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.mainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    @Inject lateinit var sharedPreferenceManager : SharedPreferenceManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "설정"
        binding.apply {
            notiSwitchButton.isChecked = sharedPreferenceManager.getNotiSendValue()
        }
        initListener()
    }
    private fun initListener() = with(binding){
        notiSwitchButton.setOnCheckedChangeListener { compoundButton, switch ->
            sharedPreferenceManager.setNofiSendValue(switch)
            if(switch){
                Toast.makeText(requireContext(), "알람 수신이 설정되었습니다.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "알람 수신이 해제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}