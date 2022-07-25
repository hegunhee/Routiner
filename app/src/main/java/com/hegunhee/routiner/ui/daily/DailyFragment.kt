package com.hegunhee.routiner.ui.daily

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.DialogDailyRoutineBinding
import com.hegunhee.routiner.databinding.DialogInsertCategoryBinding
import com.hegunhee.routiner.databinding.FragmentDailyBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.mainActivity.MainActivity
import com.hegunhee.routiner.util.setRepeatDefaultColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFragment : BaseFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val viewModel : DailyViewModel by viewModels()
    private val adapter : DailyAdapter by lazy {DailyAdapter(
        listOf(),
        deleteRoutine = { id -> viewModel.deleteData(id)},
        toggleFinishedRoutine = { routine -> viewModel.toggleFinished(routine)}
        )}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            dailyRecyclerView.adapter = adapter
        }
        setActionBarTitle()
        initObserver()
    }

    private fun setActionBarTitle(){
        (requireActivity() as MainActivity).supportActionBar?.title = "오늘의 루틴"
    }

    private fun initObserver(){
        viewModel.onClickEvent.observe(viewLifecycleOwner){
            when(it){
                Event.Uninitalized -> {}
                Event.Clicked -> {
                    insertData()
                }
                Event.EndClick -> {}
            }
        }
        viewModel.dailyRoutineListLiveData.observe(viewLifecycleOwner){
            adapter.setRoutineList(it)
        }
    }

    private fun insertData(){
        DialogDailyRoutineBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(root).show()
            // 카테고리를 추가하는 로직이 필요함
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            succeedButton.setOnClickListener {
                val routineText = routineEditText.text.toString()
                if(routineText == ""){
                    Toast.makeText(requireContext(), "입력칸이 비어있습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.insertRoutine(routineText)
                    dialog.dismiss()
                }
            }
            insertCategoryChip.setOnClickListener {
                categoryGroup
                insertCategory(categoryGroup)
            }
        }
        viewModel.endClick()
    }

    private fun insertCategory(chipGroup: ChipGroup){
        DialogInsertCategoryBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(root).show()
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            succeedButton.setOnClickListener {
                val categoryText = categoryEditText.text.toString()
                if(categoryText == ""){
                    Toast.makeText(requireContext(), "입력칸이 비어있습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.insertCategory(categoryText)
                    chipGroup.addView(Chip(requireContext()).apply {
                        text = categoryText
                        setRepeatDefaultColor()
                    })
                    dialog.dismiss()
                }
            }
        }
    }
}