import HttpRequest from '@/libs/axios'
import config from '@/config/index'
import { syGet } from '@/libs/thirdtool'

const baseUrl = process.env.NODE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.pro

const axios = new HttpRequest(baseUrl)
export default axios

/**
 * 查询字典列表
 * @param ids
 * @returns {*}
 */
export const dict = (key) => {
  if (!key) {
    /* Message.warning('字典查询数据不允许为空') -- 太多空值查询,先注释掉提示 */
    return
  }
  if (sessionStorage.getItem('dict_' + key)) {
    return Promise.resolve(JSON.parse(sessionStorage.getItem('dict_' + key)))
  }
  return axios.get('admin/dict/subByKey/' + key).then(data => {
    sessionStorage.setItem('dict_' + key, JSON.stringify(data))
    return data
  }
  )
}

/**
 * 查询字典列表,值强转成int,否则vue int 和 string 不相等
 * @param ids
 * @returns {*}
 */
export const numDict = (key) => {
  if (!key) {
    /* Message.warning('字典查询数据不允许为空') -- 太多空值查询,先注释掉提示 */
    return
  }
  if (sessionStorage.getItem('dict_' + key)) {
    return Promise.resolve(JSON.parse(sessionStorage.getItem('dict_' + key)))
  }
  return axios.get('admin/dict/subByKey/' + key).then(data => {
    let arr = []
    data.forEach(function (v) {
      v.key = Number.parseInt(v.key)
      arr.push(v)
    })
    sessionStorage.setItem('dict_' + key, JSON.stringify(arr))
    return data
  }
  )
}

/**
 * 获取字典的值
 * @param ids
 * @returns {*}
 */
export const getDictVal = (key, val) => {
  if (!key || !val) {
    /* Message.warning('字典值查询数据不允许为空') -- 太多空值查询,先注释掉提示 */
    return
  }
  if (sessionStorage.getItem('dict_val_' + key + '_' + val)) {
    return sessionStorage.getItem('dict_val_' + key + '_' + val)
  }
  let dict = syGet('admin/dict/subDetail/' + val + '/' + key)
  sessionStorage.setItem('dict_val_' + key + '_' + val, dict.name)
  return dict.name
}
