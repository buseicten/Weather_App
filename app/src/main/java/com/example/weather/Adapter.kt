package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class Adapter(val cityList: MutableList<Model>) : RecyclerView.Adapter<Adapter.ModelViewHolder>()
{
    internal var onCityClicked: (cityName: String) -> Unit =
        { _ -> }
    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val cityName: CheckBox = view.findViewById(R.id.cityName)
        val row:LinearLayout = view.findViewById(R.id.row)
        fun bindItems(item: Model, onCityClicked: (cityName:String) -> Unit = {})
        {
            row.setOnClickListener { onCityClicked(cityName.text.toString()) }
            cityName.setText(item.cityName)
            cityName.setOnCheckedChangeListener { compoundButton, check ->
                item.check = check
                if(check == true)
                {
                    onCityClicked(cityName.text.toString())
                }
                else
                {
                    onCityClicked("")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(cityList.get(position),onCityClicked)

    }
    fun getCheckList() : ArrayList<String>
    {
        val tempList = arrayListOf<String>()
        for(item in cityList)
        {
            if(item.check == true){
                tempList.add(item.cityName)
            }
        }
        return tempList
    }
}