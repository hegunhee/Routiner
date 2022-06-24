package com.hegunhee.routiner.ui.daily

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentDailyBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.MainActivity

class DailyFragment : BaseFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "DailyFragment", Toast.LENGTH_SHORT).show()
        (requireActivity() as MainActivity).supportActionBar?.title = "daily"
    }
}