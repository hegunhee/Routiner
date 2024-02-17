package com.hegunhee.routiner.insertRoutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hegunhee.routiner.insertRoutine.databinding.FragmentInsertRoutineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertRoutineFragment : Fragment(){

    private val viewModel : InsertRoutineViewModel by viewModels()
    private lateinit var viewDataBinding : FragmentInsertRoutineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_insert_routine,container,false)
        viewDataBinding = FragmentInsertRoutineBinding.bind(root).apply {
            viewModel = this@InsertRoutineFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
    }

    private fun setActionBarTitle(){
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "루틴 추가"
    }
}