package com.hegunhee.routiner.ui.repeat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.databinding.RepeatRecordItemBinding
import com.hegunhee.routiner.util.removebracket

class RepeatAdapter(
    private var repeatRoutineList: List<RepeatRoutine>,
    val clickRoot : (RepeatRoutine) -> Unit
) : RecyclerView.Adapter<RepeatAdapter.RepeatViewHolder>() {

    inner class RepeatViewHolder(private val binding: RepeatRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repeatRoutine: RepeatRoutine) = with(binding) {
            title.text = repeatRoutine.text
            chip.text = repeatRoutine.dayOfWeekList.toString().removebracket()
            root.setOnClickListener{
                clickRoot(repeatRoutine)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatViewHolder {
        return RepeatViewHolder(
            RepeatRecordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepeatViewHolder, position: Int) {
        holder.bind(repeatRoutineList[position])
    }

    override fun getItemCount(): Int = repeatRoutineList.size

    fun setList(list: List<RepeatRoutine>) {
        repeatRoutineList = list
        notifyDataSetChanged()
    }
}