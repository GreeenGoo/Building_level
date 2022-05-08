package com.education.buildinglevel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.buildinglevel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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