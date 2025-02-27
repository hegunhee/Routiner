package com.example.data.db.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.db.InMemoryDBProvider.provideInMemoryDB
import com.example.data.db.RoutinerDatabase
import com.example.data.entity.CategoryEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest {

    private lateinit var sut: CategoryDao
    private lateinit var db: RoutinerDatabase

    @Before
    fun initDB() {
        db = provideInMemoryDB(ApplicationProvider.getApplicationContext())
        sut = db.categoryDao()
    }

    @Throws
    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun givenCategoryEntity_whenInsertEntity_thenWorksFine() {
        runBlocking {
            // given
            val entity = CategoryEntity("운동")

            // when
            sut.insertCategory(entity)
            val insertedEntity = sut.getAllCategoryListByFlow().first()[0]

            // then
            assertEquals(insertedEntity, entity)
        }
    }

    @Test
    fun givenCategoryEntity_whenInsertSecond_thenIgnoreSecondInsert() {
        runBlocking {
            // given
            val entity = CategoryEntity("운동")
            val previousCount = sut.getAllCategoryListByFlow().first().size

            // when
            sut.insertCategory(entity)
            sut.insertCategory(entity)
            val entities =
                sut.getAllCategoryListByFlow().first().filter { it.name == entity.name }

            // then
            assertEquals(entities.size, previousCount + 1)
        }
    }

    @Test
    fun givenCategoryEntity_whenRemoveEntity_thenWorksFine() {
        runBlocking {
            // given
            val entity = CategoryEntity("운동")
            sut.insertCategory(entity)
            val previousCount = sut.getAllCategoryListByFlow().first().size

            // when
            sut.removeCategory(entity)
            val count = sut.getAllCategoryListByFlow().first().size

            // then
            assertEquals(count, previousCount - 1)
        }
    }

    @Test
    fun givenTwoEntities_whenDeleteEntity_thenWorksFine() {
        runBlocking {
            // given
            val previousEntities = listOf(CategoryEntity("운동"), CategoryEntity("게임"))
            previousEntities.forEach {
                sut.insertCategory(it)
            }
            val entitiesFlow = sut.getAllCategoryListByFlow()

            // when
            sut.removeCategory(previousEntities[0])
            val entities = entitiesFlow.first()

            // then
            assertEquals(entities.size, previousEntities.size - 1)
            assertEquals(entities[0],previousEntities.first { it.name == entities[0].name })
        }
    }
}
