package com.example.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testapp.viewModels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

//    private val locationManager =  this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    @RequiresApi(Build.VERSION_CODES.S)
//    private val locationProvider = locationManager.getProviderProperties(LocationManager.GPS_PROVIDER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        bottomMenu.setupWithNavController(navController)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

//        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

//        if (!gpsEnabled){
//            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//            startActivity(settingsIntent)
//        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container_main).navigateUp()
    }
}