package com.hegunhee.repeat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hegunhee.repeat.databinding.ItemRoutineDateBinding

class RepeatRoutineDateAdapter() : ListAdapter<String, RepeatRoutineDateAdapter.DateViewHolder>(diff_util) {

    inner class DateViewHolder(private val binding : ItemRoutineDateBinding) : ViewHolder(binding.root) {

        fun bindView(date : String) {
            binding.date = date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(ItemRoutineDateBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {
        val diff_util = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}