package com.example.shopapp.models


import com.google.gson.annotations.SerializedName

data class PersonInfo(
    val OK: Boolean,
    @SerializedName("profile completed")
    val profileCompleted: Boolean
)