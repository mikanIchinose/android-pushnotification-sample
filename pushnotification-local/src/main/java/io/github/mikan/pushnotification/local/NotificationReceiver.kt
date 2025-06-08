package io.github.mikan.pushnotification.local

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

open class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationId = intent.getIntExtra("notification_id", 0)
        val title = intent.getStringExtra("title") ?: "通知"
        val content = intent.getStringExtra("content") ?: "新しい通知があります"

        context.createNotificationChannel()

        // 通知をタップした時に開くActivityを取得
        getTargetActivity()?.let {
            context.showNotification(notificationId, title, content, it)
        }
    }

    // オーバーライド可能なメソッド：通知タップ時に開くActivityを指定
    protected open fun getTargetActivity(): Class<*>? = null
}