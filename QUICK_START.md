# 快速开始指南

## 项目位置

项目已创建在：`/home/wuying/clawd/WeatherApp/`

## 如何导入到 Android Studio

### 方法 1: 从文件夹导入（推荐）

1. 打开 Android Studio
2. 选择 `File` → `Open`
3. 导航到 `/home/wuying/clawd/WeatherApp/` 文件夹
4. 选择该文件夹并点击 `OK`
5. 等待 Gradle 同步完成（首次可能需要几分钟）

### 方法 2: 使用命令行导入

```bash
cd /home/wuying/clawd/WeatherApp
studio .
```

## 构建项目

### 在 Android Studio 中构建

1. 等待 Gradle 同步完成后，点击菜单栏 `Build` → `Make Project`
2. 或使用快捷键 `Ctrl+F9` (Windows/Linux) 或 `Cmd+F9` (Mac)

### 使用命令行构建

```bash
cd /home/wuying/clawd/WeatherApp

# Debug 构建
./gradlew assembleDebug

# Release 构建
./gradlew assembleRelease
```

## 运行应用

### 在 Android Studio 中运行

1. 连接 Android 设备（开启 USB 调试）或启动模拟器
2. 点击工具栏上的绿色 ▶️ 按钮运行
3. 或使用快捷键 `Shift+F10`

### 使用命令行运行

```bash
# 安装到连接的设备
./gradlew installDebug

# 启动应用
adb shell am start -n com.example.weather/.ui.MainActivity
```

## 首次运行

1. 应用启动后，会显示一个搜索框
2. 输入支持的城市名称（如：北京、上海、广州）
3. 点击"查询天气"按钮
4. 等待几秒，天气信息将显示在下方卡片中

## 支持的城市列表

- 北京
- 上海
- 广州
- 深圳
- 杭州
- 成都
- 武汉
- 西安
- 重庆
- 南京
- 天津
- 苏州
- 青岛
- 大连
- 厦门
- 长沙
- 郑州
- 济南
- 沈阳
- 哈尔滨

## 常见问题

### Q: Gradle 同步失败怎么办？

A: 尝试以下步骤：
1. File → Invalidate Caches → Invalidate and Restart
2. 检查网络连接，确保能访问 Google Maven 仓库
3. 使用国内镜像源（修改 build.gradle.kts 中的仓库地址）

### Q: 应用崩溃怎么办？

A: 查看 Logcat 中的错误日志：
1. Android Studio 底部切换到 Logcat 选项卡
2. 查看红色的错误信息
3. 检查设备网络连接是否正常

### Q: 天气数据加载不出来？

A: 可能的原因：
1. 网络连接问题
2. OpenMeteo API 服务暂时不可用
3. 城市名称输入错误

### Q: 如何添加更多城市？

A: 编辑 `MainActivity.kt` 文件：
1. 找到 `cityCoordinates` 变量
2. 添加新的城市坐标对：
   ```kotlin
   "城市名" to Pair(纬度, 经度)
   ```

## 调试技巧

### 查看 API 响应

在 `MainActivity.kt` 中添加日志：
```kotlin
Log.d("Weather", "Response: ${response.body()}")
```

### 查看网络请求

使用 Charles 或 Fiddler 抓包工具查看网络请求详情。

### 模拟不同网络条件

在 Android 模拟器中可以模拟：
- 弱网络
- 离线状态
- 延迟网络

## 项目清理

```bash
# 清理构建缓存
./gradlew clean

# 清理并重新构建
./gradlew clean build
```

## 生成签名的 APK

1. Build → Generate Signed Bundle/APK
2. 选择 APK
3. 创建或选择密钥库
4. 选择 release 构建变体
5. 生成的 APK 位于 `app/release/app-release.apk`

## 需要帮助？

- 查看 README.md 了解详细文档
- 检查 Android Studio 的 Logcat 日志
- 查阅 Android 官方文档：https://developer.android.com/docs

---

祝你使用愉快！🦞
