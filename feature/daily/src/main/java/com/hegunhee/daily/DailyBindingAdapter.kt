package com.hegunhee.daily

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("setCategory")
fun Chip.setCategory(category : String){
    this.visibility = View.INVISIBLE
    if(category.isNotBlank()){
        visibility = View.VISIBLE
        text = category
    }
}