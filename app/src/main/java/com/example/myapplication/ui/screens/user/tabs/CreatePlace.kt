package com.example.myapplication.ui.screens.user.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.PlaceType
import com.example.myapplication.model.getDisplayName
import com.example.myapplication.ui.components.DropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlace() {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phones by remember { mutableStateOf("") }
    var scheduleText by remember { mutableStateOf("") }

    var selectedCategory by remember { mutableStateOf(PlaceType.OTRO) }
    
    // Obtenemos la lista de todos los valores del enum
    val placeTypes = remember { PlaceType.values().toList() }
    // Creamos la lista de nombres a mostrar en el Composable
    val placeTypeNames = placeTypes.map { it.getDisplayName() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.create_place_title)) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.create_place_details_header),
                style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(id = R.string.create_place_name_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(id = R.string.create_place_description_label)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(stringResource(id = R.string.create_place_address_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phones,
                onValueChange = { phones = it },
                label = { Text(stringResource(id = R.string.create_place_phones_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                label = stringResource(id = R.string.create_place_category_label),
                supportingText = "",
                list = placeTypeNames, // Usamos la lista de nombres traducidos
                icon = Icons.Filled.Category,
                onItemSelected = { selectedName ->
                    // Buscamos el PlaceType que corresponde al nombre seleccionado
                    val index = placeTypeNames.indexOf(selectedName)
                    if (index != -1) {
                        selectedCategory = placeTypes[index]
                    }
                },
                onValidate = { false }
            )

            OutlinedTextField(
                value = scheduleText,
                onValueChange = { scheduleText = it },
                label = { Text(stringResource(id = R.string.create_place_schedule_label)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // TODO: Implement save logic
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(id = R.string.create_place_save_button))
            }
        }
    }
}