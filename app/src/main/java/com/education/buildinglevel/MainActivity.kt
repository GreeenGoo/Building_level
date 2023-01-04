package com.education.buildinglevel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.buildinglevel.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, LandscapeLevel())
            .commit()
        val bottomNavView: BottomNavigationView = binding.bottomNavigation
        initBottomNavView(bottomNavView)
    }

    private fun initBottomNavView(bottomNav: BottomNavigationView){
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.landscape_level -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, LandscapeLevel())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.digital_level -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, DigitalLevel())
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        bottomNav.setOnItemReselectedListener{}
    }
}