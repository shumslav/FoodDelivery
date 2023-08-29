package com.example.testapp.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoriesEntity(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)
