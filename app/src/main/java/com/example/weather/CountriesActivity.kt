package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.MultiAutoCompleteTextView
import androidx.annotation.MainThread
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_countries.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_card.*
import java.nio.file.FileVisitResult

class CountriesActivity : AppCompatActivity() {
    var myAdapter:Adapter = Adapter(getModels())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)

        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        myAdapter.onCityClicked = {cityName ->
            myAdapter.getCheckList()
            //val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("City",cityName)
            //startActivity(intent)
            selectClick()
       }
    }
    fun selectClick()
    {
        val btnClick:Button = findViewById(R.id.btnClick)
        btnClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("City",myAdapter.getCheckList().toString())
            startActivity(intent)
        }
    }
    fun getModels(): MutableList<Model>
    {
        val models = mutableListOf(
            Model("İzmir",false),
            Model("İstanbul",false),
            Model("Ankara",false),
            Model("Manisa",false),
            Model("Muğla",false),
            Model("Samsun",false)
        )
        return models
    }

}
