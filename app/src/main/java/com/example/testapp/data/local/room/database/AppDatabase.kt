package com.example.testapp.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.data.local.room.dao.DbDao
import com.example.testapp.data.local.room.entities.CategoriesEntity
import com.example.testapp.data.local.room.entities.DishesEntity

@Database(
    version = 1,
    entities = [CategoriesEntity::class, DishesEntity::class]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getDbDao(): DbDao
}