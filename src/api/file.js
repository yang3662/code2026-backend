import request from '@/utils/request'

export const fileApi = {
    // 单文件上传
    uploadFile(file) {
        const formData = new FormData()
        formData.append('file', file)
        return request.post('/files/upload', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    },

    // 多文件上传
    uploadMultipleFiles(files) {
        const formData = new FormData()
        files.forEach(file => {
            formData.append('files', file)
        })
        return request.post('/files/upload/multiple', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    }
}