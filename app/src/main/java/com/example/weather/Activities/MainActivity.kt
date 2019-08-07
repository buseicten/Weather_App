package com.example.weather.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.*

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.weather.Adapters.MyPagerAdapter
import com.example.weather.R
import com.example.weather.Retrofit.Services
import com.example.weather.Retrofit.WeatherResponse
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.ContextCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var txt_city: TextView? = null
    var txt_temp: TextView? = null
    var txt_maxmin: TextView? = null
    var txt_sunrise: TextView? = null
    var txt_humidity: TextView? = null
    var txtFindCity: TextView? = null
    var findCity: String? = null
    val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_city = findViewById(R.id.txt_city)
        txt_temp = findViewById(R.id.txt_temp)
        txt_maxmin = findViewById(R.id.txt_maxmin)
        txt_sunrise = findViewById(R.id.txt_sunrise)
        txt_humidity = findViewById(R.id.txt_humidity)
        txtFindCity = findViewById(R.id.txtFindCity)

        val rl:RelativeLayout = findViewById(R.id.rl)
        val tabs:TabLayout = findViewById(R.id.tabs_main)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("sharedPreferences",Context.MODE_PRIVATE)
        val sehir = sharedPreferences.getString("City","") ?: ""
        val state = intent.getBooleanExtra("State", true)
        val img:ImageView = findViewById(R.id.img)
        viewpager_main.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager_main)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            /*sharedPreferences.edit().remove("sehir").apply()
            rl.removeView(txt_temp)
            rl.removeView(txt_humidity)
            rl.removeView(txt_maxmin)
            rl.removeView(txt_sunrise)
            img.setBackgroundResource(R.drawable.close)*/
            return
        }
        if(!state)
        {
            sharedPreferences.edit().remove("sehir").apply()
            rl.removeView(txt_temp)
            rl.removeView(txt_humidity)
            rl.removeView(txt_maxmin)
            rl.removeView(txt_sunrise)
            img.setBackgroundResource(R.drawable.close)
        }
        if(state)
        {
            val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria:Criteria = Criteria()
            val provider:String? = locationManager.getBestProvider(criteria,true)

            val gcd : Geocoder = Geocoder(baseContext, Locale.getDefault())
            val address : List<Address>
            provider?.let {
                val location:Location? = locationManager.getLastKnownLocation(provider)
                val locationListener: LocationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        location.latitude
                        location.longitude
                    }
                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                }
                locationManager.requestLocationUpdates(provider, 1000, 1f, locationListener)
                location?.let {
                    address = gcd.getFromLocation(it.latitude, it.longitude, 1)
                    if(address.size > 0)
                    {
                        findCity = address.get(0).locality.toString()
                        name = findCity.toString()
                        tabs.addTab(tabs.newTab().setText(name))

                        txtFindCity!!.text = address.get(0).locality.toString()
                        getCurrentData()
                    }

                } /*?: run{

                   }*/
            }

        }
        for (item in sehir.split(",")) {
            //savedCity = sharedPreferences.getString("City",item)
            tabs.addTab(tabs.newTab().setText(item))
        }
        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewpager_main!!.currentItem = tabs.selectedTabPosition
                for(item in sehir.split(","))
                {
                    if(p0!!.text == item)
                    {
                        txtFindCity!!.text = item
                        name = item
                        getCurrentData()
                    }
                    if(p0!!.text == findCity)
                    {
                        txtFindCity!!.text = findCity
                        name = findCity.toString()
                        getCurrentData()
                    }
                }
            }
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
        })
    }

    internal fun getCurrentData()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Services::class.java)
        val call = service.getCurrentWeatherData(
            name,
            AppId
        )

        call.enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if(response.code() == 200)
                {
                    response.body()?.let {

                    } ?: run {

                    }
                    val weatherResponse = response.body()!!
                    val sunrise = Date(weatherResponse.sys!!.sunrise)
                    val format = SimpleDateFormat("HH:mm", Locale.US)
                    val sunset = Date(weatherResponse.sys!!.sunset)

                    txt_temp!!.text = (weatherResponse.main!!.temp - 273).toString().substring(0,2) + " ºC"
                    txt_maxmin!!.text = " " + (weatherResponse.main!!.temp_min - 273).toString().substring(0,2) + " ºC" +  " / " + (weatherResponse.main!!.temp_max - 273).toString().substring(0,2) + " ºC"
                    txt_sunrise!!.text = format.format(sunrise) + " / " + format.format(sunset)
                    txt_humidity!!.text = " % " + weatherResponse.main!!.humidity.toString().substring(0,2)
                    if(weatherResponse.clouds!!.all > 50)
                    {
                        img.setBackgroundResource(R.drawable.clouds)
                    }
                    else
                    {
                        img.setBackgroundResource(R.drawable.sun)
                    }
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                txt_city!!.text = t.message
            }
        })
    }
    companion object
    {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "d076fb6784b67eeccba7145d460125ad"
        var name = ""
    }

    fun clickSettings(view : View)
    {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun addCity(view: View)
    {
        val intent = Intent(this, CountriesActivity::class.java)
        startActivity(intent)
    }
}
