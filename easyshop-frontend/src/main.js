import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import axios from 'axios'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
import { mockXHR } from '../mock'
if (process.env.NODE_ENV === 'production') {
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false
Vue.prototype.$axios = axios

function getServerConfig() {
  return new Promise((resolve, reject) => {
    axios.get('./serverConfig.json').then((result) => {
      console.log(result) // 看打印出来的结果
      const config = result.data
      for (const key in config) {
        Vue.prototype[key] = config[key]
      }
      // 验证是否已经把属性挂在了Vue上
      console.log(Vue.prototype.BASE_ADDR)
      resolve()
    }).catch((error) => {
      console.log(error)
      reject()
    })
  })
}

async function init() {
  await getServerConfig()
  axios.defaults.baseURL = Vue.prototype.BASE_API
  new Vue({
    router,
    store,
    render: h => h(App)
  }).$mount('#app')
}

init()
