package com.hegunhee.daily

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.daily.databinding.FragmentDailyBinding
import com.hegunhee.daily.insert.InsertRoutineDialogFragment
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "오늘의 루틴"
    }

    private fun initObserver(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.onClickInsertRoutineButton.collect{
                    InsertRoutineDialogFragment().show(childFragmentManager,InsertRoutineDialogFragment.TAG)
                }
            }
        }
        viewModel.dailyRoutineEntityListLiveData.observe(viewLifecycleOwner){
            dailyAdapter.submitList(it)
        }
    }
}