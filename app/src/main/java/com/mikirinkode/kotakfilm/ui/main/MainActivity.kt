package com.mikirinkode.kotakfilm.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mikirinkode.kotakfilm.R
import com.mikirinkode.kotakfilm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val navView: BottomNavigationView = navBottom
            val navController =
                navHostFragmentActivityHome.getFragment<NavHostFragment>().navController
            val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_search, R.id.nav_movie, R.id.nav_tv_show, R.id.nav_playlist
            ).build()
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }
}