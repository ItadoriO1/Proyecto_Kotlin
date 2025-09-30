package com.example.myapplication.ui.screens

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
import com.example.myapplication.viewModel.UserViewModel
@Composable
fun Navigation(){

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

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
                navController = navController
            )
        }

        composable<RouteScreen.HomeAdmin> {
            HomeAdminScreen()
        }

        composable<RouteScreen.CreatePlace> {
            CreatePlace()
        }

    }

}