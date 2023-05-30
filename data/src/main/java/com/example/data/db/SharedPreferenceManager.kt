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

    fun getNotiSendValue() : Boolean{
        return prefs.getBoolean(CURRENT_NOTI_KEY, CURRENT_NOTI_DEFAULT)
    }

    fun setNotiSendValue(notiValue : Boolean){
        prefs.edit().putBoolean(CURRENT_NOTI_KEY,notiValue).apply()
    }


    companion object{
        const val PRES_NAME = "routiner_pref"

        const val CURRENT_DATE_KEY = "currentDate"
        const val CURRENT_DATE_DEFAULT_DATE = -1

        const val CURRENT_NOTI_KEY = "notiKey"
        const val CURRENT_NOTI_DEFAULT = false

        const val FIRST_APP_ENTER_KEY = "firstAppEnterKey"
        const val FIRST_APP_ENTER_KEY_DEFAULT = false

    }

}