package com.hegunhee.daily.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.chip.Chip
import com.hegunhee.category.CategoryAdapter
import com.hegunhee.common.base.BaseDialog
import com.hegunhee.category.insert.InsertCategoryDialogFragment
import com.hegunhee.daily.R
import com.hegunhee.daily.databinding.DialogDailyRoutineBinding
import com.hegunhee.common.util.addCheckableChip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRoutineDialogFragment : BaseDialog<DialogDailyRoutineBinding>(R.layout.dialog_daily_routine, widthPercent = 0.8, heightPercent = 0.4){

    private val viewModel : InsertRoutineDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@InsertRoutineDialogFragment.viewModel
        }
        observeData()
        categoryGroupClickListener()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                launch {
                    viewModel.navigateActions.collect{
                        when(it){
                            InsertRoutineNavigationAction.DismissDialog -> {
                                dismiss()
                            }
                            InsertRoutineNavigationAction.InsertCategoryDialog -> {
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

    private fun categoryGroupClickListener() {
        binding.categoryGroup.setOnCheckedStateChangeListener { group, checkedIdList ->
            viewModel.categoryText = if(checkedIdList.isEmpty()){
                ""
            }else{
                group.findViewById<Chip>(checkedIdList.first()).text.toString()
            }
        }
    }
    companion object {
        const val TAG = "insert_routine_dialog"
    }
}