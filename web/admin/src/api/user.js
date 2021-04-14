import axios from '@/libs/api.request'
import {removeToken} from '@/libs/util'

export const WBE_TYPE = '1001'

export const login = ({account, password}) => {
  const data = {
    account: account,
    password: password,
    product: 1001,
    type: 1
  }
  return axios.request({
    url: '/admin/userAuth/login',
    data,
    method: 'post'
  })
}

export const getUserInfo = (userId) => {
  if (!userId || userId === 'undefined') {
    removeToken()
    window.location.href = '/#/login'
    return
  }
  return axios.request({
    url: `/admin/user/userInfo/${WBE_TYPE}/` + userId,
    method: 'get'
  }).then(data => {
    return data
  })
}

export const getRouters = (userId) => {
  if (!userId || userId === 'undefined') {
    removeToken()
    window.location.href = '/#/login'
    return
  }
  return axios.request({
    url: `/admin/menu/menuTree/${WBE_TYPE}/` + userId,
    method: 'get'
  })
}

export const logout = (token) => {
  return axios.request({
    url: '/admin/userAuth/logout',
    method: 'post'
  })
}
