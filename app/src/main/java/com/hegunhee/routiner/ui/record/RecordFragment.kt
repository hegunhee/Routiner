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
        (requireActivity() as MainActivity).supportActionBar?.title = "record"
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.record_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (viewModel.recordIsEmpty.value == true) {
            Toast.makeText(requireContext(), "기록이 존재하지않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            when (item.itemId) {
                R.id.click_left -> {
                    Toast.makeText(requireContext(), "click_left", Toast.LENGTH_SHORT).show()
                }
                R.id.click_right -> {
                    Toast.makeText(requireContext(), "click_right", Toast.LENGTH_SHORT).show()

                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

}