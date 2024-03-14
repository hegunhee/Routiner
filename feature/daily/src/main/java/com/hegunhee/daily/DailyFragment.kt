package com.hegunhee.daily

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.common.progressIndicator.ProgressIndicatorAdapter
import com.hegunhee.common.progressIndicator.ProgressIndicatorContainer
import com.hegunhee.daily.databinding.FragmentDailyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DailyFragment : BaseFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val viewModel : DailyViewModel by viewModels()
    private lateinit var concatAdapter : ConcatAdapter
    private lateinit var dailyAdapter : DailyAdapter
    private lateinit var progressAdapter : ProgressIndicatorAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dailyAdapter = DailyAdapter(viewModel).apply { setHasStableIds(true) }
        progressAdapter = ProgressIndicatorAdapter()
        concatAdapter = ConcatAdapter(
            dailyAdapter,
            progressAdapter
        )
        binding.apply {
            viewmodel = viewModel
            dailyRecyclerView.adapter = concatAdapter
        }
        setActionBarTitle()
        observeData()
    }

    private fun setActionBarTitle(){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "오늘의 루틴"
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.navigationActions.collect {
                        when(it) {
                            DailyNavigationAction.InsertRoutine -> {
                                findNavController().navigate(com.hegunhee.routiner.navigation.R.id.daily_to_insertRoutine)
                            }
                        }
                    }
                }
                launch {
                    viewModel.dailyRoutineEntityList.collect{ routineList ->
                        if(routineList.isNotEmpty()) {
                            dailyAdapter.submitList(routineList.toViewTypeList())
                        }
                        progressAdapter.submitList(
                            listOf(
                                ProgressIndicatorContainer(
                                    finishedRoutine = routineList.filter { it.isFinished }.size,
                                    totalRoutine = routineList.size)
                            )
                        )
                    }
                }
            }
        }
    }
}