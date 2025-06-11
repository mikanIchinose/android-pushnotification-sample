package io.github.mikan.pushnotification.local

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

private const val CHANNEL_ID = "local_notification_channel"
private const val CHANNEL_NAME = "ローカル通知"
private const val CHANNEL_DESCRIPTION = "スケジュールされたローカル通知"

val Context.notificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.showNotification(
    notificationId: Int,
    title: String,
    content: String,
    targetActivity: Class<*>
) {
    val intent = Intent(this, targetActivity)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    val pendingIntent = PendingIntent.getActivity(
        this,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    notify(CHANNEL_ID, notificationId) {
        setSmallIcon(R.drawable.ic_notification)
        setContentTitle(title)
        setContentText(content)
        setStyle(NotificationCompat.BigTextStyle().bigText(content))
        setPriority(NotificationCompat.PRIORITY_HIGH)
        setContentIntent(pendingIntent)
        setAutoCancel(true)
    }
}

fun Context.createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = CHANNEL_DESCRIPTION
            enableVibration(true)
            enableLights(true)
        }

        notificationManager.createNotificationChannel(channel)
    }
}

fun Context.notify(channelId: String, notificationId: Int, action: NotificationCompat.Builder.() -> Unit) {
    val notification = buildNotification(channelId, action)
    notificationManager.notify(notificationId, notification)
}

private fun Context.buildNotification(channelId: String, action: NotificationCompat.Builder.() -> Unit): Notification =
    NotificationCompat.Builder(this, channelId).apply(action).build()
