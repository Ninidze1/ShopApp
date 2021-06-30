package com.example.shopapp.network.network

import com.example.shopapp.models.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): Response<ResultHandle<User>>
}