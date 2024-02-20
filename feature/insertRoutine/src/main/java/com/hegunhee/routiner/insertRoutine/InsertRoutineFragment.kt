package com.hegunhee.routiner.insertRoutine

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
import com.hegunhee.routiner.insertRoutine.databinding.FragmentInsertRoutineBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsertRoutineFragment : Fragment() {

    private val viewModel: InsertRoutineViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentInsertRoutineBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_insert_routine, container, false)
        categoryAdapter = CategoryAdapter(viewModel)
        viewDataBinding = FragmentInsertRoutineBinding.bind(root).apply {
            viewModel = this@InsertRoutineFragment.viewModel
            categoryRecyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 3, GridLayoutManager.HORIZONTAL, false)
                adapter = categoryAdapter
            }
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeDrawer()
        setActionBarTitle()
        setEditorActionListener()
        observeData()
    }

    private fun closeDrawer() {
        val drawerLayout : DrawerLayout? = activity?.findViewById(com.example.main.R.id.drawerLayout)
        drawerLayout?.closeDrawer(GravityCompat.START)
    }

    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "루틴 추가"
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
                            InsertRoutineNavigationAction.InsertRoutine -> {
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
            }
        }
    }
}