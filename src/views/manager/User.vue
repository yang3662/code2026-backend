<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input style="width: 300px " v-model="data.name" placeholder="请输入名称查询" :prefix-icon="Search"/>
      <el-button @click="load" type="primary" style="margin-left: 10px">查询</el-button>
      <el-button @click="reset" type="info">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <div style="margin-bottom: 10px">
        <el-button @click="handleAdd" type="primary">新增</el-button>
      </div>

      <div>
        <el-table :data="data.tableData" stripe style="width: 100%" v-loading="data.loading">
          <el-table-column prop="username" label="账号" />
          <el-table-column prop="name" label="姓名" />
          <el-table-column prop="avatar" label="头像">
            <template #default="scope">
              <el-image v-if="scope.row.avatar" style="width: 50px;height: 50px;display: block;border-radius: 50%"
                        :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]" preview-teleported></el-image>
            </template>
          </el-table-column>
          <el-table-column prop="role" label="角色" />
          <el-table-column prop="account" label="余额" />
          <el-table-column label="操作" width="180px" fixed="right">
            <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <div class="card">
      <el-pagination v-model:current-page="data.pageNum" v-model:page-size="data.pageSize"
                     @current-change="load" background layout="total, prev, pager, next" :total="data.total" />
    </div>

    <el-dialog v-model="data.formVisible" title="用户信息" width="30%" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding-right: 30px">
        <el-form-item prop="username" label="账号" >
          <el-input :disabled="data.form.id !==undefined" v-model="data.form.username" placeholder="请输入账号" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="name" label="姓名" >
          <el-input v-model="data.form.name" placeholder="请输入姓名" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="avatar" label="头像" >
          <el-upload
              :action="baseUrl + '/files/upload'"
              list-type="picture"
              :on-success="handleFileUpload"
          >
            <el-button type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.formVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { Search } from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage, ElMessageBox} from "element-plus";

const baseUrl = import.meta.env.VITE_BASE_URL
const formRef = ref()
const data = reactive({
  name: null,
  tableData: [],
  total: 0,
  pageNum: 1,
  pageSize: 5,
  formVisible: false,
  form: {},
  rules: {
    username: [
      { required: true, message: '请输入账号', trigger: 'blur' },
    ],
  }

})

// 前端的分页查询数据的函数,前端接口是get，与UserController.java中后端接口分别对应
const load = () => {
  request.get('/user/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res => {
    if (res.code === '200') {
      data.tableData = res.data?.list
      data.total = res.data?.total
    }else {
      ElMessage.error(res.msg)
    }
  })

}
load()

// 前端的重置函数
const reset = () => {
  data.name = null
  load()
}

// 前端的删除函数，前端接口是delete，与UserController.java中后端接口分别对应
const del = (id) => {
  ElMessageBox.confirm('您确定删除吗？','删除确认',{ type: 'warning' }).then(res => {
    request.delete('/user/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

// 前端的处理新增的函数
const handleAdd = () => {
  data.form ={}
  data.formVisible = true
}

// 前端的处理编辑的函数
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// 前端新增函数，前端接口是post，与UserController.java中后端接口分别对应
const add = () => {
  request.post('/user/add',data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 前端更新或编辑函数，前端接口是put，与UserController.java中后端接口分别对应
const update = () => {
  request.put('/user/update',data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 前端的确定或保存函数，判断后调用新增函数或更新函数
const save = () => {
  formRef.value.validate((valid) => {
    if (valid) { // 表单校验通过
      // 以下四行代码可以写成三目或三元表达格式 data.form.id ? update() : add()
      if (data.form.id) {
        update()
      }else {
        add()
      }
    }
  })
}

// 表单头像上传组件的回调函数，res.data就是头像的url
const handleFileUpload = (res) => {
  data.form.avatar = res.data
}
</script>

