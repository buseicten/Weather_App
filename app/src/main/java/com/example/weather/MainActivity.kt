package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_card.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt_city: TextView = findViewById(R.id.txt_city)
        val txt_sicaklik: TextView = findViewById(R.id.txt_sicaklik)
        val txt_weather: TextView = findViewById(R.id.txt_weather)
        val sehir = intent.getStringExtra("City")
        //  txt_city.setText(sehir)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.addTab(TabLayout.Tab().setText(sehir))
        tabs_main.setupWithViewPager(viewpager_main)
    }

    fun clickHome(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun clickSettings(view : View)
    {
        val intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }

    fun addCity(view: View)
    {
        val intent = Intent(this,CountriesActivity::class.java)
        startActivity(intent)
    }
}
