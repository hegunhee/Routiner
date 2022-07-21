package com.hegunhee.routiner.util

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.hegunhee.routiner.R

fun Chip.setRepeatDefaultColor() {
    chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.teal_200))
}