package com.example.data.repository

import com.example.data.dataSource.local.DefaultLocalDataSource
import com.example.data.entity.RepeatRoutineEntity
import com.example.data.mapper.toRepeatRoutineEntity
import com.example.domain.model.RepeatRoutine
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
class RepeatRoutineRepositoryTest {

    @InjectMocks
    private lateinit var sut: DefaultRepository

    @Mock
    private lateinit var localDateSource: DefaultLocalDataSource

    @Test
    fun givenRepeatRoutine_whenInsert_thenWorksFine() {
        runBlocking {
            // given
            val repeatRoutine = RepeatRoutine(dayOfWeekList = listOf("월"))
            whenever(localDateSource.insertRepeatRoutine(repeatRoutine.toRepeatRoutineEntity())).thenReturn(Unit)

            // when
            sut.insertRepeatRoutine(RepeatRoutine(dayOfWeekList = listOf("월")))

            // then
            verify(localDateSource).insertRepeatRoutine(repeatRoutine.toRepeatRoutineEntity())
        }
    }

    @Test
    fun given_whenGetAllRepeatRoutinesByFlow_thenWorksFine() {
        runBlocking {
            // given
            val repeatRoutineEntitiesByFlow = flowOf(listOf(RepeatRoutineEntity(dayOfWeekList = listOf("월"))))
            whenever(localDateSource.getRepeatRoutinesFlow()).thenReturn(repeatRoutineEntitiesByFlow)

            // when
            val repeatRoutinesByFlow = sut.getAllRepeatRoutineListByFlow()

            // then
            assertThat(repeatRoutinesByFlow.first().size).isEqualTo(repeatRoutineEntitiesByFlow.first().size)
            verify(localDateSource).getRepeatRoutinesFlow()
        }
    }

    @Test
    fun given_whenGetAllRepeatRoutines_thenWorksFine() {
        runBlocking {
            // given
            val repeatRoutineEntities = listOf(RepeatRoutineEntity(dayOfWeekList = listOf("월")))
            whenever(localDateSource.getRepeatRoutines()).thenReturn(repeatRoutineEntities)

            // when
            val repeatRoutines = sut.getAllRepeatRoutineList()

            // then
            assertThat(repeatRoutines.size).isEqualTo(repeatRoutineEntities.size)
            verify(localDateSource).getRepeatRoutines()
        }
    }

    @Test
    fun givenText_whenDelete_thenWorksFine() {
        runBlocking {
            // given
            val text = ""
            whenever(localDateSource.deleteRepeatRoutine(text)).thenReturn(1)

            // when
            sut.deleteRepeatRoutine(text)

            // then
            verify(localDateSource).deleteRepeatRoutine(text)
        }
    }

}
