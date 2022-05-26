package com.education.buildinglevel

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.LinearLayout

class AccelerometersDatas {
    private var sManager: SensorManager = MainActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    private var accrs = FloatArray(3)
    private var magf = FloatArray(3)
    private var magnetic = FloatArray(9)
    private var gravity = FloatArray(9)
    private var outGravity = FloatArray(4)

    fun getSensorConditions() : FloatArray {

        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER -> accrs = event.values.clone()
                    Sensor.TYPE_MAGNETIC_FIELD -> magf = event.values.clone()
                }
                SensorManager.getRotationMatrix(gravity, magnetic, accrs, magf)
                SensorManager.remapCoordinateSystem(
                    gravity,
                    SensorManager.AXIS_X,
                    SensorManager.AXIS_Z,
                    outGravity
                )
                /*SensorManager.getOrientation(outGravity, values)
                val degree = values[2] * 57.2958f
                var rotate = degree + 90
                lRotation?.rotation = rotate
                val rData: Int = 90 + degree.toInt()
                val color = if (rData == 0) {
                    Color.GREEN
                } else {
                    Color.RED
                }
                lRotation?.setBackgroundColor(color)*/
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sManager.registerListener(sListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL)
        return outGravity
    }



}

/*private val outGravity = FloatArray(9)
    fun getOptions(accrs: FloatArray, magf: FloatArray, gravity: FloatArray, magnetic: FloatArray) : FloatArray {
        SensorManager.getRotationMatrix(gravity, magnetic, accrs, magf)
        SensorManager.remapCoordinateSystem(
            gravity,
            SensorManager.AXIS_X,
            SensorManager.AXIS_Z,
            outGravity
        )
        return outGravity
    }*/