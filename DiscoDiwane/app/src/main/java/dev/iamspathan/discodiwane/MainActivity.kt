package dev.iamspathan.discodiwane

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import java.nio.channels.FileLock

// Print all the available sensors
// Accelerometer Sesnor to basically generate colors



class MainActivity : AppCompatActivity() {

    lateinit var sensorListener : SensorEventListener
    lateinit var sensorManager: SensorManager
    lateinit var accelSensor: Sensor
    lateinit var discoLayout : FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        discoLayout = findViewById(R.id.discoLayout)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for( sensors in sensorList){
            Log.d("SENSOR", "${sensors.name} -> ${sensors.vendor}")
        }

        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorListener = object : SensorEventListener {

            override fun onSensorChanged(p0: SensorEvent?) {

                p0?.values?.let { axis ->
                    val bgColor = acceltocolor(axis[0],axis[1],axis[2])
                    discoLayout.setBackgroundColor(bgColor)
                }

            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                Log.d("SENSOR", "OnAccuracyChanged")
            }
        }


    }


    private fun acceltocolor(aX:Float, aY:Float, aZ:Float): Int {

        // Axis Value ranges from -10 to +10
        // Color values ranges from 0-255
        // -1 to 1

        val R = (((aX + 12) /24) * 255).toInt()

        val G = (((aY + 12) /24) * 255).toInt()

        val B = (((aZ + 12) /24) * 255).toInt()

        return Color.rgb(R,G,B)


    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            sensorListener,
            accelSensor,
            1000)
    }

    override fun onStop() {
        sensorManager.unregisterListener(sensorListener)
        super.onStop()
    }



}