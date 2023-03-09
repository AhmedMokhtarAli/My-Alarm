package com.example.myalarm

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReciver : BroadcastReceiver() {
    private val NOTIFICATION_ID=150

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationUtils=NotificationUtils(context!!)
        val notification=notificationUtils.getNotifcationBuilder().build()
        notificationUtils.getManeger().notify(NOTIFICATION_ID,notification)
    }
}