package com.example.myapplication.ui.user.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.components.Search
import com.example.myapplication.ui.user.bottomBar.bottomBarUser
import com.example.myapplication.ui.user.navigation.RouteTab
import com.example.myapplication.ui.user.navigation.contentUser
import com.example.myapplication.ui.user.topBar.topBarUser
import com.example.myapplication.ui.navigation.RouteScreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onNavigateToPlaceDetail: (String) -> Unit,
    context: Context // 1. Añadir context como parámetro
) {

    val tabNavController = rememberNavController()
    val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if(currentRoute == RouteTab.map::class.qualifiedName){
                    Column {
                        topBarUser(
                            onNavigateToProfile = {
                                tabNavController.navigate(RouteTab.Profile)
                            },
                            onNavigateToNotification = {
                                tabNavController.navigate(RouteTab.NotificationScreen)
                            },
                            onNavigateToCreatePlaceGlobal = {
                                navController.navigate(RouteScreen.CreatePlace)
                            }
                        )
                        Search(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onNavigateToPlaceDetail = onNavigateToPlaceDetail
                        )
                    }
                }
            },
            bottomBar = {
                bottomBarUser(navController = tabNavController)
            }
        ) { padding ->
            contentUser(
                context = context, // 2. Pasar el context a contentUser
                onNavigateToPlaceDetail = onNavigateToPlaceDetail,
                padding = padding,
                navController = tabNavController,
                onNavigateToCreatePlaceGlobal = { navController.navigate(RouteScreen.CreatePlace) },
                onNavigateToLoginGlobal = { navController.navigate(RouteScreen.Login) },
                onPlaceCreated = { tabNavController.navigate(RouteTab.myPlaces) }
            )
        }
    }
}