package com.example.myapplication.ui.screens.user.tabs

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.components.InputText
import com.example.myapplication.ui.config.routes.RouteScreen // Importa RouteScreen

@Composable
fun myPlaces(
    onNavigateToCreatePlace: () -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToCreatePlace()
                },
                containerColor = Color(0xFF6200EE), // Color Morado
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Lugar")
            }
        }
    ) { paddingValues ->

        Surface(modifier = Modifier.padding(paddingValues)) { // Aplica el paddingValues aquí
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp), // Puedes usar el padding adicional si lo necesitas
                content = {
                    Text(text = "Mis Lugares")
                }
            )
        }
    }
}