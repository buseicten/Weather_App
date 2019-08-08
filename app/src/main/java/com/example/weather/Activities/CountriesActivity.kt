package com.example.weather.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_countries.*
import com.example.weather.Adapters.Adapter
import com.example.weather.Model.Model
import com.example.weather.R
import kotlinx.android.synthetic.main.item_card.*


class CountriesActivity : AppCompatActivity() {

    var myAdapter: Adapter = Adapter(getModels())
    var k:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val sharedPreferences:SharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val sehir = sharedPreferences.getString("City","") ?: ""
        val sehirList :List<String> = sehir.split(",")
        for (item in sehirList)
        {
            while(k<getModels().size)
            {
                if(getModels()[k].cityName.equals(item.trim(),false))
                {
                    myAdapter.getCheckList().add(getModels()[k].cityName)
                    myAdapter.cityList[k].check = true
                    break
                }
                k++
            }

        }
        myAdapter.onCityClicked = {cityName ->
            myAdapter.getCheckList()
            intent.putExtra("City",cityName)
            selectClick()
       }
    }

    fun selectClick()
    {
        val sharedPreferences:SharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val btnClick:Button = findViewById(R.id.btnClick)
        btnClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("City", myAdapter.getCheckList().joinToString())
            intent.putExtra("Count", myAdapter.getCheckList().size)
            editor.putString("City", myAdapter.getCheckList().joinToString())
            editor.apply()
            startActivity(intent)
        }
    }
    fun getModels(): MutableList<Model>
    {
        val models = mutableListOf(
            Model("İzmir", false),
            Model("İstanbul", false),
            Model("Ankara", false),
            Model("Manisa", false),
            Model("Muğla", false),
            Model("Samsun", false)
        )
        return models
    }
}
