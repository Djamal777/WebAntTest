package com.example.webanttes.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

private val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

fun stringDateToCalendar(stringDate: String): Calendar {
    val cal = Calendar.getInstance()
    val instant: Instant = LocalDateTime.parse(stringDate, format).atZone(ZoneId.systemDefault()).toInstant()
    val date = Date.from(instant)
    cal.time = date
    return cal
}