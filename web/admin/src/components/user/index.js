import MyUser from './user.vue'
const User = {
  install: function (Vue) {
    Vue.component('User', MyUser)
  }
}
export default User
