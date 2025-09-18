package com.example.myapplication.model

data class CountryWithCitiesResponse(
    val error: Boolean,
    val msg: String,
    val data: List<CountryWithCities>
)

data class CountryWithCities(
    val country: String,
    val cities: List<String>
)