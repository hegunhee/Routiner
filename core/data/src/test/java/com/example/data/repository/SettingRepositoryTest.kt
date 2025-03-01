package com.example.data.repository

import com.example.data.dataSource.local.DefaultLocalDataSource
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SettingRepositoryTest {

    @InjectMocks
    private lateinit var sut: DefaultSettingRepository

    @Mock
    private lateinit var localDateSource: DefaultLocalDataSource

    @Test
    fun given_whenGetCurrentDate_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250228
            whenever(localDateSource.getCurrentDate()).thenReturn(date)

            // when
            val currentDate = sut.getCurrentDate()

            // then
            assertThat(currentDate).isEqualTo(date)
            verify(localDateSource).getCurrentDate()
        }
    }

    @Test
    fun givenDate_whenSetDate_thenWorksFine() {
        runBlocking {
            // given
            val date = 20250228
            whenever(localDateSource.setCurrentDate(date)).thenReturn(Unit)

            // when
            sut.setCurrentDate(date)

            // then
            verify(localDateSource).setCurrentDate(date)
        }
    }

    @Test
    fun given_whenGetAlarmNotiTime_thenWorksFine() {
        runBlocking {
            // given
            val time = "02:30"
            whenever(localDateSource.getAlarmNotiTime()).thenReturn(time)

            // when
            val alarmNotiTime = sut.getAlarmNotiTime()

            // then
            assertThat(alarmNotiTime.toTimeStamp()).isEqualTo(time)
            verify(localDateSource).getAlarmNotiTime()
        }
    }

    @Test
    fun givenNotiTime_whenSetNotiTime_thenWorksFine() {
        runBlocking {
            // given
            val time = "02:30"
            doNothing().`when`(localDateSource).setAlarmNotiTime(time)

            // when
            sut.setAlarmNotiTime(time)

            // then
            verify(localDateSource).setAlarmNotiTime(time)
        }
    }

    @Test
    fun given_whenGetIsAppFirstOpened_thenWorksFine() {
        runBlocking {
            // given
            val firstOpened = false
            whenever(localDateSource.isAppFirstOpened()).thenReturn(firstOpened)

            // when
            val appFirstOpened = sut.isAppFirstOpened()

            // then
            assertThat(appFirstOpened).isEqualTo(firstOpened)
            verify(localDateSource).isAppFirstOpened()
        }
    }

    @Test
    fun given_whenSetAppFirstOpened_thenWorksFine() {
        runBlocking {
            // given
            doNothing().`when`(localDateSource).setAppFirstOpened()

            // when
            sut.setAppFirstOpened()

            // then
            verify(localDateSource).setAppFirstOpened()
        }
    }
}
