package com.hegunhee.repeat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.RepeatRoutine
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.repeat.databinding.DialogClickRepeatRecordItemBinding
import com.hegunhee.repeat.databinding.FragmentRepeatBinding
import com.hegunhee.repeat.insert.InsertRepeatRoutineDialogFragment
import dagger.hilt.android.AndroidEntryPoint
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
        setActionBarTitle()
        initObserver()
        observeData()
    }

    private fun setActionBarTitle(){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "반복 루틴 설정"
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