package com.example.testapp.data.remote

import com.example.testapp.data.remote.models.CategoriesListResponse
import retrofit2.http.GET

interface TestApi {
    @GET("./058729bd-1402-4578-88de-265481fd7d54")
    fun getCategoriesList():CategoriesListResponse
}