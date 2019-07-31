package com.example.weather


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager):FragmentPagerAdapter(fm) {

    var count:CountriesActivity = CountriesActivity()
    var myAdapter:Adapter = Adapter(count.getModels())
    override fun getItem(position: Int): Fragment {
        return when(position)
        {
            0 ->
            {
                FirstFragment()
            }
            else -> FirstFragment()
        }
    }
    override fun getCount(): Int {
        return myAdapter.getCheckList().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 ->
            {
               myAdapter.getCheckList()[position]
            }
            else -> myAdapter.getCheckList()[position]
        }
    }
}