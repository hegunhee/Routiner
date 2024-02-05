package com.hegunhee.common.dayOfWeek

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DayOfWeek
import com.hegunhee.common.databinding.ItemDayOfWeekBinding

class DayOfWeekAdapter(private val actionHandler: DayOfWeekActionHandler) : ListAdapter<DayOfWeek, DayOfWeekAdapter.DayOfWeekViewHolder>(diffUtil){

    inner class DayOfWeekViewHolder(private val binding : ItemDayOfWeekBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(dayOfWeek : DayOfWeek) {
            binding.dayOfWeek = dayOfWeek
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayOfWeekViewHolder {
        return DayOfWeekViewHolder(ItemDayOfWeekBinding.inflate(LayoutInflater.from(parent.context),parent,false).apply {
            actionHandler = this@DayOfWeekAdapter.actionHandler
        })
    }

    override fun onBindViewHolder(holder: DayOfWeekViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<DayOfWeek>() {
            override fun areItemsTheSame(oldItem: DayOfWeek, newItem: DayOfWeek): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: DayOfWeek, newItem: DayOfWeek): Boolean {
                return oldItem == newItem
            }
        }
    }
}