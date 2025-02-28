package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category")
    fun getAllCategoryListByFlow() : Flow<List<CategoryEntity>>

    @Delete
    suspend fun removeCategory(categoryEntity : CategoryEntity)
}