package com.example.weather.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.Switch
import androidx.core.app.ActivityCompat
import com.example.weather.R
import java.time.LocalDateTime


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val ll : LinearLayout = findViewById(R.id.ll)
        val swPermission : Switch = findViewById(R.id.swPermission)

        swPermission.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                if(isChecked)
                {
                    val ACCESS_COARSE_LOCATION = 76
                    val permissionACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(this@SettingsActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    val listPermissionsNeeded = ArrayList<String>()
                    fun CheckAndRequestPermission() : Boolean
                    {
                      if(permissionACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED)
                      {
                         listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                      }
                        if(!listPermissionsNeeded.isEmpty())
                        {
                            ActivityCompat.requestPermissions(this@SettingsActivity, listPermissionsNeeded.toArray(arrayOfNulls<String>(listPermissionsNeeded.size)), ACCESS_COARSE_LOCATION)
                        }
                        return true
                    }
                    if(!CheckAndRequestPermission())
                    {
                        return
                    }
                }
                else
                {

                }
            }
        })
    }
}
