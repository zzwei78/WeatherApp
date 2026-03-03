#!/bin/bash
# GitHub 推送脚本
# 用户名: zzwei78
# 仓库: WeatherApp

echo "======================================"
echo "  Android Weather App 推送到 GitHub"
echo "  用户名: zzwei78"
echo "======================================"
echo ""

echo "步骤 1/3: 准备工作"
echo "--------------------------------------"
echo "✅ Git 远程仓库已配置: https://github.com/zzwei78/WeatherApp.git"
echo "✅ 分支已重命名为: main"
echo ""

echo "步骤 2/3: 检查状态"
echo "--------------------------------------"
git status
echo ""

echo "步骤 3/3: 推送代码"
echo "======================================"
echo "⚠️  重要提示："
echo ""
echo "1. 首次推送需要使用 GitHub Personal Access Token"
echo "2. 密码输入时不会显示任何字符（这是正常的）"
echo "3. Token 只在创建时显示一次，请妥善保管"
echo ""
echo "如何创建 Personal Access Token："
echo "--------------------------------------"
echo "1. 访问: https://github.com/settings/tokens"
echo "2. 点击 'Generate new token' → 'Generate new token (classic)'"
echo "3. 勾选 'repo' 权限"
echo "4. 设置过期时间（建议 90 days 或 No expiration）
echo "5. 点击 'Generate token'"
echo "6. 复制生成的 token（只显示一次！）"
echo ""
echo "======================================"
echo ""
echo "现在执行推送命令："
echo ""
echo "git push -u origin main"
echo ""
echo "当提示输入时："
echo "Username: zzwei78"
echo "Password: <粘贴你的 Personal Access Token>"
echo ""
echo "======================================"
echo ""

# 执行推送
git push -u origin main
