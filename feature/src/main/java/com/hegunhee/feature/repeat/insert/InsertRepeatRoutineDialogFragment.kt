package com.hegunhee.feature.repeat.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.chip.Chip
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseDialog
import com.hegunhee.feature.category.insert.InsertCategoryDialogFragment
import com.hegunhee.feature.databinding.DialogRepeatRoutineBinding
import com.hegunhee.feature.util.addCheckableChip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRepeatRoutineDialogFragment : BaseDialog<DialogRepeatRoutineBinding>(R.layout.dialog_repeat_routine, widthPercent = 0.8, heightPercent = 0.4){

    private val viewModel : InsertRepeatRoutineDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply { viewModel = this@InsertRepeatRoutineDialogFragment.viewModel }
        observeData()
        categoryGroupClickListener()
        dayOfWeekChipGroupClickListener()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                launch {
                    viewModel.navigationActions.collect{
                        when(it){
                            InsertRepeatRoutineNavigationAction.DisMissDialog -> {
                                dismiss()
                            }
                            InsertRepeatRoutineNavigationAction.OpenInsertCategoryDialog -> {
                                InsertCategoryDialogFragment().show(childFragmentManager,"insert_category")
                            }
                        }
                    }
                }
                launch {
                    viewModel.toastMessage.collect{
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
                launch {
                    viewModel.categoryList.collect{
                        if (it.isEmpty()) return@collect
                        if (binding.categoryGroup.isEmpty()) {
                            it.forEach {
                                binding.categoryGroup.addCheckableChip(it.name)
                            }
                        } else {
                            binding.categoryGroup.addCheckableChip(it.last().name)
                        }
                    }
                }
            }
        }
    }
    private fun categoryGroupClickListener(){
        binding.categoryGroup.setOnCheckedStateChangeListener { group, checkedIdList ->
            viewModel.categoryText = if(checkedIdList.isEmpty()){
                ""
            }else{
                group.findViewById<Chip>(checkedIdList.first()).text.toString()
            }
        }
    }

    private fun dayOfWeekChipGroupClickListener() {
        binding.dayOfWeekChipGroup.setOnCheckedStateChangeListener { group, checkedIdList ->
            viewModel.dayOfWeekList = if(checkedIdList.isEmpty()){
                emptyList()
            }else{
                checkedIdList.map { group.findViewById<Chip>(it).text.toString() }.toList()
            }

        }
    }
}