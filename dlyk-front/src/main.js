//import './style.css'  //导入css样式不需要from子句
import { createApp } from 'vue'

//导入element plus包和样式
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

//导入element plus图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//导入App
import App from './App.vue'

//导入router
import router from './router/index.js'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(ElementPlus).use(router).mount('#app')
