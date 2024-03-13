package com.hegunhee.common.progressIndicator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hegunhee.common.databinding.ItemProgressIndicatorBinding

class ProgressIndicatorAdapter() : ListAdapter<ProgressIndicatorContainer,ProgressIndicatorAdapter.ProgressIndicatorViewHolder>(diffUtil) {

    inner class ProgressIndicatorViewHolder(private val binding : ItemProgressIndicatorBinding) : ViewHolder(binding.root) {

        fun bindView(progressIndicatorContainer: ProgressIndicatorContainer) = with(binding) {
            this.progressTitle.text = "작업을 ${progressIndicatorContainer.percent}% 완료했습니다"
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ProgressIndicatorContainer>() {
            override fun areItemsTheSame(oldItem: ProgressIndicatorContainer, newItem: ProgressIndicatorContainer): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProgressIndicatorContainer, newItem: ProgressIndicatorContainer): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressIndicatorViewHolder {
        return ProgressIndicatorViewHolder(ItemProgressIndicatorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProgressIndicatorViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}


data class ProgressIndicatorContainer(
    val finishedRoutine : Int,
    val totalRoutine : Int,
    val percent : Int = if(totalRoutine == 0) 0 else {
        ((finishedRoutine.toFloat()/totalRoutine.toFloat()) * 100).toInt()
    }
)