package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Role
import com.example.myapplication.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.String

class UserViewModel : ViewModel()  {
    private val _user = MutableStateFlow(emptyList<User>());
    val users: StateFlow<List<User>> = _user.asStateFlow();

    init {
        loadUsers();
    }

    private fun loadUsers() {
        _user.value = listOf(
            User(
                id = "1",
                name = "Admin",
                userName = "Admin",
                role = Role.ADMIN,
                email = "Admin@gmail.com",
                phone = "123456789",
                password = "123456",
                country = "Colombia",
                city = "Armenia"
            ),
            User(
                id = "2",
                name = "Test",
                userName = "Test",
                role = Role.USER,
                email = "Test@gmail.com",
                phone = "123456789",
                password = "123456",
                country = "España",
                city = "Barcelona"
            )
        )
    }

    fun getUserById(id: String): User? {
        return _user.value.find { it.id == id }
    }

    fun getUserByUserName(userName: String): User? {
        return _user.value.find { it.userName == userName }
    }

    fun getUserByEmail(email: String): User? {
        return _user.value.find { it.email == email }
    }

    fun createUser(user: User) {
        _user.value = _user.value + user
    }

    fun updateUser(user: User) {
        val index = _user.value.indexOfFirst { it.id == user.id }
        if (index != -1) {
            _user.value = _user.value.toMutableList().apply {
                set(index, user)
            }
        }
    }

    fun deleteUser(user: User) {
        _user.value = _user.value - user
    }

    fun clearUsers() {
        _user.value = emptyList()
    }

    fun login(userEmail: String, password: String): User? {
        return _user.value.find { it.email == userEmail && it.password == password }
    }
}