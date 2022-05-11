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
    lateinit var sManager: SensorManager
    private var magnetic = FloatArray(9)
    private var gravity = FloatArray(9)
    private var accrs = FloatArray(3)
    private var magf = FloatArray(3)
    private var values = FloatArray(3)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.electronic_level_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvSensor = activity?.findViewById<TextView>(R.id.tvSensorElectronic)
        sManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER -> accrs = event.values.clone()
                    Sensor.TYPE_MAGNETIC_FIELD -> magf = event.values.clone()
                }
                val outGravity =
                    AccelerometersDatas().getOptions(accrs, magf, gravity, magnetic)
                SensorManager.getOrientation(outGravity, values)
                val degree = values[2] * 57.2958f
                val rData: Int = 90 + degree.toInt()
                val color = if (rData == 0) {
                    Color.GREEN
                } else {
                    Color.RED
                }
                tvSensor?.text = "$rData°"
                tvSensor?.setTextColor(color)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sManager.registerListener(sListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL)
    }
}