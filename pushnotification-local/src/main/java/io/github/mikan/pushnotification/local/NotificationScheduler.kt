package io.github.mikan.pushnotification.local

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

object NotificationScheduler {

    fun scheduleNotification(
        context: Context,
        notificationId: Int,
        title: String,
        content: String,
        timeInMillis: Long,
        receiverClass: Class<*>
    ) {
        val intent = Intent(context, receiverClass).apply {
            putExtra("notification_id", notificationId)
            putExtra("title", title)
            putExtra("content", content)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
    }

    fun cancelNotification(context: Context, notificationId: Int, receiverClass: Class<*>) {
        val intent = Intent(context, receiverClass)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    fun scheduleRepeatingNotification(
        context: Context,
        notificationId: Int,
        title: String,
        content: String,
        timeInMillis: Long,
        intervalMillis: Long,
        receiverClass: Class<*>
    ) {
        val intent = Intent(context, receiverClass).apply {
            putExtra("notification_id", notificationId)
            putExtra("title", title)
            putExtra("content", content)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            intervalMillis,
            pendingIntent
        )
    }
}