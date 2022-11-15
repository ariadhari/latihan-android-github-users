package com.example.githubusers.helper

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateFormatLocale {
    val locale = Locale("in", "ID")
    const val ASIA_JAKARTA = "Asia/Jakarta"

    val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)

    /**
     * Get this date time with eg format 2020-12-12 12:12:12
     *
     *  Example input : Asia/Jakarta (default param)
     *
     *  Example output : 2017-11-09 00:00:00
     */
    fun getDateTimeNow(timeZone: String = ASIA_JAKARTA): String {

        return try {
            timestamp.timeZone = TimeZone.getTimeZone(timeZone)
            timestamp.format(Date())
        } catch (ex: Exception) {
            ""
        }
    }

    /**
     *  Create Time Different Date format
     *
     *  example output: 1 tahun lalu
     *
     *  example input: params: 2021-02-08T13:00:00
     *
     *  note* must replace space to T
     *
     *  example use: convertToTimeDiff("2021-02-08T13:00:00")
     */
    @SuppressLint("SimpleDateFormat")
    fun covertToTimeDiff(dataDate: String): String? {
        var result: String? = null
        val suffix = "ago"
        val tes : String
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val pasTime = dateFormat.parse(dataDate)
            val nowTime = Date()
            val dateDiff = nowTime.time - pasTime?.time!!
            val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                result = "$second seconds $suffix"
            } else if (minute < 60) {
                if (minute > 1) { tes = " minutes "}
                else { tes = " minute " }
                result = "$minute $tes $suffix"
            } else if (hour < 24) {
                if (hour > 1) { tes = " hours "}
                else { tes = " hour " }
                result = "$hour $tes $suffix"
            } else if (day >= 7) {
                result = when {
                    day > 360 -> {
                        (day / 360).toString() + " years " + suffix
                    }
                    day > 30 -> {
                        (day / 30).toString() + " months " + suffix
                    }
                    else -> {
                        (day / 7).toString() + " weeks " + suffix
                    }
                }
            } else if (day < 7) {
                if (day > 1) { tes = " days "}
                else { tes = " day " }
                result = "$day $tes $suffix"
            }
        } catch (e: ParseException) {
            result = "null"
        }
        return "Updated $result"
    }

}