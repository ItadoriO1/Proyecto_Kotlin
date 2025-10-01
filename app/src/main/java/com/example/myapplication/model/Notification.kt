package com.example.myapplication.model

import java.time.LocalDate

data class Notification(
  val id: String,
  val placeId: String,
  val comment: String?,
  val date: LocalDate,
 ){}