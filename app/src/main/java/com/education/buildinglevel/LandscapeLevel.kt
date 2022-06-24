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
import androidx.fragment.app.Fragment
import com.education.buildinglevel.databinding.LandscapeLevelFragmentBinding

class LandscapeLevel : Fragment() {
    lateinit var binding: LandscapeLevelFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LandscapeLevelFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainStickView = binding.mainLevelStick
        val sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val accelerometersData = AccelerometersData()
        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                val rData = accelerometersData.getOptions(event)
                mainStickView.rotation = -rData
                mainStickView.setBackgroundColor(choiceOfColor(rData.toInt()))
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        sensorManager.registerListener(
            sensorEventListener,
            accelerometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
            sensorEventListener,
            magneticSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun choiceOfColor(data: Int): Int {
        return if (data == 0) {
            Color.GREEN
        } else {
            Color.RED
        }
    }
}