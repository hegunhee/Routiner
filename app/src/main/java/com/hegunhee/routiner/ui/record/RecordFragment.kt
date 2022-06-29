package com.hegunhee.routiner.ui.record

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentRecordBinding
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    private val viewModel: RecordViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = lifecycleOwner
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
                    // 여기서 이제 RecyclerView의 데이터를 변경줌
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
                    Toast.makeText(requireContext(), "click_left", Toast.LENGTH_SHORT).show()
                    setLeftData()
                }
                R.id.click_right -> {
                    Toast.makeText(requireContext(), "click_right", Toast.LENGTH_SHORT).show()
                    setRightDate()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}