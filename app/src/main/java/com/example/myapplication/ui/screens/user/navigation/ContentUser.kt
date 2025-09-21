package com.example.myapplication.ui.screens.user.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.ui.screens.user.tabs.CreatePlace
import com.example.myapplication.ui.screens.user.tabs.EditProfile
import com.example.myapplication.ui.screens.user.tabs.NotificationScreen
import com.example.myapplication.ui.screens.user.tabs.Profile
import com.example.myapplication.ui.screens.user.tabs.map
import com.example.myapplication.ui.screens.user.tabs.myFavorites
import com.example.myapplication.ui.screens.user.tabs.myPlaces

@Composable
fun contentUser(
    padding: PaddingValues,
    navController: NavHostController
){

    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = RouteTab.map
    ){
        composable<RouteTab.map> {
            map()
        }

        composable<RouteTab.myPlaces> {
            myPlaces(
                onNavigateToCreatePlace = {
                    navController.navigate(RouteTab.CreatePlace)
                }
            )
        }

        composable<RouteTab.myFavorites> {
            myFavorites()
        }

        composable<RouteTab.Profile> {
            Profile(
                onNavigateToEditProfile = {
                    navController.navigate(RouteTab.EditProfile)
                },
                onNavigateToLogin = {
                    navController.navigate(RouteTab.Login)
                }
            )
        }

        composable<RouteTab.NotificationScreen> {
            NotificationScreen()
        }

        composable<RouteTab.CreatePlace> {
            CreatePlace()
        }

        composable<RouteTab.EditProfile> {
            EditProfile()
        }

        composable<RouteTab.Login> {
            LoginForm()
        }
    }
}