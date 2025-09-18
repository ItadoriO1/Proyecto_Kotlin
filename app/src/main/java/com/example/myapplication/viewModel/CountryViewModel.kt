package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.CountryWithCities
import com.example.myapplication.ui.config.network.ApiService
import com.example.myapplication.ui.config.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val repository = CountryRepository(ApiService.create())
    private val _countries = MutableStateFlow<List<CountryWithCities>>(emptyList())
    val countries: StateFlow<List<CountryWithCities>> = _countries
    private val _selectedCountry = MutableStateFlow<CountryWithCities?>(null)
    val selectedCountry: StateFlow<CountryWithCities?> = _selectedCountry
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadCountries()
    }

    fun loadCountries() {
        viewModelScope.launch {
            try {
                val response = repository.fetchCountriesWithCities()
                if (!response.error) {
                    _countries.value = response.data
                } else {
                    _error.value = response.msg
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun selectCountry(country: CountryWithCities) {
        _selectedCountry.value = country
    }
}