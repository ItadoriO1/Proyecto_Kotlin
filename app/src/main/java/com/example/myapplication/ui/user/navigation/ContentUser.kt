package com.example.myapplication.ui.user.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import co.edu.eam.lugaresapp.ui.screens.LoginForm
import com.example.myapplication.ui.admin.tabs.NotificationDetail
import com.example.myapplication.ui.navigation.LocalMainViewModel
import com.example.myapplication.ui.navigation.RouteScreen
import com.example.myapplication.ui.places.CreatePlace
import com.example.myapplication.ui.user.screens.EditProfile
import com.example.myapplication.ui.user.screens.NotificationScreen
import com.example.myapplication.ui.user.screens.Profile
import com.example.myapplication.ui.user.screens.map
import com.example.myapplication.ui.user.screens.myFavorites
import com.example.myapplication.ui.user.screens.myPlaces
import com.example.myapplication.utils.SharedPrefsUtil

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun contentUser(
    onNavigateToPlaceDetail: (String) -> Unit,
    padding: PaddingValues,
    navController: NavHostController,
    onNavigateToCreatePlaceGlobal: () -> Unit,
    onNavigateToLoginGlobal: () -> Unit,
    onPlaceCreated: () -> Unit,
    context: Context
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
                onNavigateToLogin = {
                    SharedPrefsUtil.clearPreference(context)
                    onNavigateToLoginGlobal()
                }
            )
        }

        composable<RouteTab.NotificationScreen> {
            NotificationScreen(
                onNavigateToNotificationDetail = {
                    navController.navigate(RouteTab.NotificationDetail(it))
                }
            )
        }

        composable<RouteTab.NotificationDetail> {
            val arguments = it.toRoute<RouteTab.NotificationDetail>()
            NotificationDetail(
                notificationId = arguments.id
            )
        }

        composable<RouteTab.CreatePlace> {
            CreatePlace(
                onPlaceCreated = onPlaceCreated,
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteTab.EditProfile> {
            EditProfile()
        }
    }
}