package com.education.buildinglevel

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class SimpleLevel : Fragment() {
    private lateinit var sManager: SensorManager
    var data1 = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.simple_level_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lRotation = activity?.findViewById<LinearLayout>(R.id.main_level_stick)
        sManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accrViewModel = ViewModelProvider(this).get(AccelerometersData::class.java)
        accrViewModel.getSensorConditions(sManager)
        accrViewModel.outGravity().observe(viewLifecycleOwner, Observer {
            lRotation?.rotation = accrViewModel.getRotate()
            lRotation?.setBackgroundColor(accrViewModel.getColor())
        })

    }
}