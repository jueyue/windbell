// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import iView from 'iview'
import i18n from '@/locale'
import config from '@/config'
import importDirective from '@/directive'
import installPlugin from '@/plugin'
import 'iview/dist/styles/iview.css'
import './index.less'
import '@/assets/icons/iconfont.css'
// 风铃通用服务
// 通用请求JS
import {C, U, R, D, L, G, P} from '@/libs/api.request'
import { dict, getDictVal, numDict } from '@/libs/common.request'
import { getTenantId } from '@/api/user'
// 通用CSS
import '@/css/info-base.css'
import '@/css/list-base.css'
// 实际打包时应该不引入mock
/* eslint-disable */
if (process.env.NODE_ENV !== 'production') require('@/mock')

Vue.use(iView, {
  i18n: (key, value) => i18n.t(key, value)
})
/**
 * @description 注册admin内置插件
 */
installPlugin(Vue)
/**
 * @description 生产环境关掉提示
 */
Vue.config.productionTip = false
/**
 * @description 全局注册应用配置
 */
Vue.prototype.$config = config
/**
 * 注册指令
 */
importDirective(Vue)

/**
 * 注册自定义函数
 */
Vue.prototype.C = C
Vue.prototype.U = U
Vue.prototype.R = R
Vue.prototype.D = D
Vue.prototype.L = L
Vue.prototype.G = G
Vue.prototype.P = P
Vue.prototype.dict = dict
Vue.prototype.numDict = numDict
Vue.prototype.getDictVal = getDictVal
Vue.prototype.getTenantId = getTenantId
Vue.prototype.PAGE_SIZE_MAX = 100


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  store,
  render: h => h(App)
})
