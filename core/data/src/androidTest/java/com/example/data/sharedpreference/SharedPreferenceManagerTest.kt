package com.example.data.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.data.db.SharedPreferenceManager
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
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
        sharedPreference = context.getSharedPreferences(SharedPreferenceManager.PRES_NAME, Context.MODE_PRIVATE)
        sharedPreference.edit().clear().apply()
    }

    @After
    fun tearDown() {
        sharedPreference.edit().clear().apply()
    }

}
