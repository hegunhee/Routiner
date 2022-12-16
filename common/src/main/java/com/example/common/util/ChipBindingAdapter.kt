package com.example.common.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("setCategory")
fun Chip.setCategory(categoryText : String){
    if(categoryText.isNotBlank()){
        visibility = View.VISIBLE
        text = categoryText
    }
}