<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">三方平台APP信息</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="appInfoConfigFrom" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="租户" prop="tenantId">
              <Select v-model="form.tenantId" filterable>
                <Option :value="item.tenantId" v-for="(item, index) in tenantList" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="类型" prop="type">
              <Select v-model="form.type" :disabled="disable">
                <Option :value="item.key" v-for="(item, index) in typeOptions" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="AppCode" prop="appCode">
              <Input v-model="form.appCode" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="功能" prop="appCode">
              <Input v-model="form.appName" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="AppId" prop="appId">
              <Input v-model="form.appId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="APP秘钥" prop="secret">
              <Input v-model="form.secret" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="子AppId" prop="subAppId">
              <Input v-model="form.subAppId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="内部子AppId" prop="subAppCode">
              <Input v-model="form.subAppCode" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="子秘钥" prop="subAppSecret">
              <Input v-model="form.subAppSecret" :disabled="disable"></Input>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </div>
    <div slot="footer">
      <Button @click="closeModel">取消</Button>
      <Button type="primary" @click="submit"  v-show="!disable">提交</Button>
    </div>
  </Modal>
</template>

<script>

export default {
  name: 'appinfoconfig-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      tenantList: [],
      typeOptions: [],
      ruleValidate: {
        tenantId: [
          { required: true, message: '租户ID不允许为空' }
        ],
        type: [
          { required: true, message: '类型不允许为空' }
        ],
        appCode: [
          { required: true, message: 'AppCode不允许为空' }
        ],
        appId: [
          { required: true, message: 'AppId不允许为空' }
        ],
        secret: [
          { required: true, message: 'APP秘钥不允许为空' }
        ]
      }
    }
  },
  methods: {
    openModel (type, data) {
      this.isShow = true
      this.type = type
      if (data) {
        this.form = data
      } else {
        this.form = {}
      }
      this.disable = type === 'detail'
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.$refs.appInfoConfigFrom.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('paas/appinfoconfig', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('paas/appinfoconfig', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
          }
        } else {
          this.$Message.error('请检查填写的数据')
        }
      })
    }
  },
  mounted: function () {
    this.L('admin/tenant', {pageSize: 1000}).then(data => {
      this.tenantList = data.rows
    })
    this.dict('paas_type').then(data => {
      this.typeOptions = data
    })
  }
}
</script>
