package com.example.myapplication.ui.screens.user.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.ui.screens.user.tabs.CreatePlace
import com.example.myapplication.ui.screens.user.tabs.EditProfile
import com.example.myapplication.ui.screens.user.tabs.NotificationScreen
import com.example.myapplication.ui.screens.user.tabs.PlaceDetail
import com.example.myapplication.ui.screens.user.tabs.Profile
import com.example.myapplication.ui.screens.user.tabs.map
import com.example.myapplication.ui.screens.user.tabs.myFavorites
import com.example.myapplication.ui.screens.user.tabs.myPlaces
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import com.example.myapplication.viewModel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun contentUser(
    padding: PaddingValues,
    navController: NavHostController,
    onNavigateToCreatePlaceGlobal: () -> Unit,
    onNavigateToLoginGlobal: () -> Unit // Nuevo parámetro para la navegación global a Login
){

    val placesViewModel: PlaceViewModel = viewModel();
    val userViewModel: UserViewModel = viewModel();
    val notificationViewModel: NotificationViewModel = viewModel()

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
                placesViewModel = placesViewModel,
                onNavigateToCreatePlace = onNavigateToCreatePlaceGlobal,
                onNavigateToPlaceDetail = {
                    navController.navigate(RouteTab.PlaceDetail(it)) }
            )
        }

        composable<RouteTab.PlaceDetail> {
            val arguments = it.toRoute<RouteTab.PlaceDetail>()
            PlaceDetail(
                placeViewModel = placesViewModel,
                placeId = arguments.id)
        }

        composable<RouteTab.myFavorites> {
            myFavorites()
        }

        composable<RouteTab.Profile> {
            Profile(
                onNavigateToEditProfile = { navController.navigate(RouteTab.EditProfile) },
                onNavigateToLogin = onNavigateToLoginGlobal // Pasa la lambda global aquí
            )
        }

        composable<RouteTab.NotificationScreen> {
            NotificationScreen(
                notificationsViewModel = notificationViewModel
            )
        }

        composable<RouteTab.CreatePlace> {
            CreatePlace()
        }

        composable<RouteTab.EditProfile> {
            EditProfile()
        }

        composable<RouteTab.Login> {
            LoginForm(
                userViewModel = userViewModel,
            )
        }
    }
}