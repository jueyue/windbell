<template>
  <i-cell-group>
    <i-cell title="只显示箭头" is-link></i-cell>
    <i-cell title="跳转到首页" is-link url="/pages/dashboard/index"></i-cell>
    <i-cell title="只有 footer 点击有效" is-link url="/pages/dashboard/index" only-tap-footer></i-cell>
    <i-cell title="开关">
      <switch slot="footer" checked/>
    </i-cell>
  </i-cell-group>
</template>

<script>
  export default {
    computed: {
    },
    methods: {
      increment () {
      },
      decrement () {
      }
    }
  }
</script>

<style>
  .counter-warp {
    text-align: center;
    margin-top: 100px;
  }

  .home {
    display: inline-block;
    margin: 100px auto;
    padding: 5px 10px;
    color: blue;
    border: 1px solid blue;
  }
</style>
