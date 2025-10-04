package com.example.myapplication.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.model.Role
import com.example.myapplication.ui.auth.RecoverPassword
import com.example.myapplication.ui.auth.RegisterScreen
import com.example.myapplication.ui.admin.HomeAdminScreen
import com.example.myapplication.ui.user.screens.HomeScreen
import com.example.myapplication.ui.places.CreatePlace
import com.example.myapplication.ui.places.PlaceDetail
import com.example.myapplication.utils.SharedPrefsUtil
import com.example.myapplication.viewModel.MainViewModel

val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> { error("MainViewModel no establecida") }
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    mainViewModel: MainViewModel
){

    val context = LocalContext.current
    val navController = rememberNavController()
    val user = SharedPrefsUtil.getPreference(context)

    val startDestination = if(user.isEmpty()){
        RouteScreen.Login
    }else {
        if (user["role"] == "ADMIN"){
            RouteScreen.HomeAdmin
        }else{
            RouteScreen.Home
        }
    }

    CompositionLocalProvider(
        LocalMainViewModel provides mainViewModel
    ){
        NavHost(
            navController = navController,
            startDestination = startDestination
        ){

            composable<RouteScreen.Login> {
                LoginForm(
                    onNavigateToRegister = {
                        navController.navigate(RouteScreen.Register)
                    },
                    onNavigateToHome = { id, role ->
                        SharedPrefsUtil.savePreference(context, id, role)
                        if (role == Role.ADMIN){
                            navController.navigate(RouteScreen.HomeAdmin)
                        }else{
                            navController.navigate(RouteScreen.Home)
                        }
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
                    navController = navController,
                    onNavigateToPlaceDetail = {
                        navController.navigate(RouteScreen.PlaceDetail(it))
                    },
                    context = context
                )
            }

            composable<RouteScreen.HomeAdmin> {
                HomeAdminScreen()
            }

            composable<RouteScreen.CreatePlace> {
                CreatePlace(
                    onPlaceCreated = {
                        navController.popBackStack()
                    },
                    onNavigateToBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable<RouteScreen.PlaceDetail> {
                val arguments = it.toRoute<RouteScreen.PlaceDetail>()
                PlaceDetail(
                    placeId = arguments.id
                )
            }

        }
    }

}