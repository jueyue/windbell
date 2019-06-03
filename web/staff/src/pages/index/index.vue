<template>
  <div>
    <i-grid>
      <i-grid-item @click="clickHandle('notice')">
        <i-grid-icon>
          <image src="/static/icon/notice.png"/>
        </i-grid-icon>
        <i-grid-label>通知</i-grid-label>
      </i-grid-item>
      <i-grid-item @click="clickHandle('wait-search')">
        <i-grid-icon>
          <image src="/static/icon/wait-search.png"/>
        </i-grid-icon>
        <i-grid-label>待调查</i-grid-label>
      </i-grid-item>
      <i-grid-item @click="clickHandle('search')">
        <i-grid-icon>
          <image src="/static/icon/search.png"/>
        </i-grid-icon>
        <i-grid-label>调查</i-grid-label>
      </i-grid-item>
    </i-grid>
    <i-grid>
      <i-grid-item @click="clickHandle('video')">
        <i-grid-icon>
          <image src="/static/icon/video.png"/>
        </i-grid-icon>
        <i-grid-label>视频上传</i-grid-label>
      </i-grid-item>
      <i-grid-item @click="clickHandle('audit')">
        <i-grid-icon>
          <image src="/static/icon/audit.png"/>
        </i-grid-icon>
        <i-grid-label>审核</i-grid-label>
      </i-grid-item>
      <i-grid-item @click="clickHandle('restart')">
        <i-grid-icon>
          <image src="/static/icon/restart.png"/>
        </i-grid-icon>
        <i-grid-label>重新发起</i-grid-label>
      </i-grid-item>
    </i-grid>
    <i-grid>
      <i-grid-item @click="clickHandle('closure')">
        <i-grid-icon>
          <image src="/static/icon/closure.png"/>
        </i-grid-icon>
        <i-grid-label>封户</i-grid-label>
      </i-grid-item>
      <i-grid-item @click="clickHandle('pay')">
        <i-grid-icon>
          <image src="/static/icon/pay.png"/>
        </i-grid-icon>
        <i-grid-label>打款</i-grid-label>
      </i-grid-item>
      <i-grid-item @click="clickHandle('statistics')">
        <i-grid-icon>
          <image src="/static/icon/statistics.png"/>
        </i-grid-icon>
        <i-grid-label>统计</i-grid-label>
      </i-grid-item>
    </i-grid>
    <i-divider content="二手车"></i-divider>
    <i-grid>
      <i-grid-item>
        <i-grid-icon>
          <image src="/static/icon/notice.png"/>
        </i-grid-icon>
        <i-grid-label>二手车</i-grid-label>
      </i-grid-item>
      <i-grid-item>
        <i-grid-icon>
          <image src="/static/icon/customer.png"/>
        </i-grid-icon>
        <i-grid-label>二手车评估</i-grid-label>
      </i-grid-item>
      <i-grid-item>
        <i-grid-icon>
          <image src="/static/icon/notice.png"/>
        </i-grid-icon>
        <i-grid-label>二手车报告</i-grid-label>
      </i-grid-item>
    </i-grid>

  </div>
</template>

<script>
  import card from '@/components/card'

  export default {
    data () {
      return {
        motto: 'Hello miniprograme',
        userInfo: {
          nickName: 'mpvue',
          avatarUrl: 'http://mpvue.com/assets/logo.png'
        }
      }
    },

    components: {
      card
    },

    methods: {
      bindViewTap (url) {
        mpvue.navigateTo({ url })
      },
      clickHandle (type) {
        console.log('clickHandle:', type)
        this.bindViewTap('../' + type + '/main')
      },
      login () {
        mpvue.login({
          success (res) {
            if (res.code) {
              console.log(res)
              // 发起网络请求
              wx.request({
                url: 'https://test.com/onLogin',
                data: {
                  code: res.code
                }
              })
            } else {
              console.log('登录失败！' + res.errMsg)
            }
          }
        })
      }
    },

    created () {
      this.login()
    }
  }
</script>

<style scoped>
  .userinfo {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .userinfo-avatar {
    width: 128 rpx;
    height: 128 rpx;
    margin: 20 rpx;
    border-radius: 50%;
  }

  .userinfo-nickname {
    color: #aaa;
  }

  .usermotto {
    margin-top: 150px;
  }

  .form-control {
    display: block;
    padding: 0 12px;
    margin-bottom: 5px;
    border: 1px solid #ccc;
  }

  .all {
    width: 7.5rem;
    height: 1rem;
    background-color: blue;
  }

  .all:after {
    display: block;
    content: '';
    clear: both;
  }

  .left {
    float: left;
    width: 3rem;
    height: 1rem;
    background-color: red;
  }

  .right {
    float: left;
    width: 4.5rem;
    height: 1rem;
    background-color: green;
  }
</style>
