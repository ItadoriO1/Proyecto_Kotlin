package com.example.myapplication.ui.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.InputText

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit = {}
){

    var name by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
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

                InputText(
                    value = city,
                    label = stringResource(R.string.text_city),
                    supportingText = stringResource(R.string.text_error_city),
                    onValueChange = {
                        city = it
                    },
                    onValidate = {
                        city.length < 5 || city.isBlank()
                    },
                    icon = Icons.Outlined.LocationOn
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
                        onNavigateToLogin()
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