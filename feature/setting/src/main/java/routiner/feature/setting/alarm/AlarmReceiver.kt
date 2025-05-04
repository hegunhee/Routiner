package routiner.feature.setting.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import routiner.core.model.Routine
import routiner.core.domain.usecase.routine.GetRoutinesByDateUseCase
import routiner.core.util.getTodayDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import routiner.feature.setting.R
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver() : BroadcastReceiver() {

    @Inject
    lateinit var getRoutinesByDateUseCase: GetRoutinesByDateUseCase

    private lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createAlarmNotificationChannel(context)
        CoroutineScope(Dispatchers.Default).launch {
            val routine = getRoutinesByDateUseCase(getTodayDate())
            sendDailyAlarmNotification(context, getRoutineCurrentText(routine, context))
        }
    }

    private fun createAlarmNotificationChannel(context: Context) {
        NotificationChannel(
            ALARM_CHANNEL_ID,
            context.getString(R.string.alarm_channel_title),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = context.getString(R.string.toady_alarm_channel_title)
            notificationManager.createNotificationChannel(this)
        }
    }

    private fun sendDailyAlarmNotification(context: Context, text: String) {
        val contentIntent = Intent(Intent.ACTION_VIEW, Uri.parse("routiner://main"))
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            ALARM_NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val builder = NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_check)
            .setContentTitle(context.getString(R.string.today_routine_title))
            .setContentText(text)
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        notificationManager.notify(ALARM_NOTIFICATION_ID, builder.build())
    }

    private fun getRoutineCurrentText(routineList: List<Routine>, context: Context): String {
        return if (routineList.isEmpty()) {
            context.getString(R.string.enter_today_routine)
        } else if (routineList.any { !it.isFinished }) {
            context.getString(
                R.string.today_routine_count,
                (routineList.size - routineList.count { it.isFinished }).toString()
            )
        } else {
            context.getString(R.string.today_routine_finish)
        }
    }

    companion object {
        const val DAILY_ALARM_PENDING_INTENT_FLAG = 1

        const val ALARM_CHANNEL_ID = "ALARM_CHANNEL"

        const val ALARM_NOTIFICATION_ID = 1

        private fun getDailyAlarmIntent(context: Context): Intent {
            return Intent(context, AlarmReceiver::class.java)
        }

        fun getAlarmPendingIntent(context: Context, pendingIntentPlag: Int): PendingIntent {
            return getDailyAlarmIntent(context).let { intent ->
                PendingIntent.getBroadcast(
                    context,
                    pendingIntentPlag,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            }
        }
    }
}