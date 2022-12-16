package com.hegunhee.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.daily.databinding.DailyItemBinding

class DailyAdapter(
    val eventHandler : DailyActionHandler,
) : ListAdapter<Routine,DailyAdapter.DailyViewHolder>(diff_util) {

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