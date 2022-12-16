package com.hegunhee.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.record.databinding.RecordItemBinding

class RecordAdapter : ListAdapter<Routine,RecordAdapter.RecordViewHolder>(diff_util){
    inner class RecordViewHolder(private val binding : RecordItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(routine: Routine) = with(binding){
            this.routine = routine
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