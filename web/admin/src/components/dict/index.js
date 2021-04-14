import MyDict from './dict.vue'
const Dict = {
  install: function (Vue) {
    Vue.component('Dict', MyDict)
  }
}
export default Dict
