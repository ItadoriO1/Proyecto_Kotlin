package com.example.myapplication.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.DropdownMenu
import com.example.myapplication.ui.components.InputText
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit = {}
){

    var name by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var countryes = listOf("Colombia")
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            androidx.compose.material3.SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                content = {

                    InputText(
                        value = name,
                        label = stringResource(R.string.text_name),
                        supportingText = stringResource(R.string.text_error_name),
                        onValueChange = {
                            name = it
                        },
                        onValidate = {
                            name.isBlank() || !name.matches(Regex("^[A-Za-z ]+$"))
                        },
                        icon = Icons.Outlined.Person
                    )

                    InputText(
                        value = userName,
                        label = stringResource(R.string.text_userName),
                        supportingText = stringResource(R.string.text_error_user_name),
                        onValueChange = {
                            userName = it
                        },
                        onValidate = {
                            userName.length < 5 || userName.isBlank()
                        },
                        icon = Icons.Outlined.Person
                    )

                    InputText(
                        value = email,
                        label = stringResource(R.string.text_email),
                        supportingText = stringResource(R.string.text_error_email),
                        onValueChange = {
                            email = it
                        },
                        onValidate = {
                            email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        },
                        icon = Icons.Outlined.Email
                    )

                    DropdownMenu(
                        label = stringResource(R.string.txt_dropwnMenu_pais),
                        supportingText = stringResource(R.string.txt_dropwnMenu_pais_error),
                        list = countryes,
                        icon = Icons.Outlined.Place,
                        onValueChange = {
                            country = it
                        },
                        onValidate =  {
                            country.isBlank()
                        }
                    )

                    InputText(
                        value = phone,
                        label = stringResource(R.string.text_phone),
                        supportingText = stringResource(R.string.text_error_phone),
                        onValueChange = {
                            phone = it
                        },
                        onValidate = {
                            !Patterns.PHONE.matcher(phone).matches() || phone.isBlank()
                        },
                        icon = Icons.Outlined.Phone
                    )

                    InputText(
                        value = password,
                        label = stringResource(R.string.text_password),
                        supportingText = stringResource(R.string.text_error_password),
                        onValueChange = {
                            password = it
                        },
                        onValidate = {
                            password.length < 5 || password.isBlank()
                        },
                        icon = Icons.Outlined.Key,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    InputText(
                        value = confirmPassword,
                        label = stringResource(R.string.text_confirm_password),
                        supportingText = stringResource(R.string.text_error_confirm_password),
                        onValueChange = {
                            confirmPassword = it
                        },
                        onValidate = {
                            password != confirmPassword || confirmPassword.isBlank()
                        },
                        icon = Icons.Outlined.Key,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Button(
                        onClick = {
                            val isValid = (name.isBlank() || !name.matches(Regex("^[A-Za-z ]+$"))) ||
                                    (userName.length < 5 || userName.isBlank()) ||
                                    (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) ||
                                    (country.isBlank()) ||
                                    (!Patterns.PHONE.matcher(phone).matches() || phone.isBlank()) ||
                                    (password.length < 5 || password.isBlank()) ||
                                    (password != confirmPassword || confirmPassword.isBlank())
                            if(!isValid){
                                onNavigateToLogin()
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Por favor, completa todos los campos correctamente",
                                        withDismissAction = true
                                    )
                                }
                            }
                        }
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.btn_register)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(stringResource(R.string.btn_create))
                    }
                }
            )
        }
    }
}