package io.github.mikan.pushnotification.local

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

val Context.notificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.notify(channelId: String, notificationId: Int, action: NotificationCompat.Builder.() -> Unit) {
    val notification = buildNotification(channelId, action)
    notificationManager.notify(notificationId, notification)
}

private fun Context.buildNotification(channelId: String, action: NotificationCompat.Builder.() -> Unit): Notification =
    NotificationCompat.Builder(this, channelId).apply(action).build()
