package com.education.buildinglevel

import android.hardware.SensorEvent
import android.hardware.SensorManager

class AccelerometersDatas {
    private val outGravity = FloatArray(9)
    fun getOptions(event: SensorEvent?, accrs: FloatArray, magf: FloatArray, gravity: FloatArray, magnetic: FloatArray) : FloatArray {
        SensorManager.getRotationMatrix(gravity, magnetic, accrs, magf)
        SensorManager.remapCoordinateSystem(
            gravity,
            SensorManager.AXIS_X,
            SensorManager.AXIS_Z,
            outGravity
        )
        return outGravity
    }

}