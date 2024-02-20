package com.hegunhee.category

import com.example.domain.model.Category

interface CategoryActionHandler {

    fun onSelectCategory(categoryText : String, isCategorySelected : Boolean)

    fun onCategoryRemoveClick(category : Category)
}