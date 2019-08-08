package com.example.weather.Activities

import android.content.SharedPreferences
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.weather.Adapters.Adapter
import com.example.weather.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.junit.Assert

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class MainActivityTest
{
    @Test
    @Throws(Exception::class)
    fun clickSettings()
    {
        onView(withId(R.id.btnSettings))
    }

    @Test
    @Throws(Exception::class)
    fun clickAddCity()
    {
        onView(withId(R.id.btnEkle))
    }
    @Test
    @Throws(Exception::class)
    fun city()
    {

    }
}