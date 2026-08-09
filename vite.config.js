import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// 导入对应包

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";

import ElementPlus from 'unplugin-element-plus/vite'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        AutoImport({
            resolvers: [ElementPlusResolver(
                { importStyle: 'sass' }
            )],
        }),
        Components({
            resolvers: [ElementPlusResolver(
                { importStyle: 'sass' }
            )],
        }),

        // 按需要定制主题配置
        ElementPlus({
            useSource: true,
        }),
    ],
    // 添加这部分配置 - 路径别名
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },

    server: {
        port: 5173,
        open: true,
        proxy: {
            '/api': {
                target: 'http://localhost:9090',  // 您的后端端口
                changeOrigin: true,
                // 如果后端接口没有 /api 前缀，去掉它
                rewrite: (path) => path.replace(/^\/api/, '')
            }
        }
    },

    css: {
        preprocessorOptions: {
            scss: {
                api: 'modern-compiler', // 使用现代 API
                silenceDeprecations: ['legacy-js-api'], // 或者静默警告
                // ⭐ 关键：添加这行配置，引入自定义主题文件
                additionalData: `@use "@/assets/css/index.scss" as *;`
            },
            sass: {
                api: 'modern-compiler',
                silenceDeprecations: ['legacy-js-api'],
            }
        }
    }
})