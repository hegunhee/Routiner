package com.hegunhee.feature.daily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.feature.R
import com.hegunhee.feature.databinding.DailyItemBinding

class DailyAdapter(
    private var routineList : List<Routine>,
    val eventHandler : DailyActionHandler,
) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    inner class DailyViewHolder(private val binding : DailyItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(routine: Routine) = with(binding){
            binding.routine = routine
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(DailyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            .apply { eventHandler = this@DailyAdapter.eventHandler }
        )
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