package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_countries.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.item_card.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.asin

class MainActivity : AppCompatActivity() {

    val adapter:Adapter = Adapter(CountriesActivity().getModels())
    val fragmentAdapter = MyPagerAdapter(supportFragmentManager)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt_city: TextView = findViewById(R.id.txt_city)
        val tabs:TabLayout = findViewById(R.id.tabs_main)
        val sehir = intent.getStringExtra("City") ?: ""
        txt_city.setText(sehir)
        viewpager_main.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager_main)

        Log.e("DT", sehir)
        for (item in sehir.split(",")) {
            tabs.addTab(tabs.newTab().setText(item))
        }
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
