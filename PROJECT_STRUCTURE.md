# 项目结构

```
WeatherApp/
│
├── app/                          # 应用模块
│   ├── build.gradle.kts          # 应用级构建配置
│   │
│   └── src/
│       └── main/
│           │
│           ├── AndroidManifest.xml  # 应用清单文件
│           │
│           └── java/com/example/weather/
│               │
│               ├── model/         # 数据模型层
│               │   └── WeatherResponse.kt
│               │       ├── WeatherResponse      # 天气响应总类
│               │       ├── CurrentWeather       # 当前天气
│               │       ├── DailyWeather         # 每日预报
│               │       └── CityCoordinates      # 城市坐标
│               │
│               ├── network/       # 网络层
│               │   └── WeatherApi.kt
│               │       ├── WeatherApi           # Retrofit API接口
│               │       └── create()            # 创建API实例
│               │
│               └── ui/            # 用户界面层
│                   └── MainActivity.kt
│                       ├── onCreate()           # 生命周期初始化
│                       ├── searchWeather()      # 搜索天气
│                       ├── updateUI()           # 更新界面
│                       ├── cityCoordinates      # 城市坐标映射
│                       └── getWeatherDescription() # 天气代码转描述
│
│           └── res/             # 资源文件
│               │
│               ├── layout/      # 布局文件
│               │   └── activity_main.xml
│               │       ├── ScrollView            # 可滚动容器
│               │       ├── TextInputLayout      # 城市输入框
│               │       ├── MaterialButton       # 搜索按钮
│               │       ├── CardView            # 天气信息卡片
│               │       │   ├── ImageView        # 天气图标
│               │       │   ├── TextViews        # 各种天气信息
│               │       │   └── View              # 分隔线
│               │
│               └── values/      # 值资源
│                   ├── colors.xml    # 颜色定义
│                   │   ├── primary_color
│                   │   ├── text_color
│                   │   └── 其他颜色...
│                   │
│                   ├── strings.xml   # 字符串资源
│                   │   ├── app_name
│                   │   ├── search_hint
│                   │   └── 其他字符串...
│                   │
│                   └── themes.xml    # 主题定义
│                       ├── Theme.WeatherApp
│                       └── Material Design主题
│
├── build.gradle.kts             # 项目级构建配置
├── settings.gradle.kts         # Gradle设置
├── gradle.properties            # Gradle属性
│
├── .gitignore                   # Git忽略文件
├── README.md                    # 项目说明文档
├── QUICK_START.md              # 快速开始指南
└── PROJECT_STRUCTURE.md       # 项目结构文档（本文件）
```

## 文件说明

### 核心代码文件

| 文件 | 说明 | 代码行数（约） |
|------|------|---------------|
| `MainActivity.kt` | 主活动，处理UI交互和天气查询 | ~220 |
| `WeatherApi.kt` | Retrofit API接口定义 | ~35 |
| `WeatherResponse.kt` | 数据模型类 | ~45 |

### 资源文件

| 文件 | 说明 | 内容数量 |
|------|------|---------|
| `activity_main.xml` | 主界面布局 | 1个 |
| `colors.xml` | 颜色资源 | 9种颜色 |
| `strings.xml` | 字符串资源 | 6个字符串 |
| `themes.xml` | 主题样式 | 1个主题 |

### 配置文件

| 文件 | 说明 |
|------|------|
| `build.gradle.kts` | 应用依赖配置 |
| `settings.gradle.kts` | Gradle项目设置 |
| `AndroidManifest.xml` | 应用清单和权限声明 |
| `.gitignore` | Git忽略配置 |

## 技术架构

```
┌─────────────────────────────────────────┐
│           Presentation Layer            │
│         (MainActivity.kt)                │
│  ┌───────────────────────────────────┐  │
│  │  - UI Interaction                  │  │
│  │  - Lifecycle Management           │  │
│  │  - Data Display                    │  │
│  └───────────────────────────────────┘  │
└───────────────────┬─────────────────────┘
                    │
┌───────────────────▼─────────────────────┐
│           Network Layer                 │
│         (WeatherApi.kt)                 │
│  ┌───────────────────────────────────┐  │
│  │  - Retrofit API Interface         │  │
│  │  - HTTP Request Configuration     │  │
│  │  - JSON Parsing (Gson)             │  │
│  └───────────────────────────────────┘  │
└───────────────────┬─────────────────────┘
                    │
┌───────────────────▼─────────────────────┐
│           Model Layer                  │
│       (WeatherResponse.kt)             │
│  ┌───────────────────────────────────┐  │
│  │  - Data Classes                   │  │
│  │  - Response Mapping               │  │
│  │  - City Coordinates                │  │
│  └───────────────────────────────────┘  │
└─────────────────────────────────────────┘
                    │
                    ▼
         ┌──────────────────┐
         │  OpenMeteo API   │
         │  (Free Weather)  │
         └──────────────────┘
```

## 数据流

```
用户输入城市名
    ↓
点击搜索按钮
    ↓
MainActivity.searchWeather()
    ↓
查找城市坐标
    ↓
WeatherApi.getWeather(lat, lon)
    ↓
Retrofit 网络请求
    ↓
OpenMeteo API 返回 JSON
    ↓
Gson 解析为 WeatherResponse
    ↓
MainActivity.updateUI()
    ↓
显示天气信息
```

## 关键依赖

```kotlin
// 网络请求
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"

// UI组件
implementation "com.google.android.material:material:1.11.0"

// 异步处理
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"

// 生命周期
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0"
```

## 扩展建议

如需扩展项目，建议在以下目录添加：

- `repository/` - 数据仓库层，用于数据缓存
- `viewmodel/` - ViewModel层，实现MVVM架构
- `utils/` - 工具类，如日期格式化、温度转换等
- `di/` - 依赖注入模块（使用Hilt或Dagger）
- `database/` - 本地数据库（Room）用于缓存

---

项目已完全准备就绪，可以直接在 Android Studio 中打开使用！🦞
