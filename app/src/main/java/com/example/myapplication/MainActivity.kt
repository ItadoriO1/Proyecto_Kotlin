package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.myapplication.ui.navigation.Navigation
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewModel.CommentViewModel
import com.example.myapplication.viewModel.CountryViewModel
import com.example.myapplication.viewModel.MainViewModel
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import com.example.myapplication.viewModel.UserViewModel

class MainActivity : ComponentActivity() {

    private val placesViewModel: PlaceViewModel by viewModels()
    private val notificationViewModel: NotificationViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private val countryViewModel: CountryViewModel by viewModels()
    private val commentViewModel: CommentViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val mainViewModel = MainViewModel(
            placesViewModel,
            notificationViewModel,
            userViewModel,
            countryViewModel,
            commentViewModel
        )
        setContent {
            MyApplicationTheme {
                Navigation(
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}

