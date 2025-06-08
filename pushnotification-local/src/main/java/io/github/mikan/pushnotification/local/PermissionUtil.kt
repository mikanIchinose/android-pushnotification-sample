package io.github.mikan.pushnotification.local

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1001

/**
 * 通知権限の有無を確認する
 * Android 13以降では明示的な権限が必要、それ以前は自動的に付与される
 */
fun Context.hasNotificationPermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

/**
 * 通知権限をユーザーにリクエストする
 * Android 13以降でのみ権限ダイアログを表示
 */
fun Activity.requestNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            NOTIFICATION_PERMISSION_REQUEST_CODE
        )
    }
}

/**
 * 通知権限の説明表示が必要かを判定する
 * ユーザーが一度権限を拒否した場合に説明UIを表示するかの判断に使用
 */
fun Activity.shouldShowNotificationPermissionRationale(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        )
    } else {
        false
    }
}

/**
 * アプリの通知設定画面を開く
 * 権限が永続的に拒否された場合に設定画面に誘導するために使用
 */
fun Context.openNotificationSettings() {
    val intent = Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.fromParts("package", packageName, null)
    }
    startActivity(intent)
}

/**
 * 正確なアラーム設定権限の有無を確認する
 * Android 12以降でスケジュール通知に必要な権限
 */
fun Context.canScheduleExactAlarms(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.canScheduleExactAlarms()
    } else {
        true
    }
}

/**
 * 正確なアラーム権限の設定画面を開く
 * Android 12以降で手動で権限を有効化する必要がある
 */
fun Context.requestExactAlarmPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            data = "package:${packageName}".toUri()
        }
        startActivity(intent)
    }
}