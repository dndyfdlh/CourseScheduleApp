package com.dicoding.courseschedule.util

import java.util.*

enum class DayName(val value: String) {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    companion object {
        fun getByNumber(dayNumber: Int) = when (dayNumber) {
            Calendar.MONDAY -> MONDAY.value
            Calendar.TUESDAY -> TUESDAY.value
            Calendar.WEDNESDAY -> WEDNESDAY.value
            Calendar.THURSDAY -> THURSDAY.value
            Calendar.FRIDAY -> FRIDAY.value
            Calendar.SATURDAY -> SATURDAY.value
            Calendar.SUNDAY -> SUNDAY.value
            else -> MONDAY.value
        }

        fun getByName(name: String) = when (name) {
            MONDAY.value -> Calendar.MONDAY-1
            TUESDAY.value -> Calendar.TUESDAY-1
            WEDNESDAY.value -> Calendar.WEDNESDAY-1
            THURSDAY.value -> Calendar.THURSDAY-1
            FRIDAY.value -> Calendar.FRIDAY-1
            SATURDAY.value -> Calendar.SATURDAY-1
            SUNDAY.value -> Calendar.SUNDAY-1
            else -> Calendar.MONDAY-1
        }
    }
}