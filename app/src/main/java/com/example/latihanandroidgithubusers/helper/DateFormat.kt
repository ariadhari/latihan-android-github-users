package com.example.latihanandroidgithubusers.helper

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateFormatLocale {
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
        val unit : String
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
                if (minute > 1) { unit = " minutes "}
                else { unit = " minute " }
                result = "$minute $unit $suffix"
            } else if (hour < 24) {
                if (hour > 1) { unit = " hours "}
                else { unit = " hour " }
                result = "$hour $unit $suffix"
            } else if (day >= 7) {
                result = when {
                    day > 360 -> {
                        if (day/360 < 2) { unit = " year " }
                        else { unit = " years " }
                        (day / 360).toString() + unit + suffix
                    }
                    day > 30 -> {
                        if (day/30 < 2) { unit = " month " }
                        else { unit = " months " }
                        (day / 30).toString() + unit + suffix
                    }
                    else -> {
                        if (day/7 < 2) { unit = " week " }
                        else { unit = " weeks " }
                        (day / 7).toString() + unit + suffix
                    }
                }
            } else if (day < 7) {
                if (day > 1) { unit = " days "}
                else { unit = " day " }
                result = "$day $unit $suffix"
            }
        } catch (e: ParseException) {
            result = "null"
        }
        return "Updated $result"
    }

}