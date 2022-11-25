package com.hegunhee.feature.daily

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("setCategory")
fun Chip.setCategory(category : String){
    if(category.isNotBlank()){
        visibility = View.VISIBLE
        text = category
    }
}