package com.hegunhee.feature.daily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.feature.databinding.DailyItemBinding

class DailyAdapter(
    private var routineList : List<Routine>,
    val eventHandler : DailyActionHandler,
) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    inner class DailyViewHolder(private val binding : DailyItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(routine: Routine) = with(binding){
            title.text = routine.text
            deleteButton.setOnClickListener {
                eventHandler.deleteRoutine(routine.id)
            }
            check.visibility = if(routine.isFinished) View.VISIBLE else View.INVISIBLE
            title.setOnClickListener{
                eventHandler.toggleFinishRoutine((routine.copy(isFinished = !routine.isFinished)))
            }
            if(routine.category.isNotBlank()){
                categoryChip.visibility = View.VISIBLE
                categoryChip.text = routine.category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(DailyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bindView(routineList[position])
    }

    override fun getItemCount(): Int = routineList.size

    fun setRoutineList(routineList : List<Routine>){
        this.routineList = routineList
        notifyDataSetChanged()
    }

}