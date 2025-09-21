package com.example.myapplication.ui.screens.user.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.InputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile() {
    // Estados para los campos editables
    var name by remember { mutableStateOf("Usuario Normal") }
    var username by remember { mutableStateOf("@usuario") }
    var city by remember { mutableStateOf("Guadalajara") }
    // El email es fijo y no se edita
    val email = "user@test.com"

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.edit_profile_title)) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputText(
                value = name,
                onValueChange = { name = it },
                label = stringResource(id = R.string.edit_profile_name_label),
                icon = Icons.Default.Person,
                supportingText = "",
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )

            InputText(
                value = username,
                onValueChange = { username = it },
                label = stringResource(id = R.string.edit_profile_username_label),
                icon = Icons.Default.Person,
                supportingText = "", //  Añadir un mensaje de error por la validación
                onValidate = { false }, // Implementar logiva de validacion
                modifier = Modifier.fillMaxWidth()
            )

            InputText(
                value = email,
                onValueChange = { /* Email no editable */ },
                label = stringResource(id = R.string.edit_profile_email_label),
                icon = Icons.Default.AlternateEmail,
                readOnly = true,
                enabled = false,
                supportingText = stringResource(id = R.string.edit_profile_email_info),
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )

            InputText(
                value = city,
                onValueChange = { city = it },
                label = stringResource(id = R.string.edit_profile_city_label),
                icon = Icons.Default.LocationOn,
                supportingText = "",
                onValidate = { false },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // TODO: Implementar la lógica para guardar los cambios del perfil
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.edit_profile_save_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    MaterialTheme {
        EditProfile()
    }
}