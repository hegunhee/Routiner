package com.hegunhee.daily.insert

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
import com.hegunhee.daily.R
import com.hegunhee.daily.databinding.DialogDailyRoutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRoutineDialogFragment : BaseDialog<DialogDailyRoutineBinding>(R.layout.dialog_daily_routine, widthPercent = 0.8, heightPercent = 0.4){

    private val viewModel : InsertRoutineDialogViewModel by viewModels()
    private lateinit var categoryAdapter : CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter(viewModel)
        binding.apply {
            viewModel = this@InsertRoutineDialogFragment.viewModel
            categoryRecyclerView.adapter = categoryAdapter
        }
        observeData()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.navigateActions.collect{
                        when(it){
                            InsertRoutineNavigationAction.DismissDialog -> {
                                dismissAllowingStateLoss()
                            }
                            InsertRoutineNavigationAction.InsertCategoryDialog -> {
                                InsertCategoryDialogFragment().show(childFragmentManager,InsertCategoryDialogFragment.TAG)
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
                        categoryAdapter.submitList(it)
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "insert_routine_dialog"
    }
}