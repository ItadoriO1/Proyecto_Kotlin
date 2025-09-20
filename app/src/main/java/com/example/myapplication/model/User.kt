package com.example.myapplication.model

data class User (
    val id: String,
    val name: String,
    val userName: String,
    val role: Role,
    val email: String,
    val phone: String,
    val password: String,
    val country: String,
    val city: String) {
}