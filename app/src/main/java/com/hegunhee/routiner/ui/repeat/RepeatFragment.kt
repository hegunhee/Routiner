package com.hegunhee.routiner.ui.repeat

import android.os.Bundle
import android.view.View
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentRepeatBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.mainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepeatFragment : BaseFragment<FragmentRepeatBinding>(R.layout.fragment_repeat){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "반복 루틴 설정"

    }
}