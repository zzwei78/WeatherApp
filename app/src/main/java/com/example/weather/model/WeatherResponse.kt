package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("current")
    val current: CurrentWeather,
    @SerializedName("daily")
    val daily: DailyWeather
)

data class CurrentWeather(
    @SerializedName("time")
    val time: String,
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("temperature_2m")
    val temperature: Double,
    @SerializedName("relative_humidity_2m")
    val humidity: Int,
    @SerializedName("apparent_temperature")
    val apparentTemperature: Double,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_speed_10m")
    val windSpeed: Double,
    @SerializedName("wind_direction_10m")
    val windDirection: Int
)

data class DailyWeather(
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)

data class CityCoordinates(
    val latitude: Double,
    val longitude: Double,
    val name: String
)
