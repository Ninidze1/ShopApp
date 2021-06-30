package com.example.shopapp.network.repository

import com.example.shopapp.models.User
import com.example.shopapp.network.network.ResultHandle

interface LoginRepository {
    suspend fun logIn(email: String, password: String): ResultHandle<User>
}