package com.hegunhee.feature.repeat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.model.RepeatRoutine
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseFragment
import com.hegunhee.feature.databinding.DialogClickRepeatRecordItemBinding
import com.hegunhee.feature.databinding.FragmentRepeatBinding
import com.hegunhee.feature.mainActivity.MainActivity
import com.hegunhee.feature.repeat.insert.InsertRepeatRoutineDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepeatFragment : BaseFragment<FragmentRepeatBinding>(R.layout.fragment_repeat) {

    private val viewModel: RepeatViewModel by viewModels()

    private lateinit var adapter: RepeatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RepeatAdapter(viewModel)
        binding.apply {
            viewmodel = viewModel
            recyclerView.adapter = adapter
        }
        (requireActivity() as MainActivity).supportActionBar?.title = "반복 루틴 설정"
        initObserver()
        observeData()

    }

    private fun initObserver() {
        viewModel.repeatRoutineListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun observeData(){
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.navigationActions.collect {
                    when (it) {
                        RepeatNavigationAction.InsertRepeatRoutine -> {
                            InsertRepeatRoutineDialogFragment().show(childFragmentManager, "insert_repeat_routine")
                        }
                        it as RepeatNavigationAction.ClickRepeatRoutine -> {
                            clickAdapterItem(it.repeatRoutine)
                        }
                    }
                }
            }
        }
    }

    private fun clickAdapterItem(repeatRoutine: RepeatRoutine) {
        DialogClickRepeatRecordItemBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(root).show()
            deleteTextView.setOnClickListener{
                viewModel.deleteRepeatRoutine(repeatRoutine.text)
                dialog.dismiss()
            }
        }
    }
}