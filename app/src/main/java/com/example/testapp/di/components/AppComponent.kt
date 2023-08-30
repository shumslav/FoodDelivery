package com.example.testapp.di.components

import android.app.Application
import com.example.testapp.data.DataRepository
import com.example.testapp.di.modules.ContextModule
import com.example.testapp.di.modules.LocalDbModule
import com.example.testapp.di.modules.RemoteModule
import com.example.testapp.fragments.MainFragment
import com.example.testapp.viewModels.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [RemoteModule::class, LocalDbModule::class, ContextModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build():AppComponent
    }

    fun inject(mainViewModel: MainViewModel)
    fun inject(fragment:MainFragment)

    fun inject(dataRepository: DataRepository)
}