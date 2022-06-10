package com.education.buildinglevel

import android.hardware.SensorManager

class AccelerometersDatas {
    private val outGravity = FloatArray(SIZE_OF_ARRAY_NINE)
    val magnetic = FloatArray(SIZE_OF_ARRAY_NINE)
    val gravity = FloatArray(SIZE_OF_ARRAY_NINE)
    var accrs = FloatArray(SIZE_OF_ARRAY_THREE)
    var magf = FloatArray(SIZE_OF_ARRAY_THREE)
    fun getOptions() : FloatArray {
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
        return outGravity
    }

    companion object {
        private const val SIZE_OF_ARRAY_NINE = 9
        private const val SIZE_OF_ARRAY_THREE = 3
    }
}