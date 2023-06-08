package com.hegunhee.repeat

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.hegunhee.common.util.addChip

@BindingAdapter("setDayOfWeekCategory")
fun RecyclerView.setDayOfWeekCategory(dayOfWeekList : List<String>){
    this.adapter = RepeatRoutineDateAdapter().apply {
        submitList(dayOfWeekList)
    }
}