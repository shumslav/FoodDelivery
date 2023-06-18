package com.example.testapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.data.remote.TestApi
import com.example.testapp.databinding.FragmentMainBinding
import com.example.testapp.viewModels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var testApi: TestApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        return binding.root
    }
}