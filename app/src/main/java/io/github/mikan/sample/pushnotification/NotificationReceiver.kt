package io.github.mikan.sample.pushnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {
    
    override fun onReceive(context: Context, intent: Intent) {
        val notificationId = intent.getIntExtra("notification_id", 0)
        val title = intent.getStringExtra("title") ?: "通知"
        val content = intent.getStringExtra("content") ?: "新しい通知があります"
        
        NotificationHelper.createNotificationChannel(context)
        NotificationHelper.showNotification(context, notificationId, title, content)
    }
}