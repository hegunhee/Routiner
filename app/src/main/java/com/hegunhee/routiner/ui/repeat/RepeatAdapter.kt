package com.hegunhee.routiner.ui.repeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.databinding.RepeatRecordItemBinding
import com.hegunhee.routiner.util.addChip
import com.hegunhee.routiner.util.setRepeatDefaultColor

class RepeatAdapter(
    private var repeatRoutineList: List<RepeatRoutine>,
    val clickRoot : (RepeatRoutine) -> Unit
) : RecyclerView.Adapter<RepeatAdapter.RepeatViewHolder>() {

    inner class RepeatViewHolder(private val binding: RepeatRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repeatRoutine: RepeatRoutine) = with(binding) {
            title.text = repeatRoutine.text
            chipGroup.removeAllViews()
            addChip(repeatRoutine.dayOfWeekList)
            chipGroup.setOnClickListener{
                clickRoot(repeatRoutine)
            }

            root.setOnClickListener{
                clickRoot(repeatRoutine)
            }
            if(repeatRoutine.category == ""){
            }else{
                categoryChip.visibility = View.VISIBLE
                categoryChip.text = repeatRoutine.category
            }
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
        holder.bind(repeatRoutineList[position])
    }

    override fun getItemCount(): Int = repeatRoutineList.size

    fun setList(list: List<RepeatRoutine>) {
        repeatRoutineList = list
        notifyDataSetChanged()
    }
}