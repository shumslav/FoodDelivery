package com.example.testapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.local.room.dao.DbDao
import com.example.testapp.data.local.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDbModule {



    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context,AppDatabase::class.java, "app_database.db")
            .build()

    @Provides
    @Singleton
    fun provideDbDao(appDatabase: AppDatabase): DbDao = appDatabase.getDbDao()
}