# 推送到 GitHub 并自动构建 APK

## 📋 步骤 1: 准备 GitHub 仓库

1. 访问 [GitHub](https://github.com/) 并登录
2. 点击右上角的 `+` 按钮，选择 `New repository`
3. 填写仓库信息：
   - **Repository name**: `WeatherApp`（或其他你喜欢的名称）
   - **Description**: Android 天气查询应用
   - **Public/Private**: 根据需要选择
   - **不要勾选** "Add a README file"（我们已有 README.md）
   - **不要勾选** "Add .gitignore"（我们已有 .gitignore）
4. 点击 `Create repository`

## 📂 步骤 2: 初始化本地 Git 仓库

```bash
cd /home/wuying/clawd/WeatherApp

# 初始化 Git 仓库
git init

# 添加所有文件
git add .

# 提交更改
git commit -m "Initial commit: Android Weather App"
```

## 🔗 步骤 3: 连接远程仓库

```bash
# 添加远程仓库（替换成你的仓库地址）
git remote add origin https://github.com/你的用户名/WeatherApp.git

# 设置主分支为 main
git branch -M main

# 推送到 GitHub
git push -u origin main
```

## 🔐 如果使用 SSH

如果你配置了 SSH 密钥：

```bash
# 使用 SSH 地址
git remote add origin git@github.com:你的用户名/WeatherApp.git

# 推送
git push -u origin main
```

## 🚀 步骤 4: 等待自动构建

推送成功后：

1. 进入你的 GitHub 仓库页面
2. 点击顶部的 `Actions` 标签
3. 你会看到 `Build Android APK` workflow 正在运行（黄色圆点）
4. 等待约 5-10 分钟，构建完成会变成绿色对勾

## 📦 步骤 5: 下载 APK

构建完成后：

1. 点击最近的构建任务（如 `Build Android APK #1`）
2. 滚动到页面底部
3. 在 `Artifacts` 部分下载：
   - **debug-apk**: 调试版，可以安装测试
   - **release-apk**: 发布版（使用 debug 签名）

## 📱 步骤 6: 安装到设备

### 方法 A: 通过 USB 安装

```bash
# 连接 Android 设备（开启 USB 调试）
adb devices

# 安装 APK
adb install app-debug.apk
```

### 方法 B: 直接安装

1. 将 APK 文件传输到手机
2. 在手机上找到 APK 文件
3. 点击安装
4. 允许安装未知来源应用
5. 完成安装

## 🔧 查看构建日志

如果构建失败或想查看详情：

1. 点击失败的构建任务
2. 点击左侧的 `Build APK`
3. 展开各个步骤查看详细日志
4. 找到错误信息并修复

## 🔄 更新代码

修改代码后重新构建：

```bash
# 查看修改的文件
git status

# 添加修改
git add .

# 提交
git commit -m "Update: 描述你的修改"

# 推送到 GitHub
git push
```

每次推送都会自动触发新的构建。

## 📊 构建状态徽章

在 README.md 中添加徽章以显示构建状态：

```markdown
![Build Status](https://github.com/你的用户名/WeatherApp/workflows/Build%20Android%20APK/badge.svg)
```

记得将 `你的用户名` 替换成实际的 GitHub 用户名！

## 🐛 常见问题

### Q: 推送失败？
A: 检查远程仓库地址是否正确：
```bash
git remote -v
```

### Q: 构建失败？
A: 查看 Actions 日志，常见原因：
- 代码语法错误
- 依赖版本冲突
- Gradle 配置问题

### Q: APK 下载不了？
A: 确保构建成功（绿色对勾），Artifacts 会保留 30 天。

### Q: 每次推送都构建，能跳过吗？
A: 在 commit 消息中添加 `[skip ci]` 或 `[ci skip]`：
```bash
git commit -m "Update docs [skip ci]"
```

## 🎉 完成！

现在你可以：
- ✅ 自动编译 APK
- ✅ 在任何地方下载最新版本
- ✅ 通过 GitHub 追踪代码变更
- ✅ 协作开发（如果仓库是 public）

---

有问题？查看 [GitHub Actions 详细文档](GITHUB_ACTIONS.md) 🦞
