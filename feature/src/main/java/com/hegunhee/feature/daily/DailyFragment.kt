package com.hegunhee.feature.daily

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseFragment
import com.hegunhee.feature.databinding.DialogDailyRoutineBinding
import com.hegunhee.feature.databinding.DialogInsertCategoryBinding
import com.hegunhee.feature.databinding.FragmentDailyBinding
import com.hegunhee.feature.mainActivity.MainActivity
import com.hegunhee.feature.util.addCheckableChip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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
                if(isClick) insertData()
            }
        }
        viewModel.dailyRoutineEntityListLiveData.observe(viewLifecycleOwner){
            dailyAdapter.submitList(it)
        }
    }

    private fun insertData(){
        DialogDailyRoutineBinding.inflate(layoutInflater).run {
            val dialog = AlertDialog.Builder(requireContext()).setView(root).show()
            viewModel.categoryEntityList.value?.forEach {
                categoryGroup.addCheckableChip(it.name)
            }
            cancelButton.setOnClickListener {
                dialog.dismiss()
            }
            succeedButton.setOnClickListener {
                val routineText = routineEditText.text.toString()
                if(routineText.isBlank()){
                    Toast.makeText(requireContext(), "입력칸이 비어있습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    val categoryText = if(categoryGroup.checkedChipId == View.NO_ID){
                        ""
                    }else{
                        categoryGroup.findViewById<Chip>(categoryGroup.checkedChipId).text.toString()
                    }
                    viewModel.insertRoutine(routineText, category = categoryText)
                    dialog.dismiss()
                }
            }
            insertCategoryChip.setOnClickListener {
                insertCategory(categoryGroup)
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
                if(categoryText.isBlank()){
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