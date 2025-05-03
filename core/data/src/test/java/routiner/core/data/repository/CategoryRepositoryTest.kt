package routiner.core.data.repository

import routiner.core.data.datasource.local.DefaultLocalDataSource
import routiner.core.data.entity.CategoryEntity
import routiner.core.data.mapper.toCategoryEntity
import hegunhee.routiner.model.Category
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
    private lateinit var sut : DefaultCategoryRepository

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
            whenever(localDateSource.getCategoriesFlow()).thenReturn(categoryEntitiesByFlow)

            // when
            val categoriesByFlow = sut.getCategoriesFlow()

            // then
            assertThat(categoriesByFlow.first().size).isEqualTo(categoryEntitiesByFlow.first().size)
            verify(localDateSource).getCategoriesFlow()
        }
    }

    @Test
    fun givenCategory_whenDelete_thenWorksFine() {
        runBlocking {
            // given
            val category = Category("운동")
            whenever(localDateSource.deleteCategory(category.toCategoryEntity())).thenReturn(1)

            // when
            val deleteCount = sut.deleteCategory(category)

            // then
            assertThat(deleteCount).isEqualTo(listOf(category).size)
            verify(localDateSource).deleteCategory(category.toCategoryEntity())
        }
    }
}
