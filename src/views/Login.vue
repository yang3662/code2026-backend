<template>
  <div class="login-container">
    <div class="login-box">
      <div style="font-weight: bold; font-size: 30px; text-align: center; margin-bottom: 30px; color: #1967e3">欢 迎 登 录</div>
      <el-form
          :model="data.form"
          ref="formRef"
          :rules="data.rules"
          @submit.prevent="login"
      >
        <el-form-item prop="username">
          <el-input
              :prefix-icon="User"
              size="large"
              v-model="data.form.username"
              placeholder="请输入账号"
              @keyup.enter="login"
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
              :prefix-icon="Lock"
              size="large"
              v-model="data.form.password"
              placeholder="请输入密码"
              show-password
              @keyup.enter="login"
          />
        </el-form-item>

        <el-form-item prop="role">
          <el-select size="large" style="width: 100%" v-model="data.form.role" >
            <el-option value="普通用户"  label="普通用户" />
            <el-option value="管理员"  label="管理员" />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button size="large" type="primary" style="width: 100%" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
      <div style="text-align: center; margin-top: 20px;">
        还没有账号？请 <a href="/register">注 册</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'
import { useRouter } from 'vue-router'

const router = useRouter()
const formRef = ref()

const data = reactive({
  form: { role: '管理员' },
  rules: {
    username: [
      { required: true, message: '请输入账号', trigger: 'blur' },
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
    ],
  }
})

// 对应教程04的04：00进度条
const login = () => {
  formRef.value.validate((valid => {
    if (valid) {
      //调用后台的接口
      request.post('login',data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success("登录成功")
          localStorage.setItem('system-user',JSON.stringify(res.data))
          if (res.data.role === '管理员') {
            router.push('/manager/home') // 登录时如果是管理员就访问管理员主页
          }else {
            router.push('/front/home') // 登录时如果不是管理员就访问前台主页
          }
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })).catch(error => {
    console.error(error)
  })
}

// // 或者以上代码如下皆可，对应教程04的04：00进度条
// const login = () => {
//   formRef.value.validate((valid) => {  // 正确的箭头函数语法
//     if (valid) {
//       //调用后台的接口
//       request.post('/login', data.form).then(res => {
//         // 注意：因为 request 拦截器已经返回了 res.data，所以这里的 res 就是后端返回的数据
//         if (res.code === '200') {
//           ElMessage.success("登录成功")
//           router.push('/manager/home')
//           localStorage.setItem('system-user', JSON.stringify(res.data))
//         } else {
//           ElMessage.error(res.msg)
//         }
//       }).catch(error => {  // 在这里处理网络错误
//         console.error(error)
//         ElMessage.error('登录失败，请稍后重试')
//       })
//     } else {
//       ElMessage.warning('请填写完整的登录信息')
//     }
//   })
// }

</script>

<style scoped>
.login-container {
  height: 100vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #2e3143;
  background-size: cover;
}

.login-box {
  width: 350px;
  padding: 50px 30px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(255, 255, 255, 0.3);
  background-color: #fff;
}
</style>