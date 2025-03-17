package com.hegunhee.routiner.insertRoutine.repeat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hegunhee.category.CategoryAdapter
import com.hegunhee.common.dayOfWeek.DayOfWeekAdapter
import com.hegunhee.routiner.insertRoutine.R
import com.hegunhee.routiner.insertRoutine.databinding.FragmentInsertRepeatRoutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRepeatRoutineFragment : Fragment() {

    private val viewModel : InsertRepeatRoutineViewModel by viewModels()
    private lateinit var viewDataBinding : FragmentInsertRepeatRoutineBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var dayOfWeekAdapter: DayOfWeekAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_insert_repeat_routine,container,false)
        categoryAdapter = CategoryAdapter(viewModel)
        dayOfWeekAdapter = DayOfWeekAdapter(viewModel)
        viewDataBinding = FragmentInsertRepeatRoutineBinding.bind(root).apply {
            viewModel = this@InsertRepeatRoutineFragment.viewModel
            categoryRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.HORIZONTAL,false)
                adapter = categoryAdapter
            }
            dayOfWeekRecyclerView.apply {
                adapter = dayOfWeekAdapter
            }
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
        setEditorActionListener()
        observeData()
    }


    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "반복 루틴 추가"
    }

    private fun setEditorActionListener() {
        viewDataBinding.categoryAddEditText.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.onCategoryAddClick()
                true
            } else {
                false
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.navigationActions.collect {
                        when (it) {
                            InsertRepeatRoutineNavigationAction.InsertRepeatRoutine -> {
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
                launch {
                    viewModel.categoryList.collect {
                        categoryAdapter.submitList(it)
                    }
                }
                launch {
                    viewModel.toastMessage.collect { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                launch {
                    viewModel.dayOfWeekList.collect { dayOfWeekList ->
                        dayOfWeekAdapter.submitList(dayOfWeekList)
                    }
                }
            }
        }
    }
}