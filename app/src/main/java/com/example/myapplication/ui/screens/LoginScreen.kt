package co.edu.eam.lugaresapp.ui.screens

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
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.myapplication.R
import com.example.myapplication.ui.components.InputText

@Composable
fun LoginForm(
    onNavigateToRegister: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
){

    var email by rememberSaveable { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            content = {

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


                Button(
                    onClick = {
                        onNavigateToHome()
                    }
                ){
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = stringResource(R.string.btn_login)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = stringResource(R.string.btn_login))
                }

                Button(
                    onClick = {
                        onNavigateToRegister()
                    }
                ){
                    Icon(
                        imageVector = Icons.Outlined.PersonAdd,
                        contentDescription = stringResource(R.string.btn_create)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = stringResource(R.string.btn_create))
                }
            }
        )
    }
}