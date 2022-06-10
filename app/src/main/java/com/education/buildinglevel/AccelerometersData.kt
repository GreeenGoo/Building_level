package com.education.buildinglevel

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager

class AccelerometersData {
    private val outGravity = FloatArray(SIZE_OF_ARRAY_NINE)
    private val magnetic = FloatArray(SIZE_OF_ARRAY_NINE)
    private val gravity = FloatArray(SIZE_OF_ARRAY_NINE)
    private var accrs = FloatArray(SIZE_OF_ARRAY_THREE)
    private var magf = FloatArray(SIZE_OF_ARRAY_THREE)
    private val values = FloatArray(SIZE_OF_ARRAY_THREE)

    fun getOptions(event: SensorEvent?) : Float {
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
        SensorManager.getOrientation(outGravity, values)
        val degree = values[SECOND_INDEX_OF_ARRAY] * DEGREE_COEFFICIENT
        return DEGREE_CORRECTION + degree
    }

    companion object {
        private const val SIZE_OF_ARRAY_NINE = 9
        private const val SIZE_OF_ARRAY_THREE = 3
        private const val SECOND_INDEX_OF_ARRAY = 2
        private const val DEGREE_COEFFICIENT = 57.2958f
        private const val DEGREE_CORRECTION = 90
    }
}