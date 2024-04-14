package com.hegunhee.setting.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    private lateinit var notificationManager : NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createAlarmNotificationChannel()
        CoroutineScope(Dispatchers.Default).launch {
            sendDailyAlarmNotification(context)
        }
    }

    private fun createAlarmNotificationChannel() {
        NotificationChannel(
            ALARM_CHANNEL_ID,
            "알람 채널",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "매일 울리는 알람 채널"
            notificationManager.createNotificationChannel(this)
        }
    }

    private fun sendDailyAlarmNotification(context : Context) {
            val contentIntent = Intent(context,MainActivity::class.java)
            val contentPendingIntent = PendingIntent.getActivity(
                context,
                ALARM_NOTIFICATION_ID,
                contentIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder = NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
                .setSmallIcon(com.hegunhee.common.R.drawable.ic_check)
                .setContentTitle(" ")
                .setContentText(" ")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            notificationManager.notify(ALARM_NOTIFICATION_ID,builder.build())
    }

    companion object {
        const val DAILY_ALARM_PENDING_INTENT_FLAG = 1

        const val ALARM_CHANNEL_ID = "ALARM_CHANNEL"

        const val ALARM_NOTIFICATION_ID = 1

        private fun getDailyAlarmIntent(context : Context) : Intent {
            return Intent(context,AlarmReceiver::class.java)
        }

        fun getAlarmPendingIntent(context : Context,pendingIntentPlag : Int) : PendingIntent {
            return getDailyAlarmIntent(context).let { intent ->
                PendingIntent.getBroadcast(context, pendingIntentPlag,intent,PendingIntent.FLAG_IMMUTABLE)
            }
        }
    }
}