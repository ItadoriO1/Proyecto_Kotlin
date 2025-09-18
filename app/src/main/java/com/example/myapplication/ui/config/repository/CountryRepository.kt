package com.example.myapplication.ui.config.repository

import com.example.myapplication.ui.config.network.ApiService

class CountryRepository(private val api: ApiService) {
    suspend fun fetchCountriesWithCities() = api.getCountriesWithCities()
}