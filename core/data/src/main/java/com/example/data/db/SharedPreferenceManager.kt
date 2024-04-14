package com.example.data.db

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceManager @Inject constructor(@ApplicationContext private val context : Context) {
    private val prefs = context.getSharedPreferences(PRES_NAME,Context.MODE_PRIVATE)

    fun getCurrentDate() : Int{
        return prefs.getInt(CURRENT_DATE_KEY, CURRENT_DATE_DEFAULT_DATE)
    }

    fun setCurrentDate(date : Int){
        prefs.edit().putInt(CURRENT_DATE_KEY,date).apply()
    }

    fun getAlarmNotiTime() : String{
        return prefs.getString(ALARM_NOTI_KEY, ALARM_NOTI_DEFAULT) ?: ALARM_NOTI_DEFAULT
    }

    fun setAlarmNotiTime(time : String){
        prefs.edit().putString(ALARM_NOTI_KEY,time).apply()
    }

    fun isAppFirstOpened() : Boolean {
        return prefs.getBoolean(FIRST_APP_ENTER_KEY,FIRST_APP_ENTER_KEY_DEFAULT)
    }

    fun setAppFirstOpened() {
        prefs.edit().putBoolean(FIRST_APP_ENTER_KEY,false).apply()
    }


    companion object{
        const val PRES_NAME = "routiner_pref"

        const val CURRENT_DATE_KEY = "currentDate"
        const val CURRENT_DATE_DEFAULT_DATE = -1

        const val ALARM_NOTI_KEY = "ALARM_NOTI"
        const val ALARM_NOTI_DEFAULT = ""

        const val FIRST_APP_ENTER_KEY = "firstAppEnterKey"
        const val FIRST_APP_ENTER_KEY_DEFAULT = true

    }

}