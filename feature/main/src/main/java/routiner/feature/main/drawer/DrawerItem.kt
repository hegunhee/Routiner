package routiner.feature.main.drawer

import androidx.annotation.DrawableRes
import routiner.feature.daily.navigation.DAILY_ROUTE
import routiner.feature.record.navigation.RECORD_ROUTE
import com.hegunhee.repeat.navigation.REPEAT_ROUTINE_ROUTE
import com.hegunhee.setting.navigation.SETTING_ROUTE
import routiner.feature.main.R

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
