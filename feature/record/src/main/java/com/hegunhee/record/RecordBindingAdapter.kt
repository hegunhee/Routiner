package com.hegunhee.record

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.hegunhee.common.R

@BindingAdapter("isFinishRoutine")
fun View.setBackground(isFinished : Boolean){
    backgroundTintList = if(isFinished){
        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.success_color))
    }else{
        ColorStateList.valueOf(ContextCompat.getColor(context,R.color.success_color))
    }
}
