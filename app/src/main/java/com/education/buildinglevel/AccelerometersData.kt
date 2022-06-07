package com.education.buildinglevel

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager

class AccelerometersData(private val values: FloatArray) {
    private var accr = FloatArray(3)
    private var magnf = FloatArray(3)
    private var magnetic = FloatArray(9)
    private var gravity = FloatArray(9)
    private var outGravity = FloatArray(9)
    fun getSensorConditions(event: SensorEvent?): FloatArray {
        when (event?.sensor?.type) {
            Sensor.TYPE_ACCELEROMETER -> accr = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> magnf = event.values.clone()
        }
        SensorManager.getRotationMatrix(gravity, magnetic, accr, magnf)
        SensorManager.remapCoordinateSystem(
            gravity,
            SensorManager.AXIS_X,
            SensorManager.AXIS_Z,
            outGravity
        )
        return outGravity
    }

    fun getRotate(): Float {
        return getDegree() -90
    }

    private fun getRDate(): Int {
        return 90 + getDegree().toInt()
    }

    private fun getDegree(): Float {
        return values[2] * 57.2958f
    }

    fun getColor(): Int {
        return if (getRDate() == 0) {
            Color.GREEN
        } else {
            Color.RED
        }
    }
}