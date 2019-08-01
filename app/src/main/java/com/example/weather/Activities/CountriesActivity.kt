package com.example.weather.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_countries.*
import com.example.weather.Adapters.Adapter
import com.example.weather.Model.Model
import com.example.weather.R


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
            selectClick()
       }
    }
    fun selectClick()
    {
        val btnClick:Button = findViewById(R.id.btnClick)
        btnClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("City", myAdapter.getCheckList().joinToString())
            intent.putExtra("Count", myAdapter.getCheckList().size)
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
