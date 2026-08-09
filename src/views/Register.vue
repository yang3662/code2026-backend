<template>
  <div class="login-container">
    <div class="login-box">
      <div style="font-weight: bold; font-size: 30px; text-align: center; margin-bottom: 30px; color: #0c9c7a">欢 迎 注 册</div>
      <el-form :model="data.form" ref="formRef" :rules="data.rules" @submit.prevent="login">
        <el-form-item prop="username">
          <el-input :prefix-icon="User" size="large" v-model="data.form.username" placeholder="请输入账号" />
        </el-form-item>

        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" size="large" v-model="data.form.password" placeholder="请输入密码" show-password />
        </el-form-item>

        <el-form-item prop="newPassword">
          <el-input :prefix-icon="Lock" size="large" v-model="data.form.newPassword" placeholder="请确认密码" show-password />
        </el-form-item>

        <el-form-item>
          <el-button size="large" style="width: 100%; background-color: #0c9c7a; border-color: #0c9c7a; color: white" @click="register">注 册</el-button>
        </el-form-item>
      </el-form>
      <div style="text-align: center; margin-top: 20px;">
        已有账号？请 <a href="/login">登 录</a>
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

const data = reactive({
  form: { role: '普通用户' },
  rules: {
    username: [
      { required: true, message: '请输入账号', trigger: 'blur' },
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
    ],
    newPassword: [
      { required: true, message: '请确认密码', trigger: 'blur' },
    ],
  }
})

const formRef = ref()

// 对应教程04的04：00进度条
const register = () => {
  formRef.value.validate((valid => {
    if (valid) {
      //调用后台的接口
      request.post('register',data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success("恭喜您！注册成功")
          router.push('/login')
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
  background: #0c9c7a;
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