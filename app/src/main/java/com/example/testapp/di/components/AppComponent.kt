package com.example.testapp.di.components

import com.example.testapp.di.modules.RemoteModule
import com.example.testapp.fragments.MainFragment
import com.example.testapp.viewModels.MainViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [RemoteModule::class])
@Singleton
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)
    fun inject(fragment:MainFragment)
}