package com.example.myalarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity,
            { timePicker: TimePicker, hour: Int, minte: Int ->
                val calender = Calendar.getInstance()
                calender.set(Calendar.HOUR_OF_DAY, hour)
                calender.set(Calendar.MINUTE, minte)
                calender.set(Calendar.SECOND, 0)

                //send alarm to brodcast
                val alarmManager = activity?.getSystemService(ALARM_SERVICE) as AlarmManager
                val intent = Intent(activity, AlarmReciver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calender.timeInMillis, pendingIntent)
            },
            hour,
            minute, false
        )
    }
}
