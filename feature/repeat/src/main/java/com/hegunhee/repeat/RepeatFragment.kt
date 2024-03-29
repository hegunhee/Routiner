package com.hegunhee.repeat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.repeat.databinding.FragmentRepeatBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepeatFragment : BaseFragment<FragmentRepeatBinding>(R.layout.fragment_repeat) {

    private val viewModel: RepeatViewModel by viewModels()

    private lateinit var repeatAdapter: RepeatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatAdapter = RepeatAdapter(viewModel)
        binding.apply {
            this.viewModel = this@RepeatFragment.viewModel
            repeatRoutineRecyclerView.adapter = repeatAdapter
        }
        setActionBarTitle()
        initObserver()
    }

    private fun setActionBarTitle(){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "반복 루틴 설정"
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.navigationActions.collect { action ->
                        when (action) {
                            RepeatNavigationAction.InsertRepeatRoutine -> {
                                findNavController().navigate(com.hegunhee.routiner.navigation.R.id.repeat_to_insertRepeatRoutine)
                            }
                        }
                    }
                }
                launch {
                    viewModel.repeatRoutineList.collect {
                        repeatAdapter.submitList(it)
                    }
                }
            }
        }
    }
}