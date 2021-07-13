package com.example.shopapp.models


import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("category_id")
    val categoryId: Int?,
    val description: String?,
    @SerializedName("full_name")
    val fullName: String?,
    val id: Int?,
    val owner: Int?,
    val price: Double?,
    @SerializedName("price_type")
    val priceType: String?,
    val tags: String?,
    val title: String?,
    val urls: List<Url>?
) {
    data class Url(
        val format: String?,
        @SerializedName("image_height")
        val imageHeight: Int?,
        @SerializedName("image_width")
        val imageWidth: Int?,
        val url: String?
    )
}