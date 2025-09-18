package com.example.myapplication.ui.config.network

import com.example.myapplication.model.CountryWithCitiesResponse
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {

    @GET("countries")
    suspend fun getCountriesWithCities(): CountryWithCitiesResponse

    companion object {
        private const val BASE_URL = "https://countriesnow.space/api/v0.1/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}