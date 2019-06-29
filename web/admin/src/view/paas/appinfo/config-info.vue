<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">三方平台APP信息</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="appinfoconfigForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="租户ID" prop="tenantId">
              <Input v-model="form.tenantId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="类型" prop="type">
              <Input v-model="form.type" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="内部AppID" prop="appCode">
              <Input v-model="form.appCode" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="外部AppId" prop="appId">
              <Input v-model="form.appId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="APP秘钥" prop="sercet">
              <Input v-model="form.sercet" :disabled="disable"></Input>
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
          <Col span="8">
            <FormItem label="创建用户Id" prop="crtUserId">
              <Input v-model="form.crtUserId" :disabled="disable"></Input>
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

import {C, U} from '@/libs/api.request'

export default {
  name: 'appinfoconfig-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        tenantId: [
          {required: true, message: '租户ID不允许为空', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '类型不允许为空', trigger: 'blur'}
        ],
        appCode: [
          {required: true, message: '内部appID不允许为空', trigger: 'blur'}
        ],
        appId: [
          {required: true, message: '外部PAPID不允许为空', trigger: 'blur'}
        ],
        sercet: [
          {required: true, message: 'APP秘钥不允许为空', trigger: 'blur'}
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
      this.$refs.appinfoconfigForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('appinfoconfig', this.form).then(data => {
                this.isShow = false
                this.$emit('handleSearch')
              })
              break
            case 'update':
              U('appinfoconfig', this.form).then(data => {
                this.isShow = false
                this.$emit('handleSearch')
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
  }
}
</script>

<style scoped>
</style>
