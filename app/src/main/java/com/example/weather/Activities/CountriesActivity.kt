package com.example.weather.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_countries.*
import com.example.weather.Adapters.Adapter
import com.example.weather.Model.Model
import com.example.weather.R
import kotlinx.android.synthetic.main.item_card.*


class CountriesActivity : AppCompatActivity() {

    var myAdapter: Adapter = Adapter(getModels())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        myAdapter.onCityClicked = {cityName ->
            myAdapter.getCheckList()
            intent.putExtra("City",cityName)
            check()
            selectClick()
       }
    }
    var k:Int = 0
    fun check()
    {
        val sharedPreferences:SharedPreferences = this.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val sehir = sharedPreferences.getString("City","") ?: ""
        for (item in sehir.split(","))
        {
            while(k<myAdapter.getCheckList().size)
            {
               // if(myAdapter.getCheckList()[k] == item)
                //{
                    if(getModels()[k].cityName == item)
                    {
                        getModels()[k].check = true
                    }
                //}
                k++
            }

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
            Model("İzmir", true),
            Model("İstanbul", false),
            Model("Ankara", false),
            Model("Manisa", false),
            Model("Muğla", false),
            Model("Samsun", false)
        )
        return models
    }

}
