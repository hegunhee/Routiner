package com.hegunhee.feature.daily

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseFragment
import com.hegunhee.feature.daily.insert.InsertRoutineDialogFragment
import com.hegunhee.feature.databinding.FragmentDailyBinding
import com.hegunhee.feature.mainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyFragment : BaseFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val viewModel : DailyViewModel by viewModels()
    private lateinit var dailyAdapter : DailyAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dailyAdapter = DailyAdapter(viewModel)
        binding.apply {
            viewmodel = viewModel
            dailyRecyclerView.adapter = dailyAdapter
        }
        setActionBarTitle()
        initObserver()
    }

    private fun setActionBarTitle(){
        (requireActivity() as MainActivity).supportActionBar?.title = "오늘의 루틴"
    }

    private fun initObserver(){
        lifecycleScope.launch{
            viewModel.onClickEvent.collect{ isClick ->
                if(isClick) InsertRoutineDialogFragment().show(childFragmentManager,"insert_routine")
            }
        }
        viewModel.dailyRoutineEntityListLiveData.observe(viewLifecycleOwner){
            dailyAdapter.submitList(it)
        }
    }
}