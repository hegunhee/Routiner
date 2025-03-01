package com.example.data.repository

import com.example.data.dataSource.local.DefaultLocalDataSource
import com.example.data.entity.RepeatRoutineEntity
import com.example.data.entity.RoutineEntity
import com.example.data.mapper.toRoutineEntity
import hegunhee.routiner.model.Routine
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
class RoutineRepositoryTest {

    @InjectMocks
    private lateinit var sut: DefaultRoutineRepository

    @Mock
    private lateinit var localDateSource: DefaultLocalDataSource

    private val date = 20250227

    @Test
    fun givenDayOfWeek_whenInsertAllRoutine_thenWorksFine() {
        runBlocking {
            // given
            val insertDataList : List<RepeatRoutineEntity> = listOf()
            val dayOfWeek = "월"
            whenever(localDateSource.getRepeatRoutines()).thenReturn(insertDataList)
            whenever(localDateSource.insertRoutines(listOf())).thenReturn(listOf())

            // when
            val insertRowIds = sut.insertRoutinesFromRepeatRoutineByDayOfWeek(dayOfWeek)

            // then
            assertThat(insertRowIds.size).isEqualTo(insertRowIds.size)
            verify(localDateSource).getRepeatRoutines()
            verify(localDateSource).insertRoutines(listOf())
        }
    }

    @Test
    fun givenRoutines_whenInsertAll_thenWorksFine() {
        runBlocking {
            // given
            val size = 5
            val routines = createRoutines(size, date)
            whenever(localDateSource.insertRoutines(routines.toRoutineEntity())).thenReturn(listOf(1,2,3,4,5))

            // when
            val insertRowIds = sut.insertRoutines(routines)

            // then
            assertThat(insertRowIds.size).isEqualTo(routines.size)
            verify(localDateSource).insertRoutines(routines.toRoutineEntity())

        }

    }

    @Test
    fun givenRoutine_whenInsert_thenWorksFine() {
        runBlocking {
            // given
            val routine = createRoutine(date, "text")
            whenever(localDateSource.insertRoutine(routine.toRoutineEntity())).thenReturn(Unit)

            // when
            sut.insertRoutine(routine)

            // then
            verify(localDateSource).insertRoutine(routine.toRoutineEntity())
        }
    }

    @Test
    fun givenDate_whenGetRoutinesFlow_thenWorksFine() {
        runBlocking {
            // given
            val size = 5
            val routineEntities = createRoutineEntities(size, date)
            whenever(localDateSource.getRoutinesFlowByDate(date)).thenReturn(flowOf(routineEntities))

            // when
            val routinesByFlow = sut.getRoutinesFlowByDate(date)

            // then
            assertThat(routinesByFlow.first().size).isEqualTo(size)
            assertThat(routinesByFlow.first().all { it.date == date }).isTrue()
            verify(localDateSource).getRoutinesFlowByDate(date)
        }
    }

    @Test
    fun givenDate_whenGetRoutines_thenWorksFine() {
        runBlocking {
            // given
            val size = 5
            val routineEntities = createRoutineEntities(size, date)
            whenever(localDateSource.getRoutinesByDate(date)).thenReturn(routineEntities)

            // when
            val routines = sut.getRoutinesByDate(date)

            // then
            assertThat(routines.size).isEqualTo(size)
            assertThat(routines.all { it.date == date }).isTrue()
            verify(localDateSource).getRoutinesByDate(date)
        }
    }

    @Test
    fun givenRoutine_whenUpdateRoutine_thenWorksFine() {
        runBlocking {
            // given
            val routine = createRoutine(date, "text")
            whenever(localDateSource.updateRoutine(routine.toRoutineEntity())).thenReturn(Unit)

            // when
            sut.updateRoutine(routine)

            // then
            verify(localDateSource).updateRoutine(routine.toRoutineEntity())
        }
    }

    @Test
    fun givenDate_whenDeleteRoutines_thenWorksFine() {
        runBlocking {
            // given
            val count = 3
            whenever(localDateSource.deleteRoutinesByDate(date)).thenReturn(count)

            // when
            val deleteCount = sut.deleteRoutinesByDate(date)

            // then
            assertThat(deleteCount).isEqualTo(count)
            verify(localDateSource).deleteRoutinesByDate(date)
        }
    }

    @Test
    fun givenRoutineId_whenDelete_thenWorksFine() {
        runBlocking {
            // given
            val routineId = 1
            whenever(localDateSource.deleteRoutine(routineId)).thenReturn(1)

            // when
            val deleteCount = sut.deleteRoutine(routineId)

            // then
            assertThat(deleteCount).isEqualTo(listOf(routineId).size)
            verify(localDateSource).deleteRoutine(routineId)
        }
    }

    private fun createRoutineEntities(size: Int, date: Int) : List<RoutineEntity> {
        return (0 until size).map { number ->
            createRoutineEntity(date, "$number text")
        }
    }
    private fun createRoutineEntity(date: Int, text: String): RoutineEntity {
        return RoutineEntity(date, text)
    }

    private fun createRoutines(size: Int, date: Int): List<Routine> {
        return (0 until size).map { number ->
            createRoutine(date, "$number text")
        }
    }

    private fun createRoutine(date: Int, text: String): Routine {
        return Routine(date, text)
    }
}
