package com.hegunhee.feature.record

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.hegunhee.feature.R
import com.hegunhee.feature.base.BaseFragment
import com.hegunhee.feature.databinding.FragmentRecordBinding
import com.hegunhee.feature.mainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private val viewModel: RecordViewModel by viewModels()
    private val adapter : RecordAdapter by lazy {RecordAdapter(listOf())}
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
        currentDate.observe(viewLifecycleOwner){
            setActionBarTitle(it)
        }
        currentRoutineListState.observe(viewLifecycleOwner){
            when(it){
                RoutineListState.Uninitalized -> {
                }
                is RoutineListState.Success -> {
                    adapter.setList(it.routineList)
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
        if (recordIsEmpty.value == true) {
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