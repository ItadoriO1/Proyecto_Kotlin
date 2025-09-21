package com.example.myapplication.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R

@Composable
fun PlaceType.getDisplayName(): String {
    val resourceId = when (this) {
        PlaceType.RESTAURANTE -> R.string.place_type_restaurante
        PlaceType.BAR -> R.string.place_type_bar
        PlaceType.HOTEL -> R.string.place_type_hotel
        PlaceType.PARQUE -> R.string.place_type_parque
        PlaceType.TIENDA -> R.string.place_type_tienda
        PlaceType.MUSEO -> R.string.place_type_museo
        PlaceType.CINE -> R.string.place_type_cine
        PlaceType.TEATRO -> R.string.place_type_teatro
        PlaceType.CAFETERIA -> R.string.place_type_cafeteria
        PlaceType.GIMNASIO -> R.string.place_type_gimnasio
        PlaceType.BIBLIOTECA -> R.string.place_type_biblioteca
        PlaceType.HOSPITAL -> R.string.place_type_hospital
        PlaceType.FARMACIA -> R.string.place_type_farmacia
        PlaceType.SUPERMERCADO -> R.string.place_type_supermercado
        PlaceType.AEROPUERTO -> R.string.place_type_aeropuerto
        PlaceType.ESTACION_TREN -> R.string.place_type_estacion_tren
        PlaceType.CENTRO_COMERCIAL -> R.string.place_type_centro_comercial
        PlaceType.ZOOLOGICO -> R.string.place_type_zoologico
        PlaceType.MONUMENTO -> R.string.place_type_monumento
        PlaceType.OTRO -> R.string.place_type_otro
    }
    return stringResource(id = resourceId)
}