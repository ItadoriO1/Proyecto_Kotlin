package com.example.myapplication.ui.user.screens

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
import com.example.myapplication.ui.navigation.RouteScreen // Importa RouteScreen para la navegación a CreatePlace
import com.example.myapplication.viewModel.MainViewModel
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel // Importa PlaceViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    placesViewModel: PlaceViewModel,
    notificationViewModel: NotificationViewModel,// HomeScreen ahora recibe PlaceViewModel
    onNavigateToPlaceDetail: (String) -> Unit,
    mainViewModel: MainViewModel
) { 

    val tabNavController = rememberNavController() // NavController para las pestañas internas
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
                                tabNavController.navigate(RouteTab.Profile) // Navega dentro de las pestañas
                            },
                            onNavigateToNotification = {
                                tabNavController.navigate(RouteTab.NotificationScreen) // Navega dentro de las pestañas
                            },
                            onNavigateToCreatePlaceGlobal = {
                                navController.navigate(RouteScreen.CreatePlace) // USA EL NAVCONTROLLER PRINCIPAL
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
                bottomBarUser(navController = tabNavController) // Usa el NavController de las pestañas
            }
        ) { padding ->
            contentUser(
                onNavigateToPlaceDetail = onNavigateToPlaceDetail,
                padding = padding,
                navController = tabNavController, // Pasa el NavController de las pestañas
                placesViewModel = placesViewModel,
                notificationViewModel = notificationViewModel,// PASA LA INSTANCIA DE PlaceViewModel
                onNavigateToCreatePlaceGlobal = { navController.navigate(RouteScreen.CreatePlace) }, // Pasa la lambda global
                onNavigateToLoginGlobal = { navController.navigate(RouteScreen.Login) }, // Pasa la lambda global para Login
                onPlaceCreated = { tabNavController.navigate(RouteTab.myPlaces) } // Navega a myPlaces después de crear un lugar
            )
        }
    }
}