package com.example.myapplication.model

import java.time.LocalTime

data class Schedule(
    val day: DayOfWeek,
    val open: LocalTime,
    val close: LocalTime
)