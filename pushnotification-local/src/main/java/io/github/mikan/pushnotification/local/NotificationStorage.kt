package io.github.mikan.pushnotification.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class StoredNotification(
    val id: Int,
    val title: String,
    val content: String,
    val scheduledTime: Long
)

class NotificationStorage private constructor(private val context: Context) {
    
    companion object {
        private const val PREFS_NAME = "notification_storage"
        private const val KEY_NOTIFICATIONS = "scheduled_notifications"
        private const val KEY_NEXT_ID = "next_notification_id"
        
        @Volatile
        private var INSTANCE: NotificationStorage? = null
        
        fun getInstance(context: Context): NotificationStorage {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: NotificationStorage(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
    
    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }
    
    fun saveNotifications(notifications: List<StoredNotification>) {
        val jsonString = json.encodeToString(notifications)
        preferences.edit {
            putString(KEY_NOTIFICATIONS, jsonString)
        }
    }
    
    fun loadNotifications(): List<StoredNotification> {
        val jsonString = preferences.getString(KEY_NOTIFICATIONS, null) ?: return emptyList()
        return try {
            json.decodeFromString<List<StoredNotification>>(jsonString)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun getNextNotificationId(): Int {
        val currentId = preferences.getInt(KEY_NEXT_ID, 1)
        preferences.edit {
            putInt(KEY_NEXT_ID, currentId + 1)
        }
        return currentId
    }
    
    fun addNotification(notification: StoredNotification) {
        val currentNotifications = loadNotifications().toMutableList()
        currentNotifications.add(notification)
        saveNotifications(currentNotifications)
    }
    
    fun removeNotification(notificationId: Int) {
        val currentNotifications = loadNotifications()
        val updatedNotifications = currentNotifications.filter { it.id != notificationId }
        saveNotifications(updatedNotifications)
    }
    
    fun clearAllNotifications() {
        preferences.edit {
            remove(KEY_NOTIFICATIONS)
        }
    }
}