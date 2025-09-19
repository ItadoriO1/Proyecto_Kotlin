package com.example.myapplication.ui.screens.user

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.user.bottomBar.bottomBarUser
import com.example.myapplication.ui.screens.user.navigation.RouteTab
import com.example.myapplication.ui.screens.user.navigation.contentUser
import com.example.myapplication.ui.screens.user.topBar.topBarUser

@Composable
fun HomeScreen(){

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if(currentRoute == RouteTab.map::class.qualifiedName){
                    topBarUser()
                }
            },
            bottomBar = {
                bottomBarUser(navController = navController)
            }
        ) { padding ->
            contentUser(padding,
                navController = navController)
        }
    }
}