package com.example.testapp.data

import com.example.testapp.data.local.room.dao.DbDao
import com.example.testapp.data.remote.TestApi
import javax.inject.Inject

class DataRepository(@Inject private val dbDao: DbDao, @Inject private val testApi: TestApi) {

    suspend fun getAllCategoriesFromLocal(){
    }
}