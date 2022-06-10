package com.education.buildinglevel

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class SimpleLevel : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.simple_level_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lRotation = activity?.findViewById<LinearLayout>(R.id.main_level_stick)
        val sManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val aData = AccelerometersData()
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                val rData = aData.getOptions(event)
                lRotation?.rotation = rData
                lRotation?.setBackgroundColor(choiceOfColor(rData.toInt()))
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sManager.registerListener(sListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun choiceOfColor(data: Int) : Int {
        return if (data == 0) {
            Color.GREEN
        } else {
            Color.RED
        }
    }
}