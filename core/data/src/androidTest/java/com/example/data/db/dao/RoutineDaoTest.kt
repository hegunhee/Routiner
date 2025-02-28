package com.example.data.db.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.db.InMemoryDBProvider.provideInMemoryDB
import com.example.data.db.RoutinerDatabase
import com.example.data.entity.RoutineEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoutineDaoTest {

    private lateinit var sut: RoutineDao
    private lateinit var db: RoutinerDatabase

    @Before
    fun initDB() {
        db = provideInMemoryDB(ApplicationProvider.getApplicationContext())
        sut = db.routineDao()
    }

    @Throws
    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun givenEntity_whenInserting_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250227
            val text = "text"
            val entity = createRoutineEntity(date, text)
            val previousCount = sut.getRoutineListByDate(date).size

            // when
            sut.insertRoutine(entity)
            val insertingEntities = sut.getRoutineListByDate(date)

            // then
            assertEquals(insertingEntities.size, previousCount + 1)
            assertTrue(insertingEntities.any { it.text == text })
        }
    }

    @Test
    fun givenEntities_whenInserting_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250227
            val size = 5
            val entities = createRoutineEntitiesWith(size, date)
            val previousCount = sut.getRoutineListByDate(date).size

            // when
            sut.insertAllRoutine(entities)
            val insertingEntities = sut.getRoutineListByDate(date)

            // then
            assertEquals(insertingEntities.size, previousCount + size)
        }
    }

    @Test
    fun givenDifferentDateEntities_whenGetByDate_thenFilterByDate() {
        runBlocking {
            // given
            val date1 = 20250227
            val size1 = 2
            val date2 = 20250225
            val size2 = 3

            val entities =
                createRoutineEntitiesWith(size1, date1) + createRoutineEntitiesWith(size2, date2)

            sut.insertAllRoutine(entities)

            // when
            val entitiesByDate = sut.getRoutineListByDate(date1)

            // then
            assertEquals(entitiesByDate.size, size1)
            assertTrue(entitiesByDate.all { it.date == date1 })
        }
    }

    @Test
    fun givenEntity_whenDeleting_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250227
            val text = "asdsd"
            val entity = createRoutineEntity(date, text)
            sut.insertRoutine(entity)
            val previousEntities = sut.getRoutineListByDate(date)

            // when
            sut.deleteRoutine(previousEntities.first { it.text == text }.id)
            val entities = sut.getRoutineListByDate(date)

            // then
            assertEquals(entities.size, previousEntities.size - 1)
            assertTrue(entities.none { it.text == text })
        }
    }

    @Test
    fun givenEntities_whenDeletingByDate_thenWorksFine() {
        runBlocking {
            // given
            val date1 = 20250227
            val size1 = 2
            val date2 = 20250225
            val size2 = 3

            val entities =
                createRoutineEntitiesWith(size1, date1) + createRoutineEntitiesWith(size2, date2)

            sut.insertAllRoutine(entities)

            // when
            sut.deleteAllRoutineByDate(date1)
            val deletedEntities = sut.getRoutineListByDate(date1)

            // then
            assertEquals(deletedEntities.size, 0)
        }
    }

    private fun createRoutineEntitiesWith(size: Int, date: Int): List<RoutineEntity> {
        return (0 until size).map { number ->
            createRoutineEntity(date, "$number text")
        }
    }

    private fun createRoutineEntity(date: Int, text: String): RoutineEntity {
        return RoutineEntity(date = date, text = text)
    }


}
