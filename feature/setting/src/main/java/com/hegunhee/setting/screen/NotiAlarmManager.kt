package com.hegunhee.setting.screen

import android.app.AlarmManager
import android.content.Context
import android.widget.Toast
import com.hegunhee.setting.alarm.AlarmReceiver
import java.time.LocalDateTime
import java.time.ZoneId

fun Context.registerAlarm(hour: Int, minute: Int) {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val pendingIntent = AlarmReceiver.getAlarmPendingIntent(this, AlarmReceiver.DAILY_ALARM_PENDING_INTENT_FLAG)

    Toast.makeText(this, "${hour}시 ${minute}분에 알람이 지정되었습니다.", Toast.LENGTH_SHORT).show()
    alarmManager.setRepeating(
        AlarmManager.RTC,
        toTimeMillis(hour = hour, minute = minute),
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )
}

fun Context.cancelAlarm() {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val pendingIntent = AlarmReceiver.getAlarmPendingIntent(this, AlarmReceiver.DAILY_ALARM_PENDING_INTENT_FLAG)

    alarmManager.cancel(pendingIntent)
    Toast.makeText(this, "알람이 취소되었습니다.", Toast.LENGTH_SHORT).show()
}

fun toTimeMillis(hour: Int, minute: Int): Long {
    val now = LocalDateTime.now()
    val targetTime = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0)

    return targetTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
