package com.hegunhee.routiner.ui.repeat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.DialogRepeatRoutineBinding
import com.hegunhee.routiner.databinding.FragmentRepeatBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.mainActivity.MainActivity
import com.hegunhee.routiner.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepeatFragment : BaseFragment<FragmentRepeatBinding>(R.layout.fragment_repeat) {

    private val viewModel: RepeatViewModel by viewModels()

    private val adapter : RepeatAdapter by lazy { RepeatAdapter(listOf()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            recyclerView.adapter = adapter
        }
        (requireActivity() as MainActivity).supportActionBar?.title = "반복 루틴 설정"
        initObserver()

    }

    private fun initObserver() {
        viewModel.clickEvent.observe(viewLifecycleOwner) {
            when (it) {
                ClickEvent.Uninitalized -> {}
                ClickEvent.Clicked -> {
                    showRepeatDialog()
                    viewModel.finishClick()
                }
                ClickEvent.Finished -> {}
            }
        }
        viewModel.repeatRoutineListLiveData.observe(viewLifecycleOwner) {
            if(it.isEmpty()){

            }else{
                adapter.setList(it)
            }
        }
    }


    private fun showRepeatDialog() {
        DialogRepeatRoutineBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(this.root).show()
            cancelButton.setOnClickListener {
                Toast.makeText(requireContext(), "취소버튼을 누르셨습니다.", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            succeedButton.setOnClickListener {
                val repeatRoutineText = routineEditText.text.toString()
                if (repeatRoutineText.isEmpty()) {
                    Toast.makeText(requireContext(), "내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
                } else if (dayOfWeekChipGroup.checkedChipIds.isEmpty()) {
                    Toast.makeText(requireContext(), "요일을 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    val dayOfWeekStringList = dayOfWeekChipGroup.checkedChipIds.map {
                        dayOfWeekChipGroup.findViewById<Chip>(it).text.toString()
                    }
                    if (getTodayDayOfWeekFormatedKorean() in dayOfWeekStringList) {
                        viewModel.insertDailyRoutine(repeatRoutineText)
                    }
                    viewModel.insertRepeatRoutine(repeatRoutineText, dayOfWeekStringList)
                    dialog.dismiss()
                }
            }
        }
    }
}