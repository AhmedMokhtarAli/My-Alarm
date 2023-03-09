package com.example.myalarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class NotificationUtils(base:Context) : ContextWrapper(base) {
    val MY_CHANNEL_ID="App Alert Notification Id"
    val MY_CHANNEL="App Alert Notification "

    private var manager:NotificationManager? =null
    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creatChannels()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun creatChannels(){
        val channel=NotificationChannel(MY_CHANNEL_ID,MY_CHANNEL,NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)
        getManeger().createNotificationChannel(channel)
    }

    fun getManeger():NotificationManager{
        if (manager==null) manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotifcationBuilder(): NotificationCompat.Builder{
        val intent= Intent(this,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        return NotificationCompat.Builder(applicationContext, MY_CHANNEL_ID)
            .setContentTitle("Alarm!")
            .setContentText("Your AlarmManager is working.")
            .setSmallIcon(R.drawable.ic_baseline_alarm_24)
            .setColor(Color.YELLOW)
            .setContentIntent(pendingIntent)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)
    }

}
