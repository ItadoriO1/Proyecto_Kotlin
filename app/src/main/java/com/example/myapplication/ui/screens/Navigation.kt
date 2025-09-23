package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.ui.config.routes.RouteScreen
import com.example.myapplication.ui.screens.admin.HomeAdminScreen
import com.example.myapplication.ui.screens.user.HomeScreen
import com.example.myapplication.ui.screens.user.tabs.CreatePlace

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ){

        composable<RouteScreen.Login> {
            LoginForm(
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