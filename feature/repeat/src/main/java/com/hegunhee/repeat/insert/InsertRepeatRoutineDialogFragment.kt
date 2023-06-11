package com.hegunhee.repeat.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.category.CategoryAdapter
import com.hegunhee.common.base.BaseDialog
import com.hegunhee.category.insert.InsertCategoryDialogFragment
import com.hegunhee.common.dayOfWeek.DayOfWeekAdapter
import com.hegunhee.repeat.R
import com.hegunhee.repeat.databinding.DialogRepeatRoutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRepeatRoutineDialogFragment : BaseDialog<DialogRepeatRoutineBinding>(R.layout.dialog_repeat_routine, widthPercent = 1.0, heightPercent = 0.8){

    private val viewModel : InsertRepeatRoutineDialogViewModel by viewModels()
    private lateinit var categoryAdapter : CategoryAdapter
    private lateinit var dayOfWeekAdapter : DayOfWeekAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter(viewModel)
        dayOfWeekAdapter = DayOfWeekAdapter(viewModel)
        binding.apply {
            viewModel = this@InsertRepeatRoutineDialogFragment.viewModel
            categoryRecyclerView.adapter = categoryAdapter
            dayOfWeekRecyclerView.adapter = dayOfWeekAdapter
        }
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.navigationActions.collect {
                        when (it) {
                            InsertRepeatRoutineNavigationAction.DisMissDialog -> {
                                dismissAllowingStateLoss()
                            }
                            InsertRepeatRoutineNavigationAction.OpenInsertCategoryDialog -> {
                                InsertCategoryDialogFragment().show(childFragmentManager, InsertCategoryDialogFragment.TAG)
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
                launch {
                    viewModel.dayOfWeekList.collect{
                        dayOfWeekAdapter.submitList(it)
                    }
                }
            }
        }
    }
}