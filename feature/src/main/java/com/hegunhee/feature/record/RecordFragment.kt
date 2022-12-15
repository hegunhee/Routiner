package com.hegunhee.feature.record

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hegunhee.feature.R
import com.example.common.base.BaseFragment
import com.example.main.MainActivity
import com.hegunhee.feature.databinding.FragmentRecordBinding
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
        (requireActivity() as MainActivity).supportActionBar?.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.record_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = with(viewModel){
        if (recordIsEmpty.value) {
            Toast.makeText(requireContext(), "기록이 존재하지않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            when (item.itemId) {
                R.id.click_left -> {
                    setLeftData()
                }
                R.id.click_right -> {
                    setRightDate()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}