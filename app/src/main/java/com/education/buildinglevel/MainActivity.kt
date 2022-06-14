package com.education.buildinglevel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.buildinglevel.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, SimpleLevel())
            .commit()
        val bottomNav: BottomNavigationView = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.landscape_level -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, SimpleLevel())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.digital_level -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment, ElectronicLevel())
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        /*requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        binding.goToFragment1Button.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, SimpleLevel())
                .commit()
        }
        binding.goToFragment2Button.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, ElectronicLevel())
                .commit()
        }*/
    }
}