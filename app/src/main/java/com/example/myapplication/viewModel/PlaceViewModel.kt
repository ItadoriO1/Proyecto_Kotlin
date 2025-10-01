package com.example.myapplication.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Location
import com.example.myapplication.model.Place
import com.example.myapplication.model.PlaceType
import com.example.myapplication.model.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class PlaceViewModel: ViewModel() {

    private val _places = MutableStateFlow(emptyList<Place>());
    val places: StateFlow<List<Place>> = _places.asStateFlow();

    init {
        loadPlaces();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadPlaces() {
        _places.value = listOf(
            Place(
                id = "1",
                name = "Ichiraku Ramen",
                description = "Comida Japonesa",
                address = "Calle 123",
                location = Location(1.0, 2.0),
                schedule = listOf(
                    Schedule(
                        day = "Martes",
                        open = LocalTime.of(8, 0),
                        close = LocalTime.of(20, 0)
                    ),
                ),
                phones = listOf("123456789"),
                category = PlaceType.RESTAURANTE,
                comments = null,
                image = listOf("https://s.soyrice.com/media/20250214044346/Authentic-Naruto-Ramen-Ichiraku-Recipe-with-Flavorful-Broth_done3.jpg"),
                userId = null
            ),
            Place(
                id = "2",
                name = "Taco bell",
                description = "Comida Mexicana",
                address = "Calle 321",
                location = Location(3.5, 7.2),
                schedule = listOf(),
                phones = listOf("123456789"),
                category = PlaceType.RESTAURANTE,
                comments = null,
                image = listOf("https://www.allrecipes.com/thmb/5vfVjThzZsweDidPCuRLZLVTut4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/ar-taste-test-taco-bell-unique-1-4x3-94016192e88a428f925c209fe29081a3.jpg"),
                userId = null
            )
        )
    }

    fun createPlace(place: Place) {
        _places.value = _places.value + place
    }

    fun updatePlace(place: Place) {
        val index = _places.value.indexOfFirst { it.id == place.id }
        if (index != -1) {
            _places.value = _places.value.toMutableList().apply {
                set(index, place)
            }
        }
    }

    fun deletePlace(place: Place) {
        _places.value = _places.value - place
    }

    fun clearPlaces() {
        _places.value = emptyList()
    }

    fun getPlaceById(id: String): Place? {
        return _places.value.find { it.id == id }
    }

    fun getPlaceByName(name: String): Place?{
        return _places.value.find { it.name == name }
    }

    fun getPlaceByCategory(category: PlaceType): List<Place>{
        return _places.value.filter { it.category == category }
    }

    fun getPlaceByIdUser(idUser: String): List<Place>{
        return _places.value.filter { it.userId == idUser }
    }
}