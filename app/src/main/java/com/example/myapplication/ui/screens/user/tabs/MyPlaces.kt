package com.example.myapplication.ui.screens.user.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.viewModel.PlaceViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun myPlaces(
    placesViewModel: PlaceViewModel,
    onNavigateToCreatePlace: () -> Unit = {},
    onNavigateToPlaceDetail: (String) -> Unit
) {
    val places by placesViewModel.places.collectAsState()

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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item { // Header para "Mis Lugares"
                    Text(
                        text = stringResource(id = R.string.menu_places),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(places) { place ->
                    ListItem(
                        headlineContent = { Text(place.name) },
                        supportingContent = { Text(place.description) },
                        leadingContent = {
                            AsyncImage(
                                model = place.image.firstOrNull(), // Usa la primera imagen si existe
                                contentDescription = "Imagen de ${place.name}",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                        },
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .clickable{
                                onNavigateToPlaceDetail(place.id)
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp)

                    )
                }
            }
        )
    }
}