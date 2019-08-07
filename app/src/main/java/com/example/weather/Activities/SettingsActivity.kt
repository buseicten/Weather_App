package com.example.weather.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.content.pm.PermissionGroupInfo
import android.location.*
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.Switch
import androidx.core.app.ActivityCompat
import com.example.weather.R
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val swPermission: Switch = findViewById(R.id.swPermission)
        if(ContextCompat.checkSelfPermission(this@SettingsActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==  PackageManager.PERMISSION_GRANTED)
        {
            swPermission.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                    if (isChecked) {
                        val ACCESS_COARSE_LOCATION = 76
                        val permissionACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(
                            this@SettingsActivity,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                        val listPermissionsNeeded = ArrayList<String>()
                        fun CheckAndRequestPermission(): Boolean {
                            if (permissionACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
                                listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            }
                            if (!listPermissionsNeeded.isEmpty()) {
                                ActivityCompat.requestPermissions(
                                    this@SettingsActivity,
                                    listPermissionsNeeded.toArray(arrayOfNulls<String>(listPermissionsNeeded.size)),
                                    ACCESS_COARSE_LOCATION
                                )
                            }
                            return true
                        }
                        if (!CheckAndRequestPermission()) {
                            return
                        }
                    }
                }
            })
            swPermission.isChecked = true
        }
        if(ContextCompat.checkSelfPermission(this@SettingsActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
        {
            swPermission.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                    if(!isChecked)
                    {
                        ContextCompat.checkSelfPermission(this@SettingsActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=  PackageManager.PERMISSION_GRANTED
                    }
                }
            })
            swPermission.isChecked = false
        }
        /*swPermission.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                if (isChecked) {
                    val ACCESS_COARSE_LOCATION = 76
                    val permissionACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(
                        this@SettingsActivity,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    val listPermissionsNeeded = ArrayList<String>()
                    fun CheckAndRequestPermission(): Boolean {
                        if (permissionACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED) {
                            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        }
                        if (!listPermissionsNeeded.isEmpty()) {
                            ActivityCompat.requestPermissions(
                                this@SettingsActivity,
                                listPermissionsNeeded.toArray(arrayOfNulls<String>(listPermissionsNeeded.size)),
                                ACCESS_COARSE_LOCATION
                            )
                        }
                        return true
                    }
                    if (!CheckAndRequestPermission()) {
                        return
                    }
                }
                if(!isChecked)
                {
                    ContextCompat.checkSelfPermission(this@SettingsActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=  PackageManager.PERMISSION_GRANTED
                }
            }
        })*/
        /*if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            swPermission.isChecked = true
        }
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            swPermission.isChecked = false
        }*/
        clickBack()
    }
    fun clickBack()
    {
        val btnBack:Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener()
        {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("State", swPermission.isChecked)
            startActivity(intent)
        }
    }
}
