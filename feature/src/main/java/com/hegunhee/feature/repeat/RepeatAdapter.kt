package com.hegunhee.feature.repeat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RepeatRoutine
import com.hegunhee.feature.databinding.RepeatRecordItemBinding

class RepeatAdapter(
    val eventHandler : RepeatActionHandler,
) : ListAdapter<RepeatRoutine,RepeatAdapter.RepeatViewHolder>(diff_util) {

    inner class RepeatViewHolder(private val binding: RepeatRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repeatRoutineEntity: RepeatRoutine) = with(binding) {
            repeatRoutine = repeatRoutineEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatViewHolder {
        return RepeatViewHolder(RepeatRecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            eventHandler = this@RepeatAdapter.eventHandler
        })
    }

    override fun onBindViewHolder(holder: RepeatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diff_util = object : DiffUtil.ItemCallback<RepeatRoutine>(){
            override fun areItemsTheSame(oldItem: RepeatRoutine, newItem: RepeatRoutine): Boolean {
                return oldItem.text == newItem.text
            }

            override fun areContentsTheSame(oldItem: RepeatRoutine, newItem: RepeatRoutine): Boolean {
                return oldItem == newItem
            }

        }
    }
}