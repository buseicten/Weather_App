package com.example.weather.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services
{
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("q") name: String, @Query("APPID") app_id: String) : Call<WeatherResponse>
}