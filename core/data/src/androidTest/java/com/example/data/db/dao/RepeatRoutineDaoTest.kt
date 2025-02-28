package com.example.data.db.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.db.InMemoryDBProvider.provideInMemoryDB
import com.example.data.db.RoutinerDatabase
import com.example.data.entity.RepeatRoutineEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepeatRoutineDaoTest {

    private lateinit var sut: RepeatRoutineDao
    private lateinit var db: RoutinerDatabase

    @Before
    fun initDB() {
        db = provideInMemoryDB(ApplicationProvider.getApplicationContext())
        sut = db.repeatRoutineDao()
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
            val entity = createRepeatRoutineEntity("가슴운동")
            val previousCount = sut.getRepeatRoutines().size

            // when
            sut.insertRepeatRoutine(entity)
            val count = sut.getRepeatRoutines().size

            // then
            assertEquals(count, previousCount + 1)
        }
    }

    @Test
    fun givenDuplicateEntity_whenInserting_thenReplaceEntity() {
        runBlocking {
            // given
            val text = "가슴 운동"
            val changedCategory = "진짜 운동"
            val entity = createRepeatRoutineEntity(text, "운동")
            sut.insertRepeatRoutine(entity)

            // when
            sut.insertRepeatRoutine(entity.copy(category = changedCategory))
            val entities = sut.getRepeatRoutines()

            // then
            assertEquals(entities.first { it.text == text }.category, changedCategory)
        }
    }

    @Test
    fun givenEntity_whenDeleting_thenWorksFine() {
        runBlocking {
            // given
            val text = "가슴 운동"
            val entity = createRepeatRoutineEntity(text)
            sut.insertRepeatRoutine(entity)

            // when
            val deleteRepeatRoutineCount = sut.deleteRepeatRoutine(text)

            // then
            assertEquals(deleteRepeatRoutineCount, 1)
        }
    }

    private fun createRepeatRoutineEntity(
        text: String,
        category: String = "운동"
    ): RepeatRoutineEntity {
        return RepeatRoutineEntity(text, listOf("월"), category)
    }
}
