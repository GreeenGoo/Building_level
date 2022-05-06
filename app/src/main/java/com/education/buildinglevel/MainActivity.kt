package com.education.buildinglevel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.buildinglevel.databinding.ElectronicLevelFragmentBinding
import com.education.buildinglevel.databinding.SimpleLevelFragmentBinding

class MainActivity : AppCompatActivity() {

    private lateinit var simpleBinding: SimpleLevelFragmentBinding
    private lateinit var electronicBinding: ElectronicLevelFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}