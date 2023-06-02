package com.hegunhee.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Category
import com.hegunhee.category.databinding.ItemCategoryBinding

class CategoryAdapter(private val actionHandler: CategoryActionHandler) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(diff_util){

    inner class CategoryViewHolder(private val binding : ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(category : Category) {
            binding.category = category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false).apply {
            actionHandler = this@CategoryAdapter.actionHandler
        })
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    companion object {
        val diff_util = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }

        }
    }
}