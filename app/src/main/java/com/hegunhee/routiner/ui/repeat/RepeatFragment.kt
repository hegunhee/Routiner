package com.hegunhee.routiner.ui.repeat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.hegunhee.routiner.R
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.databinding.DialogClickRepeatRecordItemBinding
import com.hegunhee.routiner.databinding.DialogRepeatRoutineBinding
import com.hegunhee.routiner.databinding.FragmentRepeatBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.mainActivity.MainActivity
import com.hegunhee.routiner.util.addCheckableChip
import com.hegunhee.routiner.util.getTodayDayOfWeekFormatedKorean
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepeatFragment : BaseFragment<FragmentRepeatBinding>(R.layout.fragment_repeat) {

    private val viewModel: RepeatViewModel by viewModels()

    private val adapter: RepeatAdapter by lazy {
        RepeatAdapter(listOf()) { repeatRoutine ->
            clickAdapterItem(
                repeatRoutine
            )
        }
    }

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
            if (it.isEmpty()) {

            } else {
                adapter.setList(it)
            }
        }
    }


    private fun showRepeatDialog(isRevise : Boolean = false, repeatRoutine: RepeatRoutine? = null) {
        DialogRepeatRoutineBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(this.root).show()

            repeatRoutine?.let {
                if(isRevise){
                    routineEditText.setText(it.text)
                    routineEditText.isEnabled = false
                    val chips = arrayOf<Chip>(mondayChip,tuesdayChip,wednesdayChip,thursdayChip,fridayChip,saturdayChip,sundayChip)
                    for(chip in chips){
                        if(chip.text in it.dayOfWeekList) chip.isChecked = true
                    }

                }
            }
            viewModel.categoryList.value?.forEach {
                categoryGroup.addCheckableChip(it.name)
            }
            // 추가

            cancelButton.setOnClickListener { dialog.dismiss() }

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
                    val categoryText = categoryGroup.checkedChipIds.map {
                        categoryGroup.findViewById<Chip>(it).text.toString()
                    }
                    Toast.makeText(requireContext(), categoryText.toString(), Toast.LENGTH_SHORT).show()
                    if (getTodayDayOfWeekFormatedKorean() in dayOfWeekStringList && !isRevise) {
                        if(categoryText.isEmpty()){
                            viewModel.insertDailyRoutine(repeatRoutineText)
                        }else{
                            viewModel.insertDailyRoutine(repeatRoutineText, category = categoryText.first())
                        }
                    }
                    if(categoryText.isEmpty()){
                        viewModel.insertRepeatRoutine(repeatRoutineText, dayOfWeekStringList)
                    }else{
                        viewModel.insertRepeatRoutine(repeatRoutineText, dayOfWeekStringList, category = categoryText.first())
                    }
                    // 반복에 다는것
                    dialog.dismiss()
                }
            }
        }
    }

    private fun clickAdapterItem(repeatRoutine: RepeatRoutine) {

        DialogClickRepeatRecordItemBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(root).show()
            reviseTextView.setOnClickListener{
                showRepeatDialog(true,repeatRoutine)
                dialog.dismiss()
            }
            deleteTextView.setOnClickListener{

                viewModel.deleteRepeatRoutine(repeatRoutine.text)
                dialog.dismiss()
            }
        }
    }
}