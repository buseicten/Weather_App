package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface Services
{
    @GET("data/2.5/weather?")
    //fun getCurrentWeatherData(@Query("lat") lat: String,@Query("lon") lon: String, @Query("APPID") app_id: String) : Call<WeatherResponse>
    fun getCurrentWeatherData(@Query("q") name: String, @Query("APPID") app_id: String) : Call<WeatherResponse>
}