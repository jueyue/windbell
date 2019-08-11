<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">账户流水</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="accountflowForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="ID" prop="id">
              <Input v-model="form.id" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="账户" prop="accountNo">
              <Input v-model="form.accountNo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="订单号" prop="tradeNo">
              <Input v-model="form.tradeNo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="三方交易时间" prop="thirdTradeTime">
              <Input v-model="form.thirdTradeTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="交易时间" prop="tradeTime">
              <Input v-model="form.tradeTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="科目" prop="subject">
              <Input v-model="form.subject" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="关联订单号" prop="outerTradeNo">
              <Input v-model="form.outerTradeNo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="备注" prop="memo">
              <Input v-model="form.memo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="币种" prop="currency">
              <Input v-model="form.currency" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="金额" prop="money">
              <Input v-model="form.money" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="手续费" prop="fee">
              <Input v-model="form.fee" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="到账金额" prop="realMoney">
              <Input v-model="form.realMoney" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="交易信息" prop="tradeInfo">
              <Input v-model="form.tradeInfo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="完成时间" prop="completeTime">
              <Input v-model="form.completeTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="更新时间" prop="updateTime">
              <Input v-model="form.updateTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="状态" prop="status">
              <Input v-model="form.status" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="当前余额" prop="balance">
              <Input v-model="form.balance" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="自定义类型" prop="type">
              <Input v-model="form.type" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="BeforeBalance" prop="beforeBalance">
              <Input v-model="form.beforeBalance" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="订单唯一值" prop="unique">
              <Input v-model="form.unique" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="Key" prop="key">
              <Input v-model="form.key" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="Key" prop="key">
              <Input v-model="form.key" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="Key" prop="key">
              <Input v-model="form.key" :disabled="disable"></Input>
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
  name: 'accountflow-info',
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
        thirdTradeTime: [
          {required: true, message: '三方交易时间不允许为空', trigger: 'blur'}
        ],
        tradeTime: [
          {required: true, message: '交易时间不允许为空', trigger: 'blur'}
        ],
        subject: [
          {required: true, message: '科目不允许为空', trigger: 'blur'}
        ],
        outerTradeNo: [
          {required: true, message: '关联订单号不允许为空', trigger: 'blur'}
        ],
        memo: [
          {required: true, message: '备注不允许为空', trigger: 'blur'}
        ],
        currency: [
          {required: true, message: '币种不允许为空', trigger: 'blur'}
        ],
        money: [
          {required: true, message: '金额不允许为空', trigger: 'blur'}
        ],
        fee: [
          {required: true, message: '手续费不允许为空', trigger: 'blur'}
        ],
        realMoney: [
          {required: true, message: '到账金额不允许为空', trigger: 'blur'}
        ],
        tradeInfo: [
          {required: true, message: '交易信息不允许为空', trigger: 'blur'}
        ],
        completeTime: [
          {required: true, message: '完成时间不允许为空', trigger: 'blur'}
        ],
        updateTime: [
          {required: true, message: '更新时间不允许为空', trigger: 'blur'}
        ],
        balance: [
          {required: true, message: '当前余额不允许为空', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '自定义类型不允许为空', trigger: 'blur'}
        ],
        beforeBalance: [
          {required: true, message: 'BeforeBalance不允许为空', trigger: 'blur'}
        ],
        unique: [
          {required: true, message: '订单唯一值不允许为空', trigger: 'blur'}
        ],
        key: [
          {required: true, message: 'Key不允许为空', trigger: 'blur'}
        ],
        key: [
          {required: true, message: 'Key不允许为空', trigger: 'blur'}
        ],
        key: [
          {required: true, message: 'Key不允许为空', trigger: 'blur'}
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
      this.$refs.accountflowForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('accountflow', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('accountflow', this.form).then(data => {
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
  }
}
</script>
