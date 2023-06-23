package com.example.testapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testapp.App
import com.example.testapp.data.remote.TestApi
import com.example.testapp.data.remote.models.CategoriesListItem
import com.example.testapp.data.remote.models.DishesItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel(myApp: Application) : AndroidViewModel(myApp) {

    @Inject
    lateinit var testApi: TestApi

    val categories: MutableLiveData<List<CategoriesListItem>> = MutableLiveData()
    val dishes: MutableLiveData<List<DishesItem>> = MutableLiveData()
    val dishesCategories: MutableLiveData<List<String>> = MutableLiveData()
    val selectedCategory: MutableLiveData<String> = MutableLiveData()
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
        compositeDisposable.add(testApi.getDishesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                dishes.value = it.dishes
                dishesCategories.value = getAllCategories(it.dishes)
                if (selectedCategory.value.isNullOrEmpty()){
                    selectedCategory.value = dishesCategories.value?.first()
                }
            }, {}))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun getAllCategories(dishes: List<DishesItem>): List<String> {
        val list = mutableListOf<String>()
        dishes.forEach {dish ->
            dish.tags.forEach{
                if (!list.contains(it))
                    list.add(it)
            }
        }
        list.sort()
        return list
    }
}