import Vue from 'vue'
import Router from 'vue-router'
import {routers as routes, _import} from './routers'
import store from '@/store'
import iView from 'view-design'
import { getToken, canTurnTo } from '@/libs/util'
import { isURL } from '@/libs/validate'
import { getRouters } from '@/api/user'
import config from '@/config'
import Main from '@/components/main'
const { homeName } = config

Vue.use(Router)
const router = new Router({
  routes,
  isAddRouters: false,
  mode: 'history'
})
const LOGIN_PAGE_NAME = 'login'

const turnTo = (to, access, next) => {
  if (canTurnTo(to.name, access, routes)) next() // 有权限，可访问
  else next({ replace: true, name: 'error_401' }) // 无权限，重定向到401页面
}

const addRouters = (data) => {
  if (!router.isAddRouters) {
    data.forEach(d => {
      loadComponent([d])
      routes.push(d)
      router.addRoutes([d]) // 动态添加路由
      router.isAddRouters = true
    })
  }
}

const loadComponent = (data) => {
  data.forEach(route => {
    if (route.menu === '3') {
      route['component'] = Main
    } else if (isURL(route.path)) {
      route.meta.iframeUrl = route.href
      route['meta']['type'] = 'iframe'
    } else if (route.target === 'iframe') {
      route['meta']['iframeUrl'] = `${process.env.VUE_APP_SERVER_URL}${route.href}`
    } else {
      try {
        route['component'] = _import(route.path) || null
      } catch (e) {
        console.log(e)
      }
    }
    if (route.children && route.children.length > 0) {
      loadComponent(route.children)
    }
  })
}

const gotoAssess = (to, store, next) => {
  if (store.state.user.hasGetInfo) {
    turnTo(to, store.state.user.access, next)
  } else {
    store.dispatch('getUserInfo', store.state.user.userId).then(user => {
      // 拉取用户信息，通过用户权限和跳转的页面的name来判断是否有权限访问;access必须是一个数组，如：['super_admin'] ['super_admin', 'admin']
      turnTo(to, user.access, next)
    }).catch(() => {
      next({
        name: LOGIN_PAGE_NAME
      })
    })
  }
}

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start()
  const token = getToken()
  if (!token && to.name !== LOGIN_PAGE_NAME) {
    // 未登录且要跳转的页面不是登录页
    next({
      name: LOGIN_PAGE_NAME // 跳转到登录页
    })
  } else if (!token && to.name === LOGIN_PAGE_NAME) {
    // 未登陆且要跳转的页面是登录页
    next() // 跳转
  } else if (token && to.name === LOGIN_PAGE_NAME) {
    // 已登录且要跳转的页面是登录页
    next({
      name: homeName // 跳转到homeName页
    })
  } else {
    if (router.isAddRouters) {
      gotoAssess(to, store, next)
    } else {
      // 没获取用户信息,说明没有获取路由菜单
      getRouters(store.state.user.userId).then(routerArr => {
        addRouters(routerArr)
        gotoAssess(to, store, next)
      })
    }
  }
})

router.afterEach(to => {
  iView.LoadingBar.finish()
  window.scrollTo(0, 0)
})

export default router
