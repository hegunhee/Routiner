package com.hegunhee.feature.record

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.feature.R
import com.hegunhee.feature.databinding.RecordItemBinding

class RecordAdapter : ListAdapter<Routine,RecordAdapter.RecordViewHolder>(diff_util){
    inner class RecordViewHolder(private val binding : RecordItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(routine: Routine) = with(binding){
            title.text = routine.text
            root.backgroundTintList = if(routine.isFinished){
                ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.success_color))
            }else{
                ColorStateList.valueOf(ContextCompat.getColor(root.context,R.color.fail_color))
            }
            if(routine.category.isNotBlank()){
                category.visibility = View.VISIBLE
                category.text = routine.category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(RecordItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    companion object {
        val diff_util = object : DiffUtil.ItemCallback<Routine>(){
            override fun areItemsTheSame(oldItem: Routine, newItem: Routine): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Routine, newItem: Routine): Boolean {
                return oldItem == newItem
            }

        }
    }
}