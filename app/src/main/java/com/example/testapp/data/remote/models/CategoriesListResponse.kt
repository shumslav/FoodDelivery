package com.example.testapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class CategoriesListResponse(
    @SerializedName("сategories")
    val categories:List<CategoriesListItem>
    )

