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
import android.widget.TextView
import androidx.fragment.app.Fragment

class ElectronicLevel : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.electronic_level_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvSensor = activity?.findViewById<TextView>(R.id.tv_sensor_electronic)
        val sManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val aData = AccelerometersData()
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                val rData = aData.getOptions(event).toInt()
                tvSensor?.text = "$rData°"
                tvSensor?.setTextColor(choiceOfColor(rData))
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