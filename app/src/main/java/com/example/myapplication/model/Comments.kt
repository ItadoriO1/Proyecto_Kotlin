package com.example.myapplication.model

import java.time.LocalDate

class Comments(
    val id: String,
    val userId: String,
    val placeId: String,
    val rating: String,
    val comment: String,
    val date: LocalDate
) {
}