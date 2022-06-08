package com.education.buildinglevel

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccelerometersData() : ViewModel() {
    private var accr = FloatArray(3)
    private var magnf = FloatArray(3)
    private var magnetic = FloatArray(9)
    private var gravity = FloatArray(9)
    private var _outGravity = MutableLiveData<FloatArray>()
    fun outGravity(): LiveData<FloatArray> {
        return _outGravity
    }

    private var _values = FloatArray(3)
    fun values(): FloatArray{
        return _values
    }


    fun getSensorConditions(sManager: SensorManager) {
        //_outGravity.value = FloatArray(9)
        val sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                when (event?.sensor?.type) {
                    Sensor.TYPE_ACCELEROMETER -> accr = event.values.clone()
                    Sensor.TYPE_MAGNETIC_FIELD -> magnf = event.values.clone()
                }
                SensorManager.getRotationMatrix(gravity, magnetic, accr, magnf)
                SensorManager.remapCoordinateSystem(
                    gravity,
                    SensorManager.AXIS_X,
                    SensorManager.AXIS_Z,
                    _outGravity.value
                )
                SensorManager.getOrientation(_outGravity.value, _values)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        sManager.registerListener(sListener, sensor2, SensorManager.SENSOR_DELAY_NORMAL)


        //return _outGravity.value
    }

    fun getRotate(): Float {
        return getDegree() - 90
    }

    private fun getRDate(): Int {
        return 90 + getDegree().toInt()
    }

    private fun getDegree(): Float {
        return _values[2] * 57.2958f
    }

    fun getColor(): Int {
        return if (getRDate() == 0) {
            Color.GREEN
        } else {
            Color.RED
        }
    }
}


