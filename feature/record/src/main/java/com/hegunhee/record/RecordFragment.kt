package com.hegunhee.record

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import hegunhee.routiner.model.Date
import com.hegunhee.common.base.BaseFragment
import com.hegunhee.record.databinding.FragmentRecordBinding
import com.hegunhee.record.dateSelector.DateSelectorAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private val viewModel: RecordViewModel by viewModels()
    private lateinit var recordAdapter : RecordAdapter
    private lateinit var dateAdapter : DateSelectorAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recordAdapter = RecordAdapter()
        dateAdapter = DateSelectorAdapter(viewModel)
        binding.apply {
            viewmodel = viewModel
            recordRecyclerView.adapter = recordAdapter
            dateSelectorRecyclerView.adapter = dateAdapter
        }
        observeData()
    }

    private fun observeData() = with(viewModel){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    currentDate.collect{ date ->
                        val title = if(date == Date.EMPTY) {
                            "내역이 존재하지 않습니다"
                        }else {
                            date.desc
                        }
                        setActionBarTitle(title)
                    }
                }
                launch {
                    currentRoutineList.collect{
                        recordAdapter.submitList(it)
                    }
                }
                launch {
                    dateList.collect {
                        dateAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun setActionBarTitle(title : String){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}