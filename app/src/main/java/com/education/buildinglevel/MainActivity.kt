package com.education.buildinglevel

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.buildinglevel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

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
        }
    }
}