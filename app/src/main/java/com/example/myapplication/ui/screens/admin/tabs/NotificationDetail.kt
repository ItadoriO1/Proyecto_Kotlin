package com.example.myapplication.ui.screens.admin.tabs

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.model.Notification
import com.example.myapplication.model.NotificationItem
import com.example.myapplication.model.Place
import com.example.myapplication.model.PlaceState
import com.example.myapplication.model.Schedule
import com.example.myapplication.ui.components.DropdownMenu
import com.example.myapplication.ui.components.InputText
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.example.myapplication.R




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationDetail(
    notificacionViewModel: NotificationViewModel,
    placeViewModel: PlaceViewModel,
    notificationId: String
){

    var context = LocalContext.current
    var notification by remember { mutableStateOf<Notification?>(null) }
    var place by remember { mutableStateOf<Place?>(null) }
    var notificationItem by remember { mutableStateOf<NotificationItem?>(null) }
    var comentario by remember { mutableStateOf<String?>("") }
    var estadoSeleccionado by remember { mutableStateOf(PlaceState.EN_ESPERA) }

    LaunchedEffect(notificationId) {
        notification = notificacionViewModel.getByIdPlace(notificationId)
        place = notification?.let { placeViewModel.getPlaceById(it.placeId) }
        notificationItem = NotificationItem(notification!!,place!!)
    }

    notificationItem?.let { p ->
        Box(modifier = Modifier.fillMaxSize()){
            AsyncImage(
                model = p.placeItem?.image?.firstOrNull(),
                contentDescription = "Imagen de ${p.placeItem?.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            ) {
            item{
                Spacer(modifier = Modifier.height(200.dp))
            }
            item {
                Surface(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        PlaceHeader(notificationItem = p)
                        Spacer(modifier = Modifier.height(16.dp))
                        PlaceInfoSection(notificationItem = p)
                        Spacer(modifier = Modifier.height(16.dp))
                        RevisionSection(
                            comentario = comentario ?: "",
                            onComentarioChange = { comentario = it },
                            estadoActual = estadoSeleccionado,
                            onEstadoSeleccionadoChange = { estadoSeleccionado = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                    placeViewModel.updateStatePlace(notificationItem?.placeItem?.id!!,estadoSeleccionado)
                                    notificacionViewModel.update(comentario,notificationItem?.notificationItem?.id!!)
                                    Toast.makeText(context, R.string.notifications_alert, Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        ) {
                            Text(
                                text = stringResource(R.string.notifications_save_button),
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PlaceHeader(notificationItem: NotificationItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        notificationItem.placeItem?.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    // TODO: Add rating and review count when available in the model
    notificationItem.placeItem?.description?.let { Text(text = it) }
    Spacer(modifier = Modifier.height(16.dp))
    // TODO: Add category tags
}

@Composable
private fun RevisionSection(
    comentario: String,
    onComentarioChange: (String) -> Unit,
    estadoActual: PlaceState,
    onEstadoSeleccionadoChange: (PlaceState) -> Unit
) {

    val listaDeEstados = remember { PlaceState.values().map { it.name } }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.notifications_agree),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        InputText(
            value = comentario,
            onValueChange = onComentarioChange,
            label = stringResource(R.string.notifications_agree_state),
            icon = Icons.Default.Comment,
            supportingText = "",
            onValidate = {
                comentario.isBlank()
            }
        )

        DropdownMenu(
            label = estadoActual.name,
            supportingText = "",
            list = listaDeEstados,
            icon = Icons.Default.CheckCircle,
            onItemSelected = { nombreEstado ->
                val estado = PlaceState.valueOf(nombreEstado)
                onEstadoSeleccionadoChange(estado)
            },
            onValidate = {
                false
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlaceInfoSection(notificationItem: NotificationItem){
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        InfoRow(icon = Icons.Default.LocationOn, text = notificationItem.placeItem?.address)
        InfoRow(icon = Icons.Default.Phone, text = notificationItem.placeItem?.phones
            ?.takeIf { it.isNotEmpty() }
            ?.joinToString(", ")
            ?: "No disponible")
        InfoRow(
            icon = Icons.Default.Schedule,
            text = formatSchedule(notificationItem.placeItem?.schedule)
        )
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        if (text != null) {
            Text(text = text, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatSchedule(schedule: List<Schedule>?): String {
    if (schedule?.isEmpty() ?: true) return "No hay información de horario."
    // This is a simplified version. A more complex one could group days with the same hours.
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    return schedule?.joinToString("\n") {
        "${it.day.capitalize(Locale.ROOT)}: ${it.open.format(formatter)} - ${it.close.format(formatter)}"
    } ?: ""
}

