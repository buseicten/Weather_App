package com.example.weather

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.annotation.MainThread
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.FragmentHostCallback
import androidx.fragment.app.FragmentManager
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
            Model("İzmir",true),
            Model("İstanbul",false),
            Model("Ankara",false),
            Model("Manisa",false),
            Model("Muğla",false),
            Model("Samsun",false)
        )
        return models
    }

}
