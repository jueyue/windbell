import axios from 'axios'
import store from '@/store'
import { getToken, removeToken, setToken } from '@/libs/util'
import { Message } from 'iview'
// import { Spin } from 'iview'
const addErrorLog = errorInfo => {
  const { statusText, status, request: { responseURL } } = errorInfo
  let info = {
    type: 'ajax',
    code: status,
    mes: statusText,
    url: responseURL
  }
  if (!responseURL.includes('save_error_logger')) store.dispatch('addErrorLog', info)
}

class HttpRequest {
  constructor (baseUrl = baseURL) {
    this.baseUrl = baseUrl
    this.queue = {}
  }
  getInsideConfig () {
    const config = {
      baseURL: this.baseUrl,
      headers: {
        productCode: 1001,
        token: getToken()
      }
    }
    return config
  }
  destroy (url) {
    delete this.queue[url]
    if (!Object.keys(this.queue).length) {
      // Spin.hide()
    }
  }
  interceptors (instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      // 添加全局的loading...
      if (!Object.keys(this.queue).length) {
        // Spin.show() // 不建议开启，因为界面不友好
      }
      this.queue[url] = true
      return config
    }, error => {
      return Promise.reject(error)
    })
    // 响应拦截
    instance.interceptors.response.use(res => {
      this.destroy(url)
      if (res.status !== 200) {
        Message.error('url:' + url + ', 访问失败,异常码:' + res.status)
        return false
      }
      if (res.headers.token) {
        setToken(res.headers.token)
      }
      let { data } = res
      if (!(data instanceof Blob)) {
        if (data.code !== 200) {
          // 后端服务在个别情况下回报201，待确认
          if (data.code === 401) {
            removeToken()
            window.location.href = '/#/login'
            Message.error('未登录，或登录失效，请登录')
          } else {
            if (data.msg) {
              Message.error({
                content: data.msg,
                duration: 10,
                closable: true
              })
            }
          }
          return false
        }
      }
      // 把后端的包装对象去掉
      return data.data
    }, error => {
      this.destroy(url)
      addErrorLog(error.response)
      return Promise.reject(error)
    })
  }
  request (options) {
    const instance = axios.create()
    options = Object.assign(this.getInsideConfig(), options)
    this.interceptors(instance, options.url)
    return instance(options)
  }

  get (url) {
    return this.request({
      url: url,
      method: 'get'
    })
  }

  post (url, params) {
    return this.request({
      url: url,
      data: params,
      method: 'post'
    })
  }
}
export default HttpRequest
