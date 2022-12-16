package com.hegunhee.repeat

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.ChipGroup
import com.hegunhee.common.util.addChip

@BindingAdapter("setDayOfWeekCategory")
fun ChipGroup.setDayOfWeekCategory(dayOfWeekList : List<String>){
    removeAllViews()
    addChipList(dayOfWeekList)
}

private fun ChipGroup.addChipList(list : List<String>){
    list.forEach{
        addChip(it)
    }
}