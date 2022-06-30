package com.hegunhee.routiner.ui.daily

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.hegunhee.routiner.R
import com.hegunhee.routiner.databinding.FragmentDailyBinding
import com.hegunhee.routiner.db.SharedPreferenceManager
import com.hegunhee.routiner.ui.BaseFragment
import com.hegunhee.routiner.ui.MainActivity
import com.hegunhee.routiner.util.getCurrentDate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DailyFragment : BaseFragment<FragmentDailyBinding>(R.layout.fragment_daily) {

    private val viewModel : DailyViewModel by viewModels()
    private val adapter : DailyAdapter by lazy {DailyAdapter(
        listOf(),
        deleteRoutine = { id -> viewModel.deleteData(id)},
        insertRoutine = {routine -> viewModel.toggleData(routine)}
        )}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "DailyFragment", Toast.LENGTH_SHORT).show()
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = lifecycleOwner
            dailyRecyclerView.adapter = adapter
        }
        (requireActivity() as MainActivity).supportActionBar?.title = getCurrentDate().toString()
        initObserver()
    }

    private fun initObserver(){
        viewModel.onClickEvent.observe(viewLifecycleOwner){
            when(it){
                Event.Uninitalized -> {}
                Event.Clicked -> {
                    insertData()
                }
                Event.EndClick -> {}
            }
        }
        viewModel.dailyRoutineListLiveData.observe(viewLifecycleOwner){
            adapter.setRoutineList(it)
        }
    }

    private fun insertData(){
        val editText = EditText(requireActivity())
        AlertDialog.Builder(requireActivity())
            .setTitle("루틴을 입력해주세요")
            .setView(editText)
            .setPositiveButton(
                "등록",
                DialogInterface.OnClickListener { _, _ ->
                    if (editText.text.toString() == "") {
                        Toast.makeText(requireActivity(), "입력칸이 비어있습니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.insertRoutine(editText.text.toString())
                    }
                })
            .setNegativeButton("취소", DialogInterface.OnClickListener { _, _ ->
            })
            .show()
        viewModel.endClick()
    }
}