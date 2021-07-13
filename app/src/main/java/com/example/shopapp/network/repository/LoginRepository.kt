package com.example.shopapp.network.repository

import com.example.shopapp.models.SignIn
import com.example.shopapp.models.SignUp
import com.example.shopapp.network.network.ResultHandle

interface LoginRepository {
    suspend fun logIn(email: String, password: String): ResultHandle<SignIn>
    suspend fun register(email: String, fullName: String, password: String): ResultHandle<SignUp>
}