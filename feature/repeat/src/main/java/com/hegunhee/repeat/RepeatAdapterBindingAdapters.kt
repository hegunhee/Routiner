package com.hegunhee.repeat

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setDayOfWeekCategory")
fun RecyclerView.setDayOfWeekCategory(dayOfWeekList : List<String>){
    this.adapter = RepeatRoutineDateAdapter().apply {
        submitList(dayOfWeekList)
    }
}