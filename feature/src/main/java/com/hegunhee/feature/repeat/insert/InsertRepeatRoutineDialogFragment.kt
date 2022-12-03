package com.hegunhee.feature.repeat.insert

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseDialog
import com.hegunhee.feature.category.insert.InsertCategoryDialogFragment
import com.hegunhee.feature.databinding.DialogRepeatRoutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRepeatRoutineDialogFragment : BaseDialog<DialogRepeatRoutineBinding>(R.layout.dialog_repeat_routine, widthPercent = 0.8, heightPercent = 0.4){

    private val viewModel : InsertRepeatRoutineDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply { viewModel = this@InsertRepeatRoutineDialogFragment.viewModel }
        observeData()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
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
        }
    }
}