package com.education.buildinglevel

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView
import com.education.buildinglevel.databinding.ActivityMainBinding

class AccelerometersDatas(private val activity: Activity, val idName: Int) {
    lateinit var sManager: SensorManager

    fun getOptions (){
        val tvSensor = activity.findViewById<TextView>(idName)
        sManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sListener = object : SensorEventListener{
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val sData = "Y: ${value?.get(1)}"
                tvSensor.text = sData
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

}