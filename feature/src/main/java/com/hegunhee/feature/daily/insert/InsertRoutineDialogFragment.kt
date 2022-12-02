package com.hegunhee.feature.daily.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseDialog
import com.hegunhee.feature.category.insert.InsertCategoryDialogFragment
import com.hegunhee.feature.databinding.DialogDailyRoutineBinding
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
            }
        }
    }
}