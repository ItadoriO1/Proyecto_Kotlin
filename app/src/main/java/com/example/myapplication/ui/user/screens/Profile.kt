package com.example.myapplication.ui.user.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.InputText
import com.example.myapplication.R
@Composable
fun Profile(
    onNavigateToEditProfile: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileHeader()
        PersonalInfoCard()
        ActionsCard(
            onNavigateToEditProfile = onNavigateToEditProfile,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}

@Composable
private fun ProfileHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar de Usuario",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(text = "Usuario Normal", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(text = "@usuario", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat(count = "3", label = "Registros") //se cambiaria con la informacion del usuario
                ProfileStat(count = "12", label = "Favoritos") //se cambiaria con la informacion del usuario
                ProfileStat(count = "8", label = "Reseñas") //se cambiaria con la informacion del usuario
            }
        }
    }
}

@Composable
private fun ProfileStat(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

@Composable
private fun PersonalInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = stringResource(id = R.string.profile_personal_info_header), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            
            InputText(
                label = "Nombre completo",
                value = "Usuario Normal",
                icon = Icons.Default.Person,
                onValueChange = {},
                onValidate = { false },
                readOnly = true,
                supportingText = ""
            )
            InputText(
                label = "Nombre de usuario",
                value = "usuario",
                icon = Icons.Default.Person,
                onValueChange = {},
                onValidate = { false },
                readOnly = true,
                supportingText = ""
            )
            InputText(
                label = "Email",
                value = "user@test.com",
                icon = Icons.Default.AlternateEmail,
                supportingText = "El email no se puede modificar",
                onValueChange = {},
                onValidate = { false },
                readOnly = true,
                enabled = false
            )
            InputText(
                label = "Ciudad",
                value = "Guadalajara",
                icon = Icons.Default.LocationOn,
                onValueChange = {},
                onValidate = { false },
                readOnly = true,
                supportingText = ""
            )
        }
    }
}

@Composable
private fun ActionsCard(
    onNavigateToEditProfile: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = stringResource(id = R.string.profile_actions_header), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            
            ActionItem(text = stringResource(id = R.string.profile_action_edit_profile), icon = Icons.Default.Edit, onClick = onNavigateToEditProfile)
            Divider()
            ActionItem(text = stringResource(id = R.string.profile_action_settings), icon = Icons.Default.Settings, onClick = { /*TODO*/ })
            Divider()
            ActionItem(text = stringResource(id = R.string.profile_action_logout), icon = Icons.AutoMirrored.Filled.Logout, onClick = onNavigateToLogin)
        }
    }
}

@Composable
private fun ActionItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(imageVector = icon, contentDescription = text, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = text, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        Profile(
            onNavigateToEditProfile = { /* Preview doesn't navigate */ },
            onNavigateToLogin = { /* Preview doesn't navigate */ }
        )
    }
}
