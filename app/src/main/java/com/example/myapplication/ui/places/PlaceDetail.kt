package com.example.myapplication.ui.places

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.model.DayOfWeek
import com.example.myapplication.model.Place
import com.example.myapplication.model.Schedule
import com.example.myapplication.ui.navigation.LocalMainViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetail(
    placeId: String
) {
    val mainViewModel = LocalMainViewModel.current
    val placeViewModel = mainViewModel.placeViewModel

    var place by remember { mutableStateOf<Place?>(null) }

    LaunchedEffect(placeId) {
        place = placeViewModel.getPlaceById(placeId)
    }

    place?.let { p ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            AsyncImage(
                model = p.image.firstOrNull(),
                contentDescription = "Background image of ${p.name}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Top Bar
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle share */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* TODO: Handle favorite */ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )

            // Content
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(200.dp)) // Spacer to push content down
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
                            PlaceHeader(place = p)
                            Spacer(modifier = Modifier.height(16.dp))
                            PlaceInfoSection(place = p)
                            Spacer(modifier = Modifier.height(16.dp))
                            ActionButtons()
                            Spacer(modifier = Modifier.height(16.dp))
                            CommentsSection()
                        }
                    }
                }
            }
        }
    } ?: run {
        // Optional: Show a loading indicator while the place is being fetched
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PlaceHeader(place: Place) {
    val isOpen = isPlaceOpen(place.schedule)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = place.name,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        StatusChip(isOpen = isOpen)
    }
    Spacer(modifier = Modifier.height(8.dp))
    // TODO: Add rating and review count when available in the model
    Text(text = place.description)
    Spacer(modifier = Modifier.height(16.dp))
    // TODO: Add category tags
}

@Composable
fun StatusChip(isOpen: Boolean) {
    val backgroundColor = if (isOpen) Color(0xFFE0F7E0) else Color(0xFFFFEBEE)
    val textColor = if (isOpen) Color(0xFF388E3C) else Color(0xFFD32F2F)
    val text = if (isOpen) "Abierto" else "Cerrado"

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = text, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PlaceInfoSection(place: Place) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        InfoRow(icon = Icons.Default.LocationOn, text = place.address)
        InfoRow(icon = Icons.Default.Phone, text = place.phones.joinToString(", "))
        InfoRow(
            icon = Icons.Default.Schedule,
            text = formatSchedule(place.schedule)
        )
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun ActionButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(imageVector = Icons.Default.Comment, contentDescription = "Comment")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Comentar")
        }
        OutlinedButton(
            onClick = { /* TODO */ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = "Photo")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Foto")
        }
    }
}

@Composable
private fun CommentsSection() {
    Column {
        Text(
            text = "Comentarios recientes",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Comment items will be added here
        Text("Sé el primero en comentar.", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun isPlaceOpen(schedule: List<Schedule>): Boolean {
    val now = LocalTime.now()
    val today = LocalDate.now().dayOfWeek

    if (DayOfWeek.values().isEmpty()) return false

    val todayMyApp = DayOfWeek.values()[today.value - 1]
    val todaySchedule = schedule.firstOrNull { it.day == todayMyApp }

    return todaySchedule?.let { now.isAfter(it.open) && now.isBefore(it.close) } ?: false
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatSchedule(schedule: List<Schedule>): String {
    if (schedule.isEmpty()) return "No hay información de horario."
    // This is a simplified version. A more complex one could group days with the same hours.
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    return schedule.joinToString("\n") {
        "${it.day.displayName}: ${it.open.format(formatter)} - ${it.close.format(formatter)}"
    }
}
