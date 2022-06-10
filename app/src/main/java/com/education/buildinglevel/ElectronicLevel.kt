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
        val values = FloatArray(SIZE_OF_ARRAY_THREE)
        val tvSensor = activity?.findViewById<TextView>(R.id.tv_sensor_electronic)
        val sManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                /*when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER -> accrs = event.values.clone()
                    Sensor.TYPE_MAGNETIC_FIELD -> magf = event.values.clone()
                }
                val outGravity =
                    AccelerometersDatas().getOptions(accrs, magf, gravity, magnetic)
                SensorManager.getOrientation(outGravity, values)
                val degree = values[SECOND_INDEX_OF_ARRAY] * DEGREE_COEFFICIENT
                val rData: Int = DEGREE_CORRECTION + degree.toInt()*/
                val color = choiceOfColor(rData)
                tvSensor?.text = "$rData"
                tvSensor?.setTextColor(color)
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

    companion object {
        private const val SECOND_INDEX_OF_ARRAY = 2
        private const val DEGREE_COEFFICIENT = 57.2958f
        private const val SIZE_OF_ARRAY_NINE = 9
        private const val SIZE_OF_ARRAY_THREE = 3
        private const val DEGREE_CORRECTION = 90

    }
}