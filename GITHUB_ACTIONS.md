# GitHub Actions 自动构建 APK

本项目配置了 GitHub Actions，可以自动编译 APK。

## 🚀 如何触发构建

### 方式 1: 推送代码到 GitHub
```bash
git init
git add .
git commit -m "Initial commit"

# 添加远程仓库（替换成你的仓库地址）
git remote add origin https://github.com/你的用户名/WeatherApp.git
git branch -M main
git push -u origin main
```

推送到 `main` 或 `master` 分支会自动触发构建。

### 方式 2: 手动触发构建
1. 进入 GitHub 仓库
2. 点击 `Actions` 标签
3. 选择 `Build Android APK` workflow
4. 点击 `Run workflow` 按钮

## 📦 下载 APK

构建完成后：

1. 进入 GitHub 仓库的 `Actions` 标签
2. 点击最近的构建任务
3. 滚动到页面底部的 `Artifacts` 区域
4. 下载：
   - **debug-apk**：调试版 APK，可以安装测试
   - **release-apk**：发布版 APK（使用 debug 签名）

## 🔧 工作流说明

### 构建步骤
1. **Checkout code**: 检出代码
2. **Set up JDK 17**: 配置 Java 17 环境
3. **Grant execute permission**: 给 gradlew 添加执行权限
4. **Build Debug APK**: 构建调试版 APK
5. **Build Release APK**: 构建发布版 APK
6. **Upload Artifacts**: 上传构建产物

### 构建产物
- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release-unsigned.apk`

### 产物保留时间
APK 文件会保留 30 天。

## 📱 安装 APK

### 通过 USB 安装
```bash
# 安装 Debug APK
adb install app-debug.apk

# 启动应用
adb shell am start -n com.example.weather/.ui.MainActivity
```

### 直接安装
在 Android 设备上：
1. 找到下载的 APK 文件
2. 点击安装
3. 允许未知来源安装（如果提示）
4. 完成安装

## 🎨 工作流配置文件

工作流文件位于：`.github/workflows/build-apk.yml`

主要配置：
- **触发条件**: push 到 main/master 分支、Pull Request、手动触发
- **运行环境**: ubuntu-latest
- **Java 版本**: JDK 17 (Temurin)
- **Gradle 缓存**: 启用以加速构建

## 🔐 签名说明

当前配置：
- **Debug APK**: 使用 debug keystore 签名
- **Release APK**: 使用 debug keystore 签名（用于 CI/CD）

### 生产环境签名

在生产环境中，你应该：
1. 创建正式的 keystore
2. 将 keystore 和密码作为 GitHub Secrets
3. 修改 `app/build.gradle.kts` 使用 secrets

示例：
```kotlin
signingConfigs {
    create("release") {
        storeFile = file(System.getenv("KEYSTORE_FILE") ?: "release.keystore")
        storePassword = System.getenv("KEYSTORE_PASSWORD")
        keyAlias = System.getenv("KEY_ALIAS")
        keyPassword = System.getenv("KEY_PASSWORD")
    }
}
```

在 GitHub Settings → Secrets and variables → Actions 中添加：
- `KEYSTORE_FILE`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

## 📊 构建状态徽章

在你的 README.md 中添加构建状态徽章：

```markdown
![Build Status](https://github.com/你的用户名/WeatherApp/workflows/Build%20Android%20APK/badge.svg)
```

## 🐛 故障排查

### 构建失败
1. 检查 Actions 日志中的错误信息
2. 确保 `gradlew` 文件有执行权限
3. 检查依赖配置是否正确

### APK 安装失败
- 确保设备 Android 版本 >= 7.0 (API 24)
- 允许安装未知来源应用
- 检查 APK 文件是否完整

### 网络请求失败
- 确保设备有网络连接
- 检查 OpenMeteo API 是否可访问
- 查看 Logcat 日志获取详细错误

## 📝 更新工作流

如果需要修改工作流：
1. 编辑 `.github/workflows/build-apk.yml`
2. 提交并推送更改
3. 工作流会自动使用新配置运行

## 🎯 下一步

- [ ] 添加自动测试
- [ ] 配置 Beta 发布渠道
- [ ] 添加 APK 版本号自动递增
- [ ] 集成代码质量检查
- [ ] 配置自动上传到 Google Play

---

有问题？查看 [GitHub Actions 文档](https://docs.github.com/en/actions) 🦞
