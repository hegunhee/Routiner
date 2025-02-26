package com.example.data.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.data.db.SharedPreferenceManager
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SharedPreferenceManagerTest {

    private lateinit var sut: SharedPreferenceManager

    private lateinit var sharedPreference: SharedPreferences

    companion object {
        @BeforeClass
        @JvmStatic
        fun initPrefsName() {
            val companionObject = SharedPreferenceManager::class.java.getDeclaredField("PRES_NAME")
            companionObject.isAccessible = true
            companionObject.set(null, "test_prefs")
        }
    }


    @Before
    fun initPrefs() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        sut = SharedPreferenceManager(context)
        sharedPreference =
            context.getSharedPreferences(SharedPreferenceManager.PRES_NAME, Context.MODE_PRIVATE)
        sharedPreference.edit().clear().apply()
    }

    @After
    fun tearDown() {
        sharedPreference.edit().clear().apply()
    }

    @Test
    fun given_whenGetCurrentDate_thenReturnsDefaultDate() {
        // given

        // when
        val currentDate = sut.getCurrentDate()

        // then
        assertEquals(currentDate, SharedPreferenceManager.CURRENT_DATE_DEFAULT_DATE)
    }

    @Test
    fun givenDate_whenSetCurrentDate_thenReturnsSetDate() {
        // given
        val date = 20250226

        // when
        sut.setCurrentDate(date)
        val setDate = sut.getCurrentDate()

        // then
        assertEquals(setDate, date)

    }

    @Test
    fun given_whenGetAlarmNotiTime_thenReturnsDefaultTime() {
        // given

        // when
        val alarmNotiTime = sut.getAlarmNotiTime()

        // then
        assertEquals(alarmNotiTime, SharedPreferenceManager.ALARM_NOTI_DEFAULT)
    }

    @Test
    fun givenAlarmNotiTime_whenSetAlarmNotiTime_thenReturnsSetTime() {
        // given
        val notiTime = "02:30"

        // when
        sut.setAlarmNotiTime(notiTime)
        val setNotiTime = sut.getAlarmNotiTime()

        // then
        assertEquals(setNotiTime, notiTime)
    }

    @Test
    fun given_whenGetIsAppFirstOpen_thenDefaultBoolean() {
        // given

        // when
        val isAppFirstOpened = sut.isAppFirstOpened()

        // then
        assertTrue(isAppFirstOpened)
    }

    @Test
    fun given_whenSetIsAppFirstTime_thenReturnsFalse() {
        // given

        // when
        sut.setAppFirstOpened()
        val isAppFirstOpened = sut.isAppFirstOpened()

        // then
        assertFalse(isAppFirstOpened)
    }

}
