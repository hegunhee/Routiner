package com.hegunhee.category

import hegunhee.routiner.model.Category

interface CategoryActionHandler {

    fun onSelectCategory(categoryText : String, isCategorySelected : Boolean)

    fun onCategoryRemoveClick(category : Category)
}