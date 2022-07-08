package com.hegunhee.routiner.ui.repeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.hegunhee.routiner.data.entity.RepeatRoutine
import com.hegunhee.routiner.databinding.RepeatRecordItemBinding

class RepeatAdapter(
    private var repeatRoutineList: List<RepeatRoutine>,
    val clickRoot : (RepeatRoutine) -> Unit
) : RecyclerView.Adapter<RepeatAdapter.RepeatViewHolder>() {

    inner class RepeatViewHolder(private val binding: RepeatRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repeatRoutine: RepeatRoutine) = with(binding) {
            title.text = repeatRoutine.text
            setVisible(repeatRoutine.dayOfWeekList)
            root.setOnClickListener{
                clickRoot(repeatRoutine)
            }
        }

        private fun setVisible(list : List<String>){
            binding.run {
                val chipList = arrayOf<Chip>(mondayChip,tuesdayChip,wednesdayChip,thursdayChip,fridayChip,saturdayChip,sundayChip)
                for(chip in chipList){
                    if(chip.text.toString() in list){
                        chip.visibility = View.VISIBLE
                    }else{
                        chip.visibility = View.GONE
                    }
                }
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