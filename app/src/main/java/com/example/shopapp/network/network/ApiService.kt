package com.example.shopapp.network.network

import com.example.shopapp.models.CompleteProfile
import com.example.shopapp.models.PostItem
import com.example.shopapp.models.SignIn
import com.example.shopapp.models.SignUp
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): Response<SignIn>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@Field("email") email: String, @Field("full_name") full_name: String,  @Field("password") password: String): Response<SignUp>

    @GET("posts")
    suspend fun getPosts(): Response<List<PostItem>>

    @FormUrlEncoded
    @POST("profile")
    suspend fun completeProfile(@Field("user_id") userID: Int): Response<CompleteProfile>

    @GET("https://maps.googllepis.com/maps/api/geocode/json")
    suspend fun geoCode(
        @Query("address") address: String,
        @Query("key") key: String
    ): Response<MapResult> // aq ratipis dataklasi unda ver davamugame magari gaviwede :(
}