package com.example.myapplication.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.navigation.LocalMainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    onNavigateToPlaceDetail: (String) -> Unit
){

    val placesViewModel = LocalMainViewModel.current.placeViewModel

    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = modifier.fillMaxWidth(), // Ocupará todo el ancho disponible
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { query = it },
                onSearch = { expanded = false },
                expanded = expanded,
                onExpandedChange = {expanded = it},
                placeholder = {
                    Text(stringResource(R.string.search_title))
                }
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        if(query.isNotEmpty()){
            val places = placesViewModel.getPlaceByName(query)
            LazyColumn {
                items(places){
                    ListItem(
                        headlineContent = { Text(it.name) },
                        supportingContent = { Text(it.description) },
                        leadingContent = {
                            AsyncImage(
                                model = it.image.firstOrNull(),
                                contentDescription = "Imagen de ${it.name}",
                                modifier = modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                onClick = {
                                    query = it.name
                                    expanded = false
                                    onNavigateToPlaceDetail(it.id)
                                }
                            )
                    )
                }
            }
        }

        if(query.isEmpty()){
            val places = placesViewModel.getPlaceByName(query)
            LazyColumn {
                items(places){
                    ListItem(
                        headlineContent = { Text(it.name) },
                        supportingContent = { Text(it.description) },
                        leadingContent = {
                            AsyncImage(
                                model = it.image.firstOrNull(),
                                contentDescription = "Imagen de ${it.name}",
                                modifier = modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                onClick = {
                                    query = it.name
                                    expanded = false
                                    onNavigateToPlaceDetail(it.id)
                                }
                            )
                    )
                }
            }
        }
    }
}