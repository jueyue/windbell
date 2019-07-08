<template>
  <Modal v-model="isShow" width="25%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">{{this.form.name}}-资源授权</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Tree :data="resourceData" show-checkbox multiple check-strictly="true" empty-text="数据正在加载"></Tree>
    </div>
    <div slot="footer">
      <Button type="primary" @click="submit"  v-show="!disable">提交</Button>
    </div>
  </Modal>
</template>

<script>

import {getIds, getListByFieldJudge} from '@/libs/util'

export default {
  name: 'resource-info',
  data () {
    return {
      form: {},
      isShow: false,
      resourceData: []
    }
  },
  methods: {
    openModel (data) {
      this.isShow = true
      this.form = data
      this.resourceData = []
      this.P('admin/resource/tree?menuId=' + data.id, {}).then(data => {
        this.resourceData = data
      })
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      let authArray = []
      getListByFieldJudge(authArray, this.resourceData, 'children', 'checked', true)
      this.P('admin/resource/saveMenuAuth/' + this.form.id, getIds(authArray)).then(data => {
        this.$Message.success('授权完成')
        this.closeModel()
      })
    }
  },
  mounted: function () {
  }
}
</script>
