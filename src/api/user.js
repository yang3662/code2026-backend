import request from '@/utils/request'

export const userApi = {
    // 获取用户列表
    getUsers(params) {
        return request.get('/user/selectPage',{ params })
    },

    // 获取用户详情
    getUserById(id) {
        return request.get(`/user/${id}`)
    },

    // 创建用户
    createUser(data) {
        return request.post('/user', data)
    },

    // 更新用户
    updateUser(id, data) {
        return request.put(`/user/${id}`, data)
    },

    // 删除用户
    deleteUser(id) {
        return request.delete(`/user/${id}`)
    }
}