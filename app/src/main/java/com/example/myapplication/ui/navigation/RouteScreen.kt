package com.example.myapplication.ui.navigation


import kotlinx.serialization.Serializable

sealed class RouteScreen(){

    @Serializable
    data object Login : RouteScreen()


    @Serializable
    data object Register : RouteScreen()


    @Serializable
    data object Home : RouteScreen()

    @Serializable
    data object RecoverPassword : RouteScreen()

    @Serializable
    data object HomeAdmin : RouteScreen()

    @Serializable
    data object CreatePlace : RouteScreen()

    @Serializable
    data class PlaceDetail (val id: String) : RouteScreen()
}