package com.example.shopapp.network.network

import com.example.shopapp.models.Item
import com.example.shopapp.models.SignUp
import com.example.shopapp.models.SignIn
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("email") email: String, @Field("Password") password: String): Response<ResultHandle<SignIn>>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@Field("email") email: String, @Field("full_name") full_name: String,  @Field("password") password: String): Response<ResultHandle<SignUp>>

    @GET("posts")
    suspend fun getPosts(): Response<ResultHandle<MutableList<Item>>>
}