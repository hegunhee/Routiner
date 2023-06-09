package com.hegunhee.repeat.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.hegunhee.category.CategoryAdapter
import com.hegunhee.common.base.BaseDialog
import com.hegunhee.category.insert.InsertCategoryDialogFragment
import com.hegunhee.repeat.R
import com.hegunhee.repeat.databinding.DialogRepeatRoutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRepeatRoutineDialogFragment : BaseDialog<DialogRepeatRoutineBinding>(R.layout.dialog_repeat_routine, widthPercent = 1.0, heightPercent = 0.8){

    private val viewModel : InsertRepeatRoutineDialogViewModel by viewModels()
    private lateinit var categoryAdapter : CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter(viewModel)
        binding.apply {
            viewModel = this@InsertRepeatRoutineDialogFragment.viewModel
            categoryRecyclerView.adapter = categoryAdapter
        }
        observeData()
        dayOfWeekChipGroupClickListener()
    }

    private fun observeData() {
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.navigationActions.collect {
                    when (it) {
                        InsertRepeatRoutineNavigationAction.DisMissDialog -> {
                            dismiss()
                        }
                        InsertRepeatRoutineNavigationAction.OpenInsertCategoryDialog -> {
                            InsertCategoryDialogFragment().show(childFragmentManager, "insert_category")
                        }
                    }
                }
            }
            launch {
                viewModel.toastMessage.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
            launch {
                viewModel.categoryList.collect {
                    categoryAdapter.submitList(it)
                }
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