package com.example.shopapp.models


import com.google.gson.annotations.SerializedName

data class PersonInfo(
    @SerializedName("address")
    val address: String?=null,
    @SerializedName("card_holder_name")
    val cardHolderName: String?=null,
    @SerializedName("card_number")
    val cardNumber: String?=null,
    @SerializedName("email")
    val email: String?,
    @SerializedName("expiry_date")
    val expiryDate: String?=null,
    @SerializedName("floor_apartment")
    val floorApartment: String?=null,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("profile-completed")
    val profileCompleted: Boolean?=null,
    @SerializedName("profile_url")
    val profileUrl: String?=null,
    @SerializedName("security_code")
    val securityCode: String?
)