package com.hegunhee.record.dateSelector


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Date
import com.hegunhee.record.databinding.ItemDateSelectorBinding


class DateSelectorAdapter(
    private val eventHandler : DateSelectorActionHandler
) : ListAdapter<Date,DateSelectorAdapter.DateSelectorViewHolder>(diffUtil){

    inner class DateSelectorViewHolder(private val binding : ItemDateSelectorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(date : Date) = with(binding) {
            yearDesc.text = date.year
            dayDesc.text = "${date.month}/${date.dayOfMonth} (${date.dayOfWeek})"
            when(date.dayOfWeek) {
                "토" -> {
                    yearDesc.setTextColor(Color.BLUE)
                    dayDesc.setTextColor(Color.BLUE)
                }
                "일" -> {
                    yearDesc.setTextColor(Color.RED)
                    dayDesc.setTextColor(Color.RED)
                }
                else -> {
                    yearDesc.setTextColor(Color.WHITE)
                    dayDesc.setTextColor(Color.WHITE)
                }
            }
            if(date.isSelected) {
                selectDot.visibility = View.VISIBLE
            }else {
                selectDot.visibility = View.INVISIBLE
            }
            root.setOnClickListener {
                eventHandler.onDateSelectorClick(date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateSelectorViewHolder {
        return DateSelectorViewHolder(ItemDateSelectorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DateSelectorViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Date>() {
            override fun areItemsTheSame(oldItem: Date, newItem: Date): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: Date, newItem: Date): Boolean {
                return oldItem == newItem
            }
        }
    }
}

