package com.example.weather

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var txt_city: TextView? = null
    var txt_temp: TextView? = null
    var txt_maxmin: TextView? = null
    var txt_sunrise: TextView? = null
    val fragmentAdapter = MyPagerAdapter(supportFragmentManager)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_city = findViewById(R.id.txt_city)
        txt_temp = findViewById(R.id.txt_temp)
        txt_maxmin = findViewById(R.id.txt_maxmin)
        txt_sunrise = findViewById(R.id.txt_sunrise)
        val tabs:TabLayout = findViewById(R.id.tabs_main)
        val sehir = intent.getStringExtra("City") ?: ""
        val img:ImageView = findViewById(R.id.img)
        viewpager_main.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager_main)
        for (item in sehir.split(",")) {
            if(item == "İzmir")
            {

            }
            else
                tabs.addTab(tabs.newTab().setText(item))
        }

        img.setBackgroundResource(R.drawable.sun)
        name = "Izmir"
        getCurrentData()

        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewpager_main!!.currentItem = tabs.selectedTabPosition
                for(item in sehir.split(","))
                {
                    if(p0!!.text == item)
                    {
                        img.setBackgroundResource(R.drawable.sun)
                        name = item
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
        val call = service.getCurrentWeatherData(name, AppId)

        call.enqueue(object: Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if(response.code() == 200)
                {
                    val weatherResponse = response.body()!!
                    /*val stringBuilder =
                            "Temperature: " + (weatherResponse.main!!.temp - 273).toString().substring(0,3) + " ºC" + "\n" +
                            "Temperature(min): " + (weatherResponse.main!!.temp_min - 273).toString().substring(0,3) + " ºC" + "\n" +
                            "Temperature(max): " + (weatherResponse.main!!.temp_max - 273).toString().substring(0,3) + " ºC" + "\n" +
                            "Humidity: " + weatherResponse.main!!.humidity + "\n" +
                            "Pressure: " + weatherResponse.main!!.pressure
                    txt_city!!.text = stringBuilder*/
                    txt_temp!!.text = (weatherResponse.main!!.temp - 273).toString().substring(0,2) + " ºC"
                    txt_maxmin!!.text = " " + (weatherResponse.main!!.temp_min - 273).toString().substring(0,2) + " ºC" +  " / " + (weatherResponse.main!!.temp_max - 273).toString().substring(0,2) + " ºC"
                    //txt_sunrise!!.text = weatherResponse.sys!!.sunrise.toString() + " / " + weatherResponse.sys!!.sunset.toString()

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
