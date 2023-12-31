package com.example.testapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class CategoriesListItem(
    val id: Int,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String
)
