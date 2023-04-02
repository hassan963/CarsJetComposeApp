package com.sevenpeakssoftware.hassanmashraful.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun formatDateWithPattern(
    date: String,
    currentFormat: String = DATE_TIME_FORMAT,
    expectedFormat: String
): String {
    val parser = SimpleDateFormat(currentFormat, Locale.ENGLISH)
    val formatter = SimpleDateFormat(expectedFormat, Locale.ENGLISH)
    var dateTime = ""

    try {
        val formattedDate = parser.parse(date)
        dateTime = formattedDate?.let { formatter.format(it) } ?: ""
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return dateTime
}

fun isCurrentYear(date: String): Boolean {
    val calendar: Calendar = Calendar.getInstance()
    val currentYear: Int = calendar.get(Calendar.YEAR)
    val formattedDate = formatStringToDate(date, DATE_TIME_FORMAT)

    return formattedDate?.let {
        currentYear == getYear(it)
    } ?: false
}

fun formatStringToDate(date: String, format: String): Date? {
    var formattedDate: Date? = null
    try {
        formattedDate = SimpleDateFormat(format, Locale.ENGLISH).parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return formattedDate
}

fun getYear(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.YEAR)
}