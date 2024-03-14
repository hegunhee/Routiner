package com.hegunhee.daily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Routine
import com.hegunhee.daily.databinding.ItemDailyBinding
import com.hegunhee.daily.databinding.ItemDailyHeaderBinding
import java.lang.IllegalArgumentException

class DailyAdapter(
    val eventHandler : DailyActionHandler,
) : ListAdapter<DailyViewType,DailyAdapter.DailyAdapterViewHolder>(diffUtil) {

    sealed class DailyAdapterViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(data : DailyViewType)
    }

    inner class DailyHeaderViewHolder(private val binding : ItemDailyHeaderBinding) : DailyAdapterViewHolder(binding.root) {

        override fun bindView(data: DailyViewType) {

        }
    }

    inner class DailyViewHolder(private val binding : ItemDailyBinding) : DailyAdapterViewHolder(binding.root){

        override fun bindView(data: DailyViewType) {
            val item = data as DailyViewType.Item
            this.binding.routine = item.routine
            this.binding.eventHandler = eventHandler
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyAdapterViewHolder {
        return when(viewType) {
            R.layout.item_daily_header -> {
                DailyHeaderViewHolder(ItemDailyHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_daily -> {
                DailyViewHolder(ItemDailyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> { throw IllegalArgumentException()}
        }
    }

    override fun onBindViewHolder(holder: DailyAdapterViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            ITEM_VIEW_TYPE_HEADER -> { R.layout.item_daily_header}
            else -> { R.layout.item_daily }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<DailyViewType>(){
            override fun areItemsTheSame(oldItem: DailyViewType, newItem: DailyViewType): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(oldItem: DailyViewType, newItem: DailyViewType
            ): Boolean {
                return oldItem == newItem
            }
        }

        private const val ITEM_VIEW_TYPE_HEADER = 0
    }
}

sealed class DailyViewType {

    data class Header(val size : Int= 0) : DailyViewType()

    data class Item(
        val routine : Routine
    ) : DailyViewType()
}

fun List<Routine>.toViewTypeList(): List<DailyViewType> {
    return listOf<DailyViewType>(DailyViewType.Header()) + map { DailyViewType.Item(it) }
}