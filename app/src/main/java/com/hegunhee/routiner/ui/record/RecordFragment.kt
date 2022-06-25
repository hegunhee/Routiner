package com.hegunhee.routiner.ui.record

import android.os.Bundle
import android.view.View
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentRecordBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "record"
    }
}