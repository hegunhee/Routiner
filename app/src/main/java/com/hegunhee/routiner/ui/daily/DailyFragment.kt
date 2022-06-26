package com.hegunhee.routiner.ui.daily

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentDailyBinding
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DailyFragment : BaseFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    @Inject lateinit var prefs : SharedPreferenceManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "DailyFragment", Toast.LENGTH_SHORT).show()
        (requireActivity() as MainActivity).supportActionBar?.title = "daily"
        Toast.makeText(requireContext(), ""+prefs.getCurrentDate(), Toast.LENGTH_SHORT).show()
    }
}