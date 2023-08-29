package com.example.testapp.data.models

import com.google.gson.annotations.SerializedName

data class DishesItem(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    @SerializedName("image_url")
    val imgUrl: String,
    @SerializedName("tegs")
    val tags: List<String>
)
