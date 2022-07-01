package com.hegunhee.routiner.ui.setting

import android.os.Bundle
import android.view.View
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentSettingBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "설정"
        binding.apply {
        }


    }
}