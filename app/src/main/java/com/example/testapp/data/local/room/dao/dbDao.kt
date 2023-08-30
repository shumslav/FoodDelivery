package com.example.testapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.testapp.data.local.room.entities.CategoriesEntity
import com.example.testapp.data.local.room.entities.DishesEntity
import com.example.testapp.data.local.room.typeConverters.TagsConverter

@Dao
@TypeConverters(TagsConverter::class)
interface DbDao {

    @Insert(entity = CategoriesEntity::class)
    fun insertCategory(category: CategoriesEntity)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): List<CategoriesEntity>

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getCategoryById(categoryId: Long): CategoriesEntity

    @Insert(entity = DishesEntity::class)
    fun insertDish(dish: DishesEntity)

    @Query("SELECT * FROM dishes")
    fun getAllDishes(): List<DishesEntity>

    @Query("SELECT * FROM dishes WHERE id = :dishId")
    fun getDishById(dishId: Long): DishesEntity

}