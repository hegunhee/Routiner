package com.example.main.drawer

import androidx.annotation.DrawableRes
import com.example.main.R
import com.hegunhee.daily.navigation.DAILY_ROUTE
import com.hegunhee.record.navigation.RECORD_ROUTE
import com.hegunhee.repeat.screen.navigation.REPEAT_ROUTINE_ROUTE
import com.hegunhee.setting.screen.navigation.SETTING_ROUTE

enum class DrawerItem(
    val titleString: String,
    @DrawableRes val iconRes: Int,
    val navRoute: String
) {
    Daily(
        titleString = "daily",
        iconRes = R.drawable.ic_daily,
        navRoute = DAILY_ROUTE,
    ),

    Record(
        titleString = "record",
        iconRes = R.drawable.ic_record,
        navRoute = RECORD_ROUTE
    ),

    Repeat(
        titleString = "repeat",
        iconRes = R.drawable.ic_repeat,
        navRoute = REPEAT_ROUTINE_ROUTE,
    ),

    Setting(
        titleString = "setting",
        iconRes = R.drawable.ic_setting,
        navRoute = SETTING_ROUTE
    );

    companion object {
        fun ofOrNull(route: String): DrawerItem? {
            return entries.firstOrNull { it.navRoute == route }
        }
    }
}
