package com.example.myapplication.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.ui.config.routes.RouteScreen
import com.example.myapplication.ui.screens.admin.HomeAdminScreen
import com.example.myapplication.ui.screens.user.HomeScreen
import com.example.myapplication.ui.screens.user.tabs.CreatePlace
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import com.example.myapplication.viewModel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(){

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()
    val placesViewModel: PlaceViewModel = viewModel()
    val notificationViewModel: NotificationViewModel = viewModel()


    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ){

        composable<RouteScreen.Login> {
            LoginForm(
                userViewModel = userViewModel,
                onNavigateToRegister = {
                    navController.navigate(RouteScreen.Register)
                },
                onNavigateToHome = {
                    navController.navigate(RouteScreen.Home)
                }
            )
        }

        composable<RouteScreen.Register> {
            RegisterScreen(
                userViewModel = userViewModel,
                onNavigateToLogin = {
                    navController.navigate(RouteScreen.Login)
                }
            )
        }

        composable<RouteScreen.RecoverPassword>{
            RecoverPassword()
        }

        composable<RouteScreen.Home>{
            HomeScreen(
                navController = navController,
                placesViewModel = placesViewModel,
                notificationViewModel = notificationViewModel// Ahora pasamos la instancia única del ViewModel
            )
        }

        composable<RouteScreen.HomeAdmin> {
            HomeAdminScreen()
        }

        composable<RouteScreen.CreatePlace> {
            CreatePlace(
                placeViewModel = placesViewModel,
                notificationViewModel = notificationViewModel,
                onPlaceCreated = {
                    navController.popBackStack()
                }
            )
        }

    }

}