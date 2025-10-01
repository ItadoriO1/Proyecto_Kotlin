package com.example.myapplication.ui.screens.user.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewModel.NotificationViewModel
import com.example.myapplication.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(
    notificationsViewModel: NotificationViewModel,
){
    val notifications by notificationsViewModel.notification.collectAsState()

    Scaffold(){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item { // Header para notificaciones
                    Text(
                        text = stringResource(R.string.notifications_profile),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(notifications){ notification ->
                    ListItem(
                        headlineContent = { notification.comment?.let { Text(it) } }
                    )
                }
            }
            )
    }
}