package com.hegunhee.feature.category.insert

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseDialog
import com.hegunhee.feature.databinding.DialogInsertCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertCategoryDialogFragment : BaseDialog<DialogInsertCategoryBinding>(R.layout.dialog_insert_category, widthPercent = 0.6, heightPercent = 0.2) {

    private val viewModel : InsertCategoryDialogViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply { viewModel = this@InsertCategoryDialogFragment.viewModel }
        observeData()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                launch {
                    viewModel.dismissDialog.collect{
                        dismiss()
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