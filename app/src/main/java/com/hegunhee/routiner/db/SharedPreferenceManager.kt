package com.hegunhee.routiner.db

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(@ApplicationContext private val context : Context) {
    private val prefs = context.getSharedPreferences(PRES_NAME,Context.MODE_PRIVATE)

    fun getCurrentDate() : Int{
        return prefs.getInt(CURRENT_DATE_KEY, CURRENT_DEFAULT_DATE)
    }

    fun setCurrentDate(date : Int){
        prefs.edit().putInt(CURRENT_DATE_KEY,date).apply()
    }


    companion object{
        const val PRES_NAME = "routiner_pref"

        const val CURRENT_DATE_KEY = "currentDate"
        const val CURRENT_DEFAULT_DATE = -1


    }

}