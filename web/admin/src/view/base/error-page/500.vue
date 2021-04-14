<template>
  <error-content code="500" desc="发生了不可知事件,程序猿正在加班中" :src="src"/>
</template>

<script>
import error404 from '@/assets/images/error-page/error-500.svg'
import errorContent from './error-content.vue'
export default {
  name: 'error_500',
  components: {
    errorContent
  },
  data () {
    return {
      src: error404
    }
  }
}
</script>
