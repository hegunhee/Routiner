package com.example.main.drawer

import androidx.annotation.DrawableRes
import com.example.main.R
import com.hegunhee.daily.screen.navigation.DAILY_ROUTE

enum class DrawerItem(
    val titleString: String,
    @DrawableRes val iconRes: Int,
    val navRoute: String
) {
    Daily(
        titleString = "daily",
        iconRes = R.drawable.ic_daily,
        navRoute = DAILY_ROUTE,
    );

    companion object {
        fun ofOrNull(route: String): DrawerItem? {
            return entries.firstOrNull { it.navRoute == route }
        }
    }
}
