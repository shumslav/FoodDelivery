package com.example.testapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testapp.App
import com.example.testapp.data.remote.TestApi
import com.example.testapp.data.remote.models.CategoriesListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel(myApp: Application) : AndroidViewModel(myApp) {

    @Inject
    lateinit var testApi: TestApi

    val categories: MutableLiveData<List<CategoriesListItem>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    init {
        App.appComponent.inject(this)
        compositeDisposable.add(testApi.getCategoriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                categories.value = it.categories
            }, {})
        )
    }
}