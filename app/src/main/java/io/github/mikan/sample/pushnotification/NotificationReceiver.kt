package io.github.mikan.sample.pushnotification

import io.github.mikan.pushnotification.local.NotificationReceiver as BaseNotificationReceiver

class NotificationReceiver : BaseNotificationReceiver() {

    override fun getTargetActivity(): Class<*> = MainActivity::class.java
}