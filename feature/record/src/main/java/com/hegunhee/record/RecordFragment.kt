package com.hegunhee.record

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
            recyclerView.adapter = adapter
        }
        setHasOptionsMenu(true)
        observeData()
    }

    private fun observeData() = with(viewModel){
        lifecycleScope.launchWhenResumed{
            launch {
                currentDate.collect{ date ->
                    setActionBarTitle(date)
                }
            }
            launch {
                currentRoutineListState.collect{
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun setActionBarTitle(title : String){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}