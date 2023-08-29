package com.example.testapp.data.remote

import com.example.testapp.data.models.CategoriesListResponse
import com.example.testapp.data.models.DishesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface TestApi {
    @GET("./v3/058729bd-1402-4578-88de-265481fd7d54")
    fun getCategoriesList():Single<CategoriesListResponse>

    @GET("./v3/aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    fun getDishesList():Single<DishesResponse>
}