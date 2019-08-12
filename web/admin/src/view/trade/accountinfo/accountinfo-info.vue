<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">账户基本信息</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="accountinfoForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="账户" prop="accountNo">
              <Input v-model="form.accountNo" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="账户名称" prop="name">
              <Input v-model="form.name" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="应用" prop="appid">
              <Input v-model="form.appid" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="类型" prop="type">
              <Input v-model="form.type" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="允许充值" prop="isRecharge">
              <Input v-model="form.isRecharge" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="允许提现" prop="isCash">
              <Input v-model="form.isCash" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="允许为负" prop="isBalanceNegative">
              <Input v-model="form.isBalanceNegative" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="用户名称" prop="userName">
              <Input v-model="form.userName" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="用户手机" prop="userPhone">
              <Input v-model="form.userPhone" :disabled="disable"></Input>
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
  name: 'accountinfo-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        name: [
          {required: true, message: '账户名称不允许为空', trigger: 'blur'}
        ],
        appid: [
          {required: true, message: '应用不允许为空', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '类型不允许为空', trigger: 'blur', type: 'number'}
        ],
        isRecharge: [
          {required: true, message: '是否允许充值不允许为空', trigger: 'blur', type: 'number'}
        ],
        isCash: [
          {required: true, message: '是否允许提现不允许为空', trigger: 'blur', type: 'number'}
        ],
        isBalanceNegative: [
          {required: true, message: '是否允许余额为负不允许为空', trigger: 'blur', type: 'number'}
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
      this.$refs.accountinfoForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('trade/accountinfo', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('trade/accountinfo', this.form).then(data => {
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
