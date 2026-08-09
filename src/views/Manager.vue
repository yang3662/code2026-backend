<template>
  <div>
    <!-- 顶部导航栏 -->
    <div style="height: 60px; background-color: #2e3143; display: flex; align-items: center; border-bottom: 1px solid #add">
      <div style="flex: 1">
        <div style="padding-left: 20px; display: flex; align-items: center">
          <img src="@/assets/imgs/logo.png" alt="" style="width: 40px; height: 40px; margin-right: 10px">
          <div style="color: white; font-size: 18px; font-weight: bold">小白做毕设2026</div>
        </div>
      </div>
      <div style="display: flex; align-items: center; padding-right: 20px; gap: 12px; cursor: pointer">
        <img style="width: 40px; height: 40px; border-radius: 50%; object-fit: cover" :src="data.user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="">
        <span style="color: white; font-size: 14px">{{ data.user.name || data.user.username || '代码小白' }}</span>
      </div>
    </div>

    <!-- 主体内容区 -->
    <div style="display: flex; height: calc(100vh - 60px)">
      <!-- 左侧菜单栏 -->
      <div style="width: 220px; background-color: #f5f5f5; box-shadow: 2px 0 8px rgba(0,0,0,0.05)">
        <el-menu
            router
            style="height: 100%; border-right: none"
            :default-active="router.currentRoute.value.path"
            :default-openeds="['user','info']"
        >

          <el-menu-item index="/manager/home">
            <el-icon><HomeFilled /></el-icon>
            <span>系统首页</span>
          </el-menu-item>

          <el-sub-menu index="info">
            <template #title>
              <el-icon><Memo /></el-icon>
              <span>信息管理</span>
            </template>

            <el-menu-item index="/manager/category">
              <el-icon><Document /></el-icon>
              <span>商品分类</span>
            </el-menu-item>

            <el-menu-item index="/manager/goods">
              <el-icon><Document /></el-icon>
              <span>商品信息</span>
            </el-menu-item>

            <el-menu-item index="/manager/carousel">
              <el-icon><Document /></el-icon>
              <span>轮播图信息</span>
            </el-menu-item>

            <el-menu-item index="/manager/collect">
              <el-icon><Document /></el-icon>
              <span>用户收藏</span>
            </el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="user">
            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>

            <el-menu-item index="/manager/user">
              <el-icon><User /></el-icon>
              <span>普通用户</span>
            </el-menu-item>

            <el-menu-item index="/manager/admin">
              <el-icon><User /></el-icon>
              <span>管理员信息</span>
            </el-menu-item>
          </el-sub-menu>

          <el-menu-item index="/manager/person">
            <el-icon><User /></el-icon>
            <span>个人信息</span>
          </el-menu-item>

          <el-menu-item index="/manager/password">
            <el-icon><Lock /></el-icon>
            <span>修改密码</span>
          </el-menu-item>

          <el-menu-item index="logout" @click="logout">
            <el-icon><SwitchButton /></el-icon>
            <span>退出系统</span>
          </el-menu-item>
        </el-menu>
      </div>

      <!-- 右侧内容区 -->
      <div style="flex: 1; padding: 20px; overflow-y: auto; background-color: #f9f9f9">
        <router-view @updateUser="updateUser" />
      </div>
    </div>
  </div>
</template>


<script setup>
import { reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import router from "@/router/index.js"
import { HomeFilled, User, SwitchButton } from '@element-plus/icons-vue'

// 以下代码是教程04进度条 1：04：47，和以下AI补全的代码作用相近
const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}')
})

if (!data.user?.id) {
  ElMessage.error('请登录')
  router.push('/login')
}

const updateUser = () => {
  data.user = JSON.parse(localStorage.getItem('system-user') || '{}')
}

const logout = () => {
  router.push('/login')
  ElMessage.success('退出成功')
  localStorage.removeItem('system-user')
}


</script>

<style scoped>
/* 菜单选中高亮状态样式 */
.el-menu-item.is-active {
  background-color: #d7d7e6 !important;
}

/* 菜单悬浮效果 */
.el-menu-item:hover {
  color: #000;
}

</style>