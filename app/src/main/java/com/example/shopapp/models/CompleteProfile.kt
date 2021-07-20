package com.example.shopapp.models

import com.google.gson.annotations.SerializedName

data class CompleteProfile(
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("profile-completed")
    val profileCompleted: Boolean,
    val address: String?,
    val card_holder_name: String?,
    val card_number: String?,
    val expiry_date: String?,
    val floor_apartment: String?,
    val profile_url: String?,
    val security_code: String?
)
