package com.example.shopapp.models


import com.google.gson.annotations.SerializedName

data class LoginResp(
    @SerializedName("token")
    val token: String?,
    @SerializedName("user_id")
    val userId: Int?
)