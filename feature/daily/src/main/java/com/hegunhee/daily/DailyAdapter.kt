package com.hegunhee.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.daily.databinding.ItemDailyBinding
import com.hegunhee.daily.databinding.ItemDailyContainerBinding

class DailyContainerAdapter(
    val eventHandler : DailyActionHandler,
) : ListAdapter<RoutineContainer,DailyContainerAdapter.DailyRoutineViewHolder>(diffUtil) {

    inner class DailyRoutineViewHolder(private val binding : ItemDailyContainerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(routineContainer : RoutineContainer) = with(binding) {
            this.dailyRecyclerView.adapter = DailyAdapter(this@DailyContainerAdapter.eventHandler).apply {
                submitList(routineContainer.routineList)
            }
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RoutineContainer>(){
            override fun areItemsTheSame(oldItem: RoutineContainer, newItem: RoutineContainer
            ): Boolean {
                return oldItem.routineList == newItem.routineList
            }

            override fun areContentsTheSame(oldItem: RoutineContainer, newItem: RoutineContainer
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyRoutineViewHolder {
        return DailyRoutineViewHolder(ItemDailyContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false).apply {
            eventHandler = this@DailyContainerAdapter.eventHandler
        })
    }

    override fun onBindViewHolder(holder: DailyRoutineViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}

data class RoutineContainer(
    val routineList : List<Routine>
)
class DailyAdapter(
    val eventHandler : DailyActionHandler,
) : ListAdapter<Routine,DailyAdapter.DailyViewHolder>(diffUtil) {

    inner class DailyViewHolder(private val binding : ItemDailyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindView(routine: Routine) = with(binding){
            binding.routine = routine
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(ItemDailyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            .apply { eventHandler = this@DailyAdapter.eventHandler }
        )
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Routine>(){
            override fun areItemsTheSame(oldItem: Routine, newItem: Routine): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Routine, newItem: Routine): Boolean {
                return oldItem == newItem
            }
        }
    }

}