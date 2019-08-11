<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">支付订单</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="payorderForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="ID" prop="id">
              <Input v-model="form.id" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="订单号" prop="orderid">
              <Input v-model="form.orderid" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="购买用户ID" prop="userId">
              <Input v-model="form.userId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="用户类型" prop="userType">
              <Input v-model="form.userType" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="商品描述" prop="goodsDesc">
              <Input v-model="form.goodsDesc" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="产品类型" prop="type">
              <Input v-model="form.type" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="订单名称" prop="subject">
              <Input v-model="form.subject" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="支付渠道" prop="channel">
              <Input v-model="form.channel" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="上游订单号" prop="upOrderId">
              <Input v-model="form.upOrderId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="上游渠道" prop="upChannel">
              <Input v-model="form.upChannel" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="购买者" prop="buyerId">
              <Input v-model="form.buyerId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="产品价格" prop="price">
              <Input v-model="form.price" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="状态1" prop="status">
              <Input v-model="form.status" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="Memo" prop="memo">
              <Input v-model="form.memo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="支付相关信息" prop="info">
              <Input v-model="form.info" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="支付时间" prop="payTime">
              <Input v-model="form.payTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="租户ID" prop="tenantId">
              <Input v-model="form.tenantId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="创建用户Id" prop="crtUserId">
              <Input v-model="form.crtUserId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="创建时间" prop="crtTime">
              <Input v-model="form.crtTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="修改人" prop="mdfUserId">
              <Input v-model="form.mdfUserId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="修改时间" prop="mdfTime">
              <Input v-model="form.mdfTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="Unique" prop="unique">
              <Input v-model="form.unique" :disabled="disable"></Input>
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

import {C, U, P} from '@/libs/api.request'

export default {
  name: 'payorder-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        id: [
          {required: true, message: 'ID不允许为空', trigger: 'blur'}
        ],
        userType: [
          {required: true, message: '用户类型不允许为空', trigger: 'blur'}
        ],
        subject: [
          {required: true, message: '订单名称不允许为空', trigger: 'blur'}
        ],
        upOrderId: [
          {required: true, message: '上游订单号不允许为空', trigger: 'blur'}
        ],
        upChannel: [
          {required: true, message: '上游渠道不允许为空', trigger: 'blur'}
        ],
        buyerId: [
          {required: true, message: '购买者不允许为空', trigger: 'blur'}
        ],
        memo: [
          {required: true, message: 'Memo不允许为空', trigger: 'blur'}
        ],
        info: [
          {required: true, message: '支付相关信息不允许为空', trigger: 'blur'}
        ],
        payTime: [
          {required: true, message: '支付时间不允许为空', trigger: 'blur'}
        ],
        tenantId: [
          {required: true, message: '租户ID不允许为空', trigger: 'blur'}
        ],
        crtUserId: [
          {required: true, message: '创建用户Id不允许为空', trigger: 'blur'}
        ],
        crtTime: [
          {required: true, message: '创建时间不允许为空', trigger: 'blur'}
        ],
        mdfUserId: [
          {required: true, message: '修改人不允许为空', trigger: 'blur'}
        ],
        mdfTime: [
          {required: true, message: '修改时间不允许为空', trigger: 'blur'}
        ],
        unique: [
          {required: true, message: 'Unique不允许为空', trigger: 'blur'}
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
      this.$refs.payorderForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('payorder', this.form).then(data => {
                this.isShow = false
                this.$emit('handleSearch')
              })
              break
            case 'update':
              U('payorder', this.form).then(data => {
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
  .info-header {
    height: 40px;
    color: #31708f;
    background-color: #d9edf7;
    border-color: #bce8f1;
  }

  .ivu-modal-header-inner {
    margin: 10px 15px 0px 0px;
    padding-left: 15px;
    height: 40px;
  }

  .ivu-modal-close {
    margin: 10px 15px 0px 0px;
  }

  .ivu-form-item {
    width: 260px;
  }
</style>
