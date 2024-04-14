package com.hegunhee.setting.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.domain.model.Routine
import com.example.domain.usecase.routine.GetRoutineListByDateUseCase
import com.example.main.MainActivity
import com.hegunhee.routiner.util.getTodayDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver() : BroadcastReceiver() {

    @Inject
    lateinit var getRoutineListByDateUseCase: GetRoutineListByDateUseCase

    private lateinit var notificationManager : NotificationManager

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createAlarmNotificationChannel()
        CoroutineScope(Dispatchers.Default).launch {
            val routine = getRoutineListByDateUseCase(getTodayDate())
            sendDailyAlarmNotification(context,getRoutineCurrentText(routine))
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

    private fun sendDailyAlarmNotification(context : Context,text : String) {
            val contentIntent = Intent(context,MainActivity::class.java)
            val contentPendingIntent = PendingIntent.getActivity(
                context,
                ALARM_NOTIFICATION_ID,
                contentIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder = NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
                .setSmallIcon(com.hegunhee.common.R.drawable.ic_check)
                .setContentTitle("오늘의 루틴")
                .setContentText(text)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            notificationManager.notify(ALARM_NOTIFICATION_ID,builder.build())
    }

    private fun getRoutineCurrentText(routineList : List<Routine>) : String{
        return if(routineList.isEmpty()) {
            "오늘의 루틴을 입력해주세요"
        }else if(routineList.any { !it.isFinished }) {
            "${routineList.size - routineList.count { it.isFinished }}개의 루틴이 남았습니다."
        }else {
            "모든 루틴을 완료했습니다!"
        }
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