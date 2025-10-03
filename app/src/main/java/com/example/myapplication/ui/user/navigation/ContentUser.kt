package com.example.myapplication.ui.user.navigation

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
import com.example.myapplication.ui.admin.tabs.NotificationDetail
import com.example.myapplication.ui.places.CreatePlace
import com.example.myapplication.ui.user.screens.EditProfile
import com.example.myapplication.ui.user.screens.NotificationScreen
import com.example.myapplication.ui.user.screens.Profile
import com.example.myapplication.ui.user.screens.map
import com.example.myapplication.ui.user.screens.myFavorites
import com.example.myapplication.ui.user.screens.myPlaces
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import com.example.myapplication.viewModel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun contentUser(
    onNavigateToPlaceDetail: (String) -> Unit,
    padding: PaddingValues,
    navController: NavHostController,
    placesViewModel: PlaceViewModel,
    notificationViewModel: NotificationViewModel,// ViewModel pasado como parámetro
    onNavigateToCreatePlaceGlobal: () -> Unit,
    onNavigateToLoginGlobal: () -> Unit, // Nuevo parámetro para la navegación global a Login
    onPlaceCreated: () -> Unit
){
    // Se elimina la creación local del ViewModel: val placesViewModel: PlaceViewModel = viewModel();
    val userViewModel: UserViewModel = viewModel();

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
                onNavigateToCreatePlace = onNavigateToCreatePlaceGlobal,
                onNavigateToPlaceDetail = onNavigateToPlaceDetail
            )
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
                notificationsViewModel = notificationViewModel,
                placesViewModel = placesViewModel,
                onNavigateToNotificationDetail = {
                    navController.navigate(RouteTab.NotificationDetail(it))
                }
            )
        }

        composable<RouteTab.NotificationDetail> {
            val arguments = it.toRoute<RouteTab.NotificationDetail>()
            NotificationDetail(
                notificacionViewModel = notificationViewModel,
                placeViewModel = placesViewModel,
                notificationId = arguments.id
            )
        }

        composable<RouteTab.CreatePlace> {
            CreatePlace(
                placeViewModel = placesViewModel,
                onPlaceCreated = onPlaceCreated,
                notificationViewModel = notificationViewModel
            )
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