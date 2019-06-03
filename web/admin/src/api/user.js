import axios from '@/libs/api.request'

export const login = ({account, password}) => {
  const data = {
    account,
    password
  }
  return axios.request({
    url: '/admin/user/login',
    data,
    method: 'post'
  })
}

export const getUserInfo = (userId) => {
  return axios.request({
    url: '/admin/user/userInfo/' + userId,
    method: 'get'
  }).then(data => {
    sessionStorage.setItem('user', data)
    sessionStorage.setItem('tenantId', data.tenantId)
    sessionStorage.setItem('userId', data.userId)
    return data
  })
}

export const logout = (token) => {
  return axios.request({
    url: '/admin/user/logout',
    method: 'post'
  })
}

export const getUser = () => {
  return sessionStorage.getItem('user')
}

export const getTenantId = () => {
  return sessionStorage.getItem('tenantId')
}

export const getUserId = () => {
  return sessionStorage.getItem('userId')
}
