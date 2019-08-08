package com.example.weather.Activities

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.weather.Adapters.Adapter
import com.example.weather.R
import kotlinx.android.synthetic.main.item_card.*
import java.util.concurrent.RecursiveAction

@RunWith(AndroidJUnit4::class)
class CountriesActivityTest
{
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<CountriesActivity>(CountriesActivity::class.java)
    @Test
    fun clickAddCity()
    {
        //onView(withId(R.id.cityName)).perform(ViewActions.click())
        onView(withId(R.id.btnClick)).perform(ViewActions.click())
    }

    @Test
    fun selectCity()
    {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<Adapter.ModelViewHolder>(0, ViewActions.click()))
    }
}