package com.example.weather.Activities

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.weather.R

import org.junit.Test

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class SettingsActivityTest
{
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<SettingsActivity>(SettingsActivity::class.java)
    @Test
    fun clickSettings()
    {
        //onView(withId(R.id.swPermission)).perform(ViewActions.click())
        onView(withId(R.id.btnBack)).perform(ViewActions.click())
    }
}