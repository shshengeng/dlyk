//import './style.css'  //导入css样式不需要from子句
import { createApp } from 'vue'

//导入element plus包和样式
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

//import App from './App.vue'

import LoginView from "./view/LoginView.vue";

createApp(LoginView).use(ElementPlus).mount('#app')
