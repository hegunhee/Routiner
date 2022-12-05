package com.hegunhee.feature.repeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RepeatRoutine
import com.example.domain.model.Routine
import com.hegunhee.feature.databinding.RepeatRecordItemBinding
import com.hegunhee.feature.util.addChip

class RepeatAdapter(
    val eventHandler : RepeatActionHandler,
) : ListAdapter<RepeatRoutine,RepeatAdapter.RepeatViewHolder>(diff_util) {

    inner class RepeatViewHolder(private val binding: RepeatRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repeatRoutineEntity: RepeatRoutine) = with(binding) {
            eventHandler = this@RepeatAdapter.eventHandler
            repeatRoutine = repeatRoutineEntity
            title.text = repeatRoutineEntity.text
            chipGroup.removeAllViews()
            addChip(repeatRoutineEntity.dayOfWeekList)

            if(repeatRoutineEntity.category != ""){
                categoryChip.visibility = View.VISIBLE
                categoryChip.text = repeatRoutineEntity.category
            }
            binding.executePendingBindings()
        }


        private fun addChip(list : List<String>){
            binding.run {
                list.forEach {
                    chipGroup.addChip(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatViewHolder {
        return RepeatViewHolder(RepeatRecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RepeatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diff_util = object : DiffUtil.ItemCallback<RepeatRoutine>(){
            override fun areItemsTheSame(oldItem: RepeatRoutine, newItem: RepeatRoutine): Boolean {
                return oldItem.text == newItem.text
            }

            override fun areContentsTheSame(
                oldItem: RepeatRoutine,
                newItem: RepeatRoutine
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}