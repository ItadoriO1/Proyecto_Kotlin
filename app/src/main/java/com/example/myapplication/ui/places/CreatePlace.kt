package com.example.myapplication.ui.places

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.DateRange // Para horario, se puede usar algo como DateRange o AccessTime
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.*
import com.example.myapplication.ui.components.DropdownMenu
import com.example.myapplication.ui.components.InputText // Importa tu componente InputText
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.viewModel.PlaceViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeParseException
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlace(
    placeViewModel: PlaceViewModel,
    notificationViewModel: NotificationViewModel,
    onPlaceCreated: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var phones by remember { mutableStateOf("") }
    var scheduleText by remember { mutableStateOf("") }
    var images by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(PlaceType.OTRO) }

    val placeTypes = remember { PlaceType.values().toList() }
    val placeTypeNames = placeTypes.map { it.getDisplayName() }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.create_place_title)) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.create_place_details_header),
                style = MaterialTheme.typography.headlineSmall
            )

            InputText(
                value = name,
                onValueChange = { name = it },
                label = stringResource(id = R.string.create_place_name_label),
                icon = Icons.Default.Title,
                supportingText = "", // Puedes añadir lógica de validación aquí si es necesario
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )
            InputText(
                value = description,
                onValueChange = { description = it },
                label = stringResource(id = R.string.create_place_description_label),
                icon = Icons.Default.Description,
                supportingText = "",
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )
            InputText(
                value = address,
                onValueChange = { address = it },
                label = stringResource(id = R.string.create_place_address_label),
                icon = Icons.Default.Place,
                supportingText = "",
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InputText(
                    value = latitude,
                    onValueChange = { latitude = it },
                    label = stringResource(id = R.string.create_place_latitude_label),
                    icon = Icons.Default.LocationOn,
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    supportingText = "",
                    onValidate = { false }
                )
                InputText(
                    value = longitude,
                    onValueChange = { longitude = it },
                    label = stringResource(id = R.string.create_place_longitude_label),
                    icon = Icons.Default.LocationOn,
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    supportingText = "",
                    onValidate = { false }
                )
            }

            InputText(
                value = phones,
                onValueChange = { phones = it },
                label = stringResource(id = R.string.create_place_phones_label),
                icon = Icons.Default.Phone,
                supportingText = stringResource(id = R.string.create_place_phones_supporting_text),
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )
            InputText(
                value = images,
                onValueChange = { images = it },
                label = stringResource(id = R.string.create_place_images_label),
                icon = Icons.Default.Image,
                supportingText = stringResource(id = R.string.create_place_images_supporting_text),
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                label = stringResource(id = R.string.create_place_category_label),
                supportingText = "",
                list = placeTypeNames,
                icon = Icons.Filled.Category,
                onItemSelected = { selectedName ->
                    val index = placeTypeNames.indexOf(selectedName)
                    if (index != -1) selectedCategory = placeTypes[index]
                },
                onValidate = { false }
            )

            InputText(
                value = scheduleText,
                onValueChange = { scheduleText = it },
                label = stringResource(id = R.string.create_place_schedule_label),
                icon = Icons.Default.DateRange,
                modifier = Modifier.fillMaxWidth(),
                supportingText = stringResource(id = R.string.create_place_schedule_supporting_text),
                onValidate = { false }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        val lat = latitude.toDouble()
                        val lon = longitude.toDouble()

                        val phoneList = phones.split(",").map { it.trim() }.filter { it.isNotEmpty() }
                        val imageList = images.split(",").map { it.trim() }.filter { it.isNotEmpty() }

                        val scheduleList = scheduleText.split(";").map { scheduleItem ->
                            val parts = scheduleItem.split(",")
                            Schedule(
                                day = parts[0].trim(),
                                open = LocalTime.parse(parts[1].trim()),
                                close = LocalTime.parse(parts[2].trim())
                            )
                        }

                        val newPlace = Place(
                            id = UUID.randomUUID().toString(),
                            name = name,
                            description = description,
                            address = address,
                            location = Location(lat, lon),
                            schedule = scheduleList,
                            phones = phoneList,
                            category = selectedCategory,
                            comments = null,
                            image = imageList,
                            userId = null // O el ID del usuario actual
                        )

                        val newNotification = Notification(
                            id = UUID.randomUUID().toString(),
                            placeId = newPlace.id,
                            comment = "Tu lugar esta a la espera de revision",
                            date = LocalDate.now()
                        )

                        placeViewModel.createPlace(newPlace)
                        notificationViewModel.create(newNotification)
                        Toast.makeText(context, context.getString(R.string.create_place_save_success_message), Toast.LENGTH_SHORT).show()
                        onPlaceCreated()

                    } catch (e: Exception) {
                        val errorMessage = when (e) {
                            is NumberFormatException -> context.getString(R.string.create_place_error_latitude_longitude_format)
                            is DateTimeParseException -> context.getString(R.string.create_place_error_time_format)
                            is IndexOutOfBoundsException -> context.getString(R.string.create_place_error_schedule_format)
                            else -> context.getString(R.string.create_place_error_generic_save, e.message)
                        }
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier.align(Alignment.End).fillMaxSize()
            ) {
                Text(stringResource(id = R.string.create_place_save_button))
            }
        }
    }
}