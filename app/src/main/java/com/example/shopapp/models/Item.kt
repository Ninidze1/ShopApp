package com.example.shopapp.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("category_id")
    val categoryId: Int?,
    val description: String?,
    val id: Int?,
    val owner: Int?,
    val price: Double?,
    @SerializedName("price_type")
    val priceType: String?,
    val tags: String?,
    val title: String?,
    val urls: List<String?>?
)