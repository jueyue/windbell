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
            <FormItem label="余额" prop="balance">
              <Input v-model="form.balance" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="不可提现余额" prop="frozenBalance">
              <Input v-model="form.frozenBalance" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="不可提现余额校验值" prop="frozenBalCheck">
              <Input v-model="form.frozenBalCheck" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="币种" prop="currency">
              <Input v-model="form.currency" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="开户时间" prop="openTime">
              <Input v-model="form.openTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="是否允许充值" prop="isRecharge">
              <Input v-model="form.isRecharge" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="是否允许提现" prop="isCash">
              <Input v-model="form.isCash" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="是否允许余额为负" prop="isBalanceNegative">
              <Input v-model="form.isBalanceNegative" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="用户名称" prop="userName">
              <Input v-model="form.userName" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="余额校验值" prop="balCheck">
              <Input v-model="form.balCheck" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="用户手机" prop="userPhone">
              <Input v-model="form.userPhone" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="创建时间" prop="crtTime">
              <Input v-model="form.crtTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="创建用户Id" prop="crtUserId">
              <Input v-model="form.crtUserId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="修改时间" prop="mdfTime">
              <Input v-model="form.mdfTime" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="修改用户" prop="mdfUserId">
              <Input v-model="form.mdfUserId" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="Unique" prop="unique">
              <Input v-model="form.unique" :disabled="disable"></Input>
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

export default {
  name: 'accountinfo-info',
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
        name: [
          {required: true, message: '账户名称不允许为空', trigger: 'blur'}
        ],
        appid: [
          {required: true, message: '应用不允许为空', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '类型不允许为空', trigger: 'blur'}
        ],
        balance: [
          {required: true, message: '余额不允许为空', trigger: 'blur'}
        ],
        frozenBalance: [
          {required: true, message: '不可提现余额不允许为空', trigger: 'blur'}
        ],
        frozenBalCheck: [
          {required: true, message: '不可提现余额校验值不允许为空', trigger: 'blur'}
        ],
        currency: [
          {required: true, message: '币种不允许为空', trigger: 'blur'}
        ],
        openTime: [
          {required: true, message: '开户时间不允许为空', trigger: 'blur'}
        ],
        isRecharge: [
          {required: true, message: '是否允许充值不允许为空', trigger: 'blur'}
        ],
        isCash: [
          {required: true, message: '是否允许提现不允许为空', trigger: 'blur'}
        ],
        isBalanceNegative: [
          {required: true, message: '是否允许余额为负不允许为空', trigger: 'blur'}
        ],
        userName: [
          {required: true, message: '用户名称不允许为空', trigger: 'blur'}
        ],
        balCheck: [
          {required: true, message: '余额校验值不允许为空', trigger: 'blur'}
        ],
        userPhone: [
          {required: true, message: '用户手机不允许为空', trigger: 'blur'}
        ],
        crtTime: [
          {required: true, message: '创建时间不允许为空', trigger: 'blur'}
        ],
        crtUserId: [
          {required: true, message: '创建用户Id不允许为空', trigger: 'blur'}
        ],
        mdfTime: [
          {required: true, message: '修改时间不允许为空', trigger: 'blur'}
        ],
        mdfUserId: [
          {required: true, message: '修改用户不允许为空', trigger: 'blur'}
        ],
        unique: [
          {required: true, message: 'Unique不允许为空', trigger: 'blur'}
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
      this.$refs.accountinfoForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('accountinfo', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('accountinfo', this.form).then(data => {
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
