package com.example.weather.Activities

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weather.R

class SplashActivity : AppCompatActivity()
{
    val ACCESS_COARSE_LOCATION : Int = 76
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        if(!checkAndRequestPermission())
        {
            return
        }
    }
    fun checkAndRequestPermission() : Boolean
    {
        val permissionACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        val listPermissionsNeeded = ArrayList<String>()
        if(permissionACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(arrayOfNulls<String>(listPermissionsNeeded.size)), ACCESS_COARSE_LOCATION)
            return false
        }
        return true
    }
}
