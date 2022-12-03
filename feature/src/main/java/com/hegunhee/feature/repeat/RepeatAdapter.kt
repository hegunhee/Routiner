package com.hegunhee.feature.repeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RepeatRoutine
import com.hegunhee.feature.databinding.RepeatRecordItemBinding
import com.hegunhee.feature.util.addChip

class RepeatAdapter(
    private var repeatRoutineList: List<RepeatRoutine>,
    val eventHandler : RepeatActionHandler,
) : RecyclerView.Adapter<RepeatAdapter.RepeatViewHolder>() {

    inner class RepeatViewHolder(private val binding: RepeatRecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repeatRoutineEntity: RepeatRoutine) = with(binding) {
            eventHandler = this@RepeatAdapter.eventHandler
            repeatRoutine = repeatRoutineEntity
            title.text = repeatRoutineEntity.text
            chipGroup.removeAllViews()
            addChip(repeatRoutineEntity.dayOfWeekList)


//            chipGroup.setOnClickListener{
//                clickRoot(repeatRoutineEntity)
//            }

//            root.setOnClickListener{
//                clickRoot(repeatRoutineEntity)
//            }
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
        holder.bind(repeatRoutineList[position])
    }

    override fun getItemCount(): Int = repeatRoutineList.size

    fun setList(list: List<RepeatRoutine>) {
        repeatRoutineList = list
        notifyDataSetChanged()
    }
}