package com.hegunhee.category.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.category.R
import com.hegunhee.category.databinding.DialogInsertCategoryBinding
import com.hegunhee.common.base.BaseDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertCategoryDialogFragment : BaseDialog<DialogInsertCategoryBinding>(R.layout.dialog_insert_category, widthPercent = 0.6, heightPercent = 0.2) {

    private val viewModel : InsertCategoryViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply { viewModel = this@InsertCategoryDialogFragment.viewModel }
        observeData()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.navigationActions.collect{
                        when(it) {
                            InsertCategoryNavigationAction.DismissDialog -> {
                                dismissAllowingStateLoss()
                            }
                        }
                        dismissAllowingStateLoss()
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

    companion object{
        val TAG = "insertCategory"
    }
}