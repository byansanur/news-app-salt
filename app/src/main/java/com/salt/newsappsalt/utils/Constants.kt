package com.salt.newsappsalt.utils

import com.salt.newsappsalt.BuildConfig
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val BASE_URL = BuildConfig.BASE_URL
const val API_KEY = BuildConfig.API_KEY

const val CATEGORY_BUSINESS = "business"
const val CATEGORY_ENTERTAINMENT = "entertainment"
const val CATEGORY_GENERAL = "general"
const val CATEGORY_HEALTH = "health"
const val CATEGORY_SCIENCE = "science"
const val CATEGORY_SPORTS = "sports"
const val CATEGORY_TECHNOLOGY = "technology"

fun convertDateTime(dateTime: String?) : String? {
    var convTime: String? = null
    val prefix = "Updated "
    val suffix = "Ago"
    try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        val pasTime = dateFormat.parse(dateTime).time
        val nowTime = System.currentTimeMillis()
        val dateDiff: Long = nowTime - pasTime
        val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
        val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
        val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
        when {
            minute < 60 -> {
                convTime = "$prefix $minute Minutes $suffix"
            }
            hour < 24 -> {
                convTime = "$prefix $hour Hours $suffix"
            }
            day >= 7 -> {
                convTime = if (day > 360) {
                    prefix + (day / 360).toString() + " Years " + suffix
                } else if (day > 30) {
                    prefix + (day / 30).toString() + " Months " + suffix
                } else {
                    prefix + (day / 7).toString() + " Week " + suffix
                }
            }
            day < 7 -> {
                convTime = "$prefix $day Days $suffix"
            }
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return convTime
}