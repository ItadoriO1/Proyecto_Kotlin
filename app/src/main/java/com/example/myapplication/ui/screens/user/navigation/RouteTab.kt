package com.example.myapplication.ui.screens.user.navigation

import kotlinx.serialization.Serializable

sealed class RouteTab(){

    @Serializable
    data object map : RouteTab()

    @Serializable
    data object myFavorites : RouteTab()

    @Serializable
    data object myPlaces : RouteTab()

    @Serializable
    data object Profile : RouteTab()

    @Serializable
    data object EditProfile : RouteTab()
    @Serializable
    data object NotificationScreen : RouteTab()

    @Serializable
    data object CreatePlace : RouteTab()

    @Serializable
    data object Login : RouteTab()
}