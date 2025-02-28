package com.example.data.repository

import com.example.data.dataSource.local.DefaultLocalDataSource
import com.example.data.entity.CategoryEntity
import com.example.data.mapper.toCategoryEntity
import com.example.domain.model.Category
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryTest {

    @InjectMocks
    private lateinit var sut : DefaultRepository

    @Mock
    private lateinit var localDateSource: DefaultLocalDataSource

    @Test
    fun givenCategory_whenInsert_thenWorksFine() {
        runBlocking {
            // given
            val category = Category("운동")
            whenever(localDateSource.insertCategory(category.toCategoryEntity())).thenReturn(Unit)

            // when
            sut.insertCategory(category)

            // then
            verify(localDateSource).insertCategory(category.toCategoryEntity())

        }

    }

    @Test
    fun given_whenGetAllCategoryByFlow_thenWorksFine() {
        runBlocking {
            // given
            val categoryEntitiesByFlow = flowOf(listOf(CategoryEntity("운동")))
            whenever(localDateSource.getAllCategoryListByFlow()).thenReturn(categoryEntitiesByFlow)

            // when
            val categoriesByFlow = sut.getAllCategoryListByFlow()

            // then
            assertThat(categoriesByFlow.first().size).isEqualTo(categoryEntitiesByFlow.first().size)
            verify(localDateSource).getAllCategoryListByFlow()
        }
    }

    @Test
    fun givenCategory_whenDelete_thenWorksFine() {
        runBlocking {
            // given
            val category = Category("운동")
            whenever(localDateSource.deleteCategory(category.toCategoryEntity())).thenReturn(Unit)

            // when
            sut.deleteCategory(category)

            // then
            verify(localDateSource).deleteCategory(category.toCategoryEntity())
        }
    }
}
