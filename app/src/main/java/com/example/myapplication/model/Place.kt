package com.example.myapplication.model

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val location: Location,
    val schedule: List<Schedule>,
    val phones: List<String>,
    val category: PlaceType,
    val comments: Comments,
    val image: List<String>
){}