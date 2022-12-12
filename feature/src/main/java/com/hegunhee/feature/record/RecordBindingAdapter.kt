package com.hegunhee.feature.record

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.hegunhee.feature.R

@BindingAdapter("isFinishRoutine")
fun View.setBackground(isFinished : Boolean){
    backgroundTintList = if(isFinished){
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.success_color))
    }else{
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.fail_color))
    }
}

@BindingAdapter("setCategory")
fun Chip.setCategory(categoryText : String){
    if(categoryText.isNotBlank()){
        visibility = View.VISIBLE
        text = categoryText
    }
}