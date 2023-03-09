package com.example.myalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val time=findViewById<TextView>(R.id.calender)
        val calendar=Calendar.getInstance()
        time.text="${calendar.timeInMillis.hours} : ${calendar.timeInMillis.minutes} "

    }
    fun showTimePicker(view: View){
        val timePicker=TimePickerFragment()
        timePicker.show(supportFragmentManager,"time_picker")
    }

    fun cancelAlert(view: View){
        val alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        val intent=Intent(this, AlarmReciver::class.java)
        val pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.cancel(pendingIntent)

    }
}