# 天气查询 Android 应用

一个简洁美观的Android天气查询应用，使用Kotlin开发，基于OpenMeteo免费天气API。

## 功能特性

- 🌡️ **实时天气**：显示当前温度、体感温度、湿度和风速
- 🎯 **天气描述**：详细的天气状况描述（晴、多云、雨、雪等）
- 📅 **未来预报**：显示未来3天的天气预报
- 🎨 **Material Design**：现代化UI设计，符合Material Design规范
- 🏙️ **支持20个中国主要城市**：包括北京、上海、广州、深圳等
- ⚡ **快速响应**：使用协程和Retrofit实现高效网络请求

## 支持的城市

北京、上海、广州、深圳、杭州、成都、武汉、西安、重庆、南京、天津、苏州、青岛、大连、厦门、长沙、郑州、济南、沈阳、哈尔滨

## 技术栈

- **语言**: Kotlin
- **最低SDK**: 24 (Android 7.0)
- **目标SDK**: 34 (Android 14)
- **架构**: MVVM
- **网络库**: Retrofit 2.9.0
- **异步处理**: Kotlin Coroutines
- **UI组件**: Material Components
- **天气API**: OpenMeteo (免费，无需API Key)

## 项目结构

```
WeatherApp/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/weather/
│           │   ├── model/          # 数据模型
│           │   │   └── WeatherResponse.kt
│           │   ├── network/        # 网络层
│           │   │   └── WeatherApi.kt
│           │   └── ui/             # UI层
│           │       └── MainActivity.kt
│           └── res/
│               └── layout/
│                   └── activity_main.xml
└── build.gradle.kts
```

## 使用方法

### 方式 1: 使用 GitHub Actions 自动构建（推荐）🚀

[![Build Android APK](https://github.com/你的用户名/WeatherApp/workflows/Build%20Android%20APK/badge.svg)](https://github.com/你的用户名/WeatherApp/actions)

本项目已配置 GitHub Actions，可以自动编译 APK：

1. 将代码推送到 GitHub
2. 自动触发构建流程
3. 在 Actions 页面下载编译好的 APK
4. 直接安装到 Android 设备

详细说明请查看：[GitHub Actions 使用指南](GITHUB_ACTIONS.md)

### 方式 2: 在 Android Studio 中构建

#### 1. 环境要求

- Android Studio Arctic Fox 或更高版本
- JDK 8 或更高版本
- Android SDK 34
- Gradle 8.1 或更高版本

#### 2. 导入项目

1. 克隆或下载项目到本地
2. 使用 Android Studio 打开项目文件夹
3. 等待 Gradle 同步完成

### 3. 运行应用

1. 连接 Android 设备或启动模拟器
2. 点击 "Run" 按钮或按 `Shift + F10`
3. 在应用中输入城市名称进行查询

## API 说明

应用使用 OpenMeteo 天气API，该API完全免费且无需API Key。

**API基础URL**: `https://api.open-meteo.com/v1/`

**请求示例**:
```
GET /forecast?latitude=39.9042&longitude=116.4074&current=temperature_2m,relative_humidity_2m&daily=temperature_2m_max,temperature_2m_min
```

## 主要文件说明

### MainActivity.kt
主活动类，负责：
- 用户界面交互
- 处理搜索按钮点击事件
- 发起天气API请求
- 更新UI显示

### WeatherApi.kt
Retrofit接口，定义天气API请求方法。

### WeatherResponse.kt
数据模型类，定义API响应数据结构。

### activity_main.xml
主界面布局文件，包含搜索框、天气信息卡片等。

## 自定义扩展

### 添加更多城市

在 `MainActivity.kt` 中的 `cityCoordinates` Map 中添加新的城市坐标：

```kotlin
private val cityCoordinates = mapOf(
    "城市名" to Pair(纬度, 经度),
    // 添加更多城市...
)
```

### 修改主题颜色

在 `res/values/colors.xml` 中修改颜色值：

```xml
<color name="primary_color">#2196F3</color>
<color name="accent_color">#FF4081</color>
```

## 依赖库

```gradle
// AndroidX Core
implementation "androidx.core:core-ktx:1.12.0"
implementation "androidx.appcompat:appcompat:1.6.1"

// Material Components
implementation "com.google.android.material:material:1.11.0"

// Lifecycle
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0"
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0"

// Coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"

// Retrofit
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"
```

## 注意事项

1. **网络权限**: 应用需要在 AndroidManifest.xml 中声明 INTERNET 权限
2. **HTTPS**: OpenMeteo API 使用 HTTPS，Android 9.0+ 默认允许
3. **坐标精度**: 使用预定义的城市坐标，精度在市区范围

## 未来改进计划

- [ ] 添加自动定位功能
- [ ] 支持搜索更多城市
- [ ] 添加天气预警功能
- [ ] 支持多种温度单位（摄氏度/华氏度）
- [ ] 添加天气动画效果
- [ ] 支持桌面小组件
- [ ] 添加历史天气查询
- [ ] 支持多语言

## 许可证

本项目仅供学习和参考使用。

## 作者

🦞 Created by AutoGLM

---

有问题或建议？欢迎提出 Issue 或 Pull Request！
