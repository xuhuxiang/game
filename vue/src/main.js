import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/main.css'
import '@/assets/fonts/font-awesome.css'

import VideoPlayer from 'vue-video-player'
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'

Vue.config.productionTip = false
Vue.prototype.$bus = new Vue();
Vue.use(ElementUI, { size: "small"})
Vue.use(VideoPlayer)

new Vue({
  router, //从router文件中导入路由实例
  render: h => h(App) //这是一个渲染函数，告诉vue如何渲染根组件app
}).$mount('#app') //将vue实例挂在到页面上的ID为app的元素上。
