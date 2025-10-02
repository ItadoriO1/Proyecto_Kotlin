package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    supportingText: String,
    onValueChange: (String) -> Unit,
    onValidate: (String) -> Boolean,
    icon: ImageVector? = null,
    visualTransformation: VisualTransformation? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default // Nuevo parámetro
){

    var isError by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        value = value,
        isError = isError,
        supportingText = {
            if(isError){
                Text(text = supportingText)
            }
        },
        leadingIcon = {
            if(icon != null){
                Icon(
                    imageVector = icon,
                    contentDescription = label
                )
            }
        },
        visualTransformation = visualTransformation ?: VisualTransformation.None,
        onValueChange = {
            onValueChange(it)
            if (!readOnly && enabled) {
                isError = onValidate(it)
            }
        },
        readOnly = readOnly,
        enabled = enabled,
        keyboardOptions = keyboardOptions // Pasa el nuevo parámetro
    )
}