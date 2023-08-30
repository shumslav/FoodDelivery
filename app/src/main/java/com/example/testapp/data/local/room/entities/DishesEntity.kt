package com.example.testapp.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testapp.data.local.room.typeConverters.TagsConverter


@Entity(tableName = "dishes")
@TypeConverters(TagsConverter::class)
data class DishesEntity(
    @PrimaryKey val id: Long,
    val name:String,
    val price: Int,
    val weight: Int,
    val description:String,

    @ColumnInfo(name = "image_url")
    val imageUrl:String,
    val tags: List<String>
)
