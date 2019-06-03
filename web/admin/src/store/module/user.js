import { login, logout, getUserInfo } from '@/api/user'
import { setUserId, getUserId, setToken, getToken } from '@/libs/util'

export default {
  state: {
    account: '',
    userId: getUserId(),
    avatarImgPath: '',
    token: getToken(),
    access: '',
    hasGetInfo: false
  },
  mutations: {
    setAvatar (state, avatarPath) {
      state.avatarImgPath = avatarPath
    },
    setUserId (state, id) {
      state.userId = id
      setUserId(id)
    },
    setUserName (state, name) {
      state.account = name
    },
    setAccess (state, access) {
      state.access = access
    },
    setToken (state, token) {
      state.token = token
      setToken(token)
    },
    setHasGetInfo (state, status) {
      state.hasGetInfo = status
    }
  },
  actions: {
    // 登录
    handleLogin ({ commit }, { account, password }) {
      account = account.trim()
      return new Promise((resolve, reject) => {
        login({
          account,
          password
        }).then(res => {
          const data = res
          commit('setToken', data.token)
          commit('setUserId', data.userId)
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 退出登录
    handleLogOut ({ state, commit }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('setToken', '')
          commit('setUserId', '')
          commit('setAccess', [])
          resolve()
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 获取用户相关信息
    getUserInfo ({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getUserInfo(state.userId).then(res => {
            const data = res
            commit('setAvatar', data.avatar)
            commit('setUserName', data.name)
            commit('setAccess', 'admin')
            commit('setHasGetInfo', true)
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    }
  }
}
