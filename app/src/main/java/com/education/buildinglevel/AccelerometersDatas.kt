package com.education.buildinglevel

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView

class AccelerometersDatas(private val activity: Activity, val idName: Int) {
    lateinit var sManager: SensorManager
    private var magnetic = FloatArray(9)
    private var gravity = FloatArray(9)

    private var accrs = FloatArray(3)
    private var magf = FloatArray(3)
    private var values = FloatArray(3)

    fun getOptions() {
        val tvSensor = activity.findViewById<TextView>(idName)
        sManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                /*val value = sEvent?.values
                val sData = "Y: ${value?.get(1)}"
                tvSensor.text = sData*/
                when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER->accrs = event.values.clone()
                    Sensor.TYPE_MAGNETIC_FIELD->magf = event.values.clone()
                }

                SensorManager.getRotationMatrix(gravity, magnetic, accrs, magf)
                val outGravity = FloatArray(9)
                SensorManager.remapCoordinateSystem(gravity,
                    SensorManager.AXIS_X,
                    SensorManager.AXIS_Z,
                    outGravity)
                SensorManager.getOrientation(outGravity, values)
            }


            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sManager.registerListener(sListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL)
    }

}