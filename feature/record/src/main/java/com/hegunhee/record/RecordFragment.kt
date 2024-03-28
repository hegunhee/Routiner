package com.hegunhee.record

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.record.databinding.FragmentRecordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private val viewModel: RecordViewModel by viewModels()
    private val adapter : RecordAdapter by lazy {RecordAdapter()}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            recordRecyclerView.adapter = adapter
        }
        observeData()
    }

    private fun observeData() = with(viewModel){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    currentDate.collect{ dateDesc ->
                        setActionBarTitle(dateDesc)
                    }
                }
                launch {
                    currentRoutineList.collect{
                        adapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun setActionBarTitle(title : String){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}