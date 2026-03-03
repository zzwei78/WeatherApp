package com.example.weather.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weather.R
import com.example.weather.model.WeatherResponse
import com.example.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var etCity: EditText
    private lateinit var btnSearch: Button
    private lateinit var tvLocation: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvFeelsLike: TextView
    private lateinit var tvForecast: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: ScrollView

    private val weatherApi = WeatherApi.create()

    // 常见城市坐标
    private val cityCoordinates = mapOf(
        "北京" to Pair(39.9042, 116.4074),
        "上海" to Pair(31.2304, 121.4737),
        "广州" to Pair(23.1291, 113.2644),
        "深圳" to Pair(22.5431, 114.0579),
        "杭州" to Pair(30.2741, 120.1551),
        "成都" to Pair(30.5728, 104.0668),
        "武汉" to Pair(30.5928, 114.3055),
        "西安" to Pair(34.3416, 108.9398),
        "重庆" to Pair(29.4316, 106.9123),
        "南京" to Pair(32.0603, 118.7969),
        "天津" to Pair(39.0842, 117.2010),
        "苏州" to Pair(31.2989, 120.5853),
        "青岛" to Pair(36.0671, 120.3826),
        "大连" to Pair(38.9140, 121.6147),
        "厦门" to Pair(24.4798, 118.0894),
        "长沙" to Pair(28.2282, 112.9388),
        "郑州" to Pair(34.7466, 113.6253),
        "济南" to Pair(36.6512, 117.1201),
        "沈阳" to Pair(41.8057, 123.4315),
        "哈尔滨" to Pair(45.8038, 126.5350)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etCity = findViewById(R.id.etCity)
        btnSearch = findViewById(R.id.btnSearch)
        tvLocation = findViewById(R.id.tvLocation)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvDescription = findViewById(R.id.tvDescription)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWind = findViewById(R.id.tvWind)
        tvFeelsLike = findViewById(R.id.tvFeelsLike)
        tvForecast = findViewById(R.id.tvForecast)
        progressBar = findViewById(R.id.progressBar)
        scrollView = findViewById(R.id.scrollView)
    }

    private fun setupListeners() {
        btnSearch.setOnClickListener {
            val cityName = etCity.text.toString().trim()
            if (cityName.isNotEmpty()) {
                searchWeather(cityName)
            } else {
                Toast.makeText(this, "请输入城市名称", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchWeather(cityName: String) {
        // 查找城市坐标
        val coordinates = cityCoordinates[cityName]
        if (coordinates == null) {
            Toast.makeText(this, "暂不支持该城市，请尝试其他城市", Toast.LENGTH_SHORT).show()
            return
        }

        showLoading(true)

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    weatherApi.getWeather(coordinates.first, coordinates.second).execute()
                }

                if (response.isSuccessful && response.body() != null) {
                    updateUI(response.body()!!, cityName)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "获取天气信息失败",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "网络错误: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun updateUI(weather: WeatherResponse, cityName: String) {
        val current = weather.current
        val daily = weather.daily

        // 更新当前位置
        tvLocation.text = cityName

        // 更新当前温度
        tvTemperature.text = "${String.format("%.1f", current.temperature)}°C"

        // 更新天气描述
        tvDescription.text = getWeatherDescription(current.weatherCode)

        // 更新湿度
        tvHumidity.text = "湿度: ${current.humidity}%"

        // 更新风速
        tvWind.text = "风速: ${String.format("%.1f", current.windSpeed)} km/h"

        // 更新体感温度
        tvFeelsLike.text = "体感: ${String.format("%.1f", current.apparentTemperature)}°C"

        // 更新未来天气预报（显示未来3天）
        val forecast = StringBuilder("未来3天预报:\n\n")
        val startIndex = 1 // 从明天开始
        val endIndex = minOf(4, daily.time.size) // 显示最多3天

        for (i in startIndex until endIndex) {
            val date = daily.time[i].substring(5) // 只显示月-日
            val high = String.format("%.1f", daily.temperatureMax[i])
            val low = String.format("%.1f", daily.temperatureMin[i])
            val desc = getWeatherDescription(daily.weatherCode[i])
            forecast.append("$date: $high°C / $low°C - $desc\n")
        }

        tvForecast.text = forecast.toString()

        // 显示天气图标
        updateWeatherIcon(current.weatherCode)

        // 滚动到顶部
        scrollView.post {
            scrollView.fullScroll(ScrollView.FOCUS_UP)
        }
    }

    private fun updateWeatherIcon(code: Int) {
        // 根据天气代码显示不同的图标或背景
        val iconView = findViewById<ImageView>(R.id.ivWeatherIcon)
        val colorRes = when (code) {
            0, 1 -> android.R.color.holo_orange_light  // 晴天
            2, 3 -> android.R.color.holo_blue_light   // 多云
            45, 48 -> android.R.color.darker_gray     // 雾天
            in 51..67, in 80..82 -> android.R.color.holo_blue_dark // 雨
            in 71..77 -> android.R.color.white       // 雪
            in 85..86 -> android.R.color.white       // 大雪
            else -> android.R.color.holo_blue_bright  // 默认
        }
        iconView.setBackgroundColor(getColor(colorRes))
    }

    private fun getWeatherDescription(code: Int): String {
        return when (code) {
            0 -> "晴朗"
            1 -> "大部晴朗"
            2 -> "多云"
            3 -> "阴天"
            45, 48 -> "有雾"
            51 -> "小毛毛雨"
            53 -> "中毛毛雨"
            55 -> "大毛毛雨"
            56, 57 -> "冻雨"
            61 -> "小雨"
            63 -> "中雨"
            65 -> "大雨"
            66, 67 -> "冻雨"
            71 -> "小雪"
            73 -> "中雪"
            75 -> "大雪"
            77 -> "冰粒"
            80 -> "阵雨"
            81 -> "强阵雨"
            82 -> "暴雨"
            85, 86 -> "阵雪"
            95 -> "雷雨"
            96, 99 -> "雷雨伴有冰雹"
            else -> "未知天气"
        }
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        btnSearch.isEnabled = !show
    }
}
