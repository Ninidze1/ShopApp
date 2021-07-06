package com.example.shopapp.models


import com.google.gson.annotations.SerializedName

data class SignUp(
    @SerializedName("OK")
    val oK: Boolean?,
    @SerializedName("registered")
    val registered: Boolean?
)