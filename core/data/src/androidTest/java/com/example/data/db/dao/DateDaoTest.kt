package com.example.data.db.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.db.InMemoryDBProvider.provideInMemoryDB
import com.example.data.db.RoutinerDatabase
import com.example.data.entity.DateEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DateDaoTest {

    private lateinit var sut: DateDao
    private lateinit var db: RoutinerDatabase

    @Before
    fun initDB() {
        db = provideInMemoryDB(ApplicationProvider.getApplicationContext())
        sut = db.dateDao()
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
            val entity = DateEntity(20250227)
            val previousEntities = sut.getAllDateList()

            // when
            sut.insertDate(entity)
            val entities = sut.getAllDateList()

            // then
            assertEquals(entities.size, previousEntities.size + 1)
            assertEquals(entities[0], entity)
        }
    }
}
