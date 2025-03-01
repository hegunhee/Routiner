package com.example.data.repository

import com.example.data.dataSource.local.DefaultLocalDataSource
import com.example.data.entity.DateEntity
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
class DateRepositoryTest {

    @InjectMocks
    private lateinit var sut: DefaultDateRepository

    @Mock
    private lateinit var localDateSource: DefaultLocalDataSource

    @Test
    fun givenDate_whenInsert_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250228
            val dateEntity = DateEntity(date)
            whenever(localDateSource.insertDate(dateEntity)).thenReturn(Unit)

            // when
            sut.insertDate(date)

            // then
            verify(localDateSource).insertDate(dateEntity)
        }
    }

    @Test
    fun given_whenGetAllDate_thenWorksFine() {
        runBlocking {
            // given
            val dateEntities = listOf(DateEntity(20250228), DateEntity(20250227))
            whenever(localDateSource.getDateList()).thenReturn(dateEntities)

            // when
            val dateList = sut.getAllDateList()

            // then
            assertThat(dateList.size).isEqualTo(dateEntities.size)
            verify(localDateSource).getDateList()
        }
    }
}
