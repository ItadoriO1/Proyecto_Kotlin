package com.example.myapplication.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.ui.auth.RecoverPassword
import com.example.myapplication.ui.auth.RegisterScreen
import com.example.myapplication.ui.admin.HomeAdminScreen
import com.example.myapplication.ui.user.screens.HomeScreen
import com.example.myapplication.ui.places.CreatePlace
import com.example.myapplication.ui.places.PlaceDetail
import com.example.myapplication.ui.user.navigation.RouteTab
import com.example.myapplication.viewModel.MainViewModel
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import com.example.myapplication.viewModel.UserViewModel


val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> { error("MainViewModel no establecida") }
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    mainViewModel: MainViewModel
){

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()
    val placesViewModel: PlaceViewModel = viewModel()
    val notificationViewModel: NotificationViewModel = viewModel()


    CompositionLocalProvider(
        LocalMainViewModel provides mainViewModel
    ){
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
                    notificationViewModel = notificationViewModel,// Ahora pasamos la instancia única del ViewModel
                    onNavigateToPlaceDetail = {
                        navController.navigate(RouteScreen.PlaceDetail(it))
                    },
                    mainViewModel = mainViewModel
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

            composable<RouteScreen.PlaceDetail> {
                val arguments = it.toRoute<RouteScreen.PlaceDetail>()
                PlaceDetail(
                    placeViewModel = placesViewModel,
                    placeId = arguments.id)
            }

        }
    }

}