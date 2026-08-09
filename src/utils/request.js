import { ElMessage } from 'element-plus'
import router from "../router"
import axios from 'axios'

// 创建 axios 实例
const request = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL, // + '/api', // 2026.8.9 21：01 添加： + '/api'
    timeout: 30000 // 后台接口超时设置

})

// request拦截器
// 可以自请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    return config
}, error => {
    return Promise.reject(error)
});


// reponse拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 当权限验证不通过时给出提示
        if (res.code === '401') {
            ElMessage.error(res.msg);
            router.push('/login')
        }
        return res;
    },
        error => {
        console.log('err' + error)
            return Promise.reject(error)
    }
)

export default request
