package com.example.testapp

import android.app.Application
import com.example.testapp.di.components.AppComponent
import com.example.testapp.di.components.DaggerAppComponent

class App:Application() {
    companion object{

        lateinit var appComponent:AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder().build()
    }
}