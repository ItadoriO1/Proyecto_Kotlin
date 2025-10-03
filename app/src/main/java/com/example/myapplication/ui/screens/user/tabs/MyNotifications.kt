package com.example.myapplication.ui.screens.user.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.R
import com.example.myapplication.model.NotificationItem
import com.example.myapplication.viewModel.PlaceViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    notificationsViewModel: NotificationViewModel,
    placesViewModel: PlaceViewModel,
    onNavigateToNotificationDetail: (String) -> Unit //Se agrega el parámetro de navegación para mandar al detalle
){
    val notifications by notificationsViewModel.notification.collectAsState()
    val places by placesViewModel.places.collectAsState()

    val combinedList: List<NotificationItem> = remember(notifications,places){
        notifications.map { notification ->
            val placeAsociado = places.find { it.id == notification.placeId }
            NotificationItem(notification,placeAsociado)
        }
    }

    Scaffold(){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item { // Header para notificaciones
                    Text(
                        text = stringResource(R.string.notifications_profile),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                items(combinedList){ item ->
                    val notification = item.notificationItem
                    val place = item.placeItem

                    ListItem(
                        headlineContent = { notification.comment?.let { Text(it) } },
                        supportingContent = {place?.description},
                        leadingContent = {
                            AsyncImage(
                                model = place?.image?.firstOrNull(),
                                contentDescription = "Imagen de ${place?.name}",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                        },
                        trailingContent = {
                            StatusChip(state = place?.state)
                        },
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .clickable{
                                onNavigateToNotificationDetail(notification.id) //Se llama a la función de navegación, y se pasa el id de la notificacion como parametro
                            }
                    )
                }
            }
            )
    }
}