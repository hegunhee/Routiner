package com.example.common.util

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.example.common.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun Chip.setRoutineDefaultColor() {
    chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.teal_200))
}

fun ChipGroup.addCheckableChip(text : String) {
    addView(Chip(context).apply {
        this.text = text
        setRoutineDefaultColor()
        isCheckable = true

    })
}

fun ChipGroup.addChip(text : String){
    addView(Chip(context).apply {
        this.text = text
        setRoutineDefaultColor()
    })
}
