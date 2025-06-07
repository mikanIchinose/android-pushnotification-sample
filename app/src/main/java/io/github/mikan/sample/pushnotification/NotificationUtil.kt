package io.github.mikan.sample.pushnotification

import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.content.ContextWrapper
import androidx.core.app.NotificationCompat

val ContextWrapper.notificationManager
    get() = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

inline fun ContextWrapper.notifyWithBuilder(
    notificationId: Int,
    channelId: String,
    block: NotificationCompat.Builder.() -> Unit,
) {
    val builder = NotificationCompat.Builder(this, channelId).apply(block)
    notificationManager.notify(notificationId, builder.build())
}
