package com.hegunhee.feature.repeat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.domain.model.RepeatRoutine
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseFragment
import com.hegunhee.feature.databinding.DialogClickRepeatRecordItemBinding
import com.hegunhee.feature.databinding.DialogInsertCategoryBinding
import com.hegunhee.feature.databinding.DialogRepeatRoutineBinding
import com.hegunhee.feature.databinding.FragmentRepeatBinding
import com.hegunhee.feature.mainActivity.MainActivity
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
                    val categoryText = if(categoryGroup.checkedChipId == View.NO_ID){
                        ""
                    }else{
                        categoryGroup.findViewById<Chip>(categoryGroup.checkedChipId).text.toString()
                    }
                    if (getTodayDayOfWeekFormatedKorean() in dayOfWeekStringList && !isRevise) {
                        viewModel.insertDailyRoutine(repeatRoutineText, category = categoryText)
                    }
                    viewModel.insertRepeatRoutine(repeatRoutineText, dayOfWeeks = dayOfWeekStringList, category = categoryText)
                    dialog.dismiss()
                }
            }
            insertCategoryChip.setOnClickListener {
                insertCategory(categoryGroup)
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

    private fun insertCategory(chipGroup: ChipGroup){
        DialogInsertCategoryBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(root).show()
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            succeedButton.setOnClickListener {
                val categoryText = categoryEditText.text.toString().trim()
                if(categoryText == ""){
                    Toast.makeText(requireContext(), "입력칸이 비어있습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    chipGroup.addCheckableChip(categoryText)
                    viewModel.insertCategory(categoryText)
                    dialog.dismiss()
                }
            }
        }
    }
}