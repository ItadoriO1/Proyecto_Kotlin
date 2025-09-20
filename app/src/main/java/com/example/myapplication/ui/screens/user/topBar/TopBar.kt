package com.example.myapplication.ui.screens.user.topBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarUser(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToNotification: () -> Unit = {}
){
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.title_user))
        },
        actions = {
            IconButton(onClick = {
                onNavigateToNotification()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = ""
                )
            }
            IconButton(onClick = {
                onNavigateToProfile()
            }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = ""
                )
            }
        }
    )
}