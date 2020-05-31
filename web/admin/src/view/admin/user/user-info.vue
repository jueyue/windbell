<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">用户管理</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="userForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="账户" prop="account">
              <Input v-model="form.account" placeholder="13112345678" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="用户">
              <Input v-model="form.name" placeholder="张三" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="角色" prop="roles">
              <Select v-model="roles" multiple>
                <Option :value="item.id" v-for="(item, index) in roleList" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="生日">
              <DatePicker type="date" placeholder="1990-01-01" v-model="form.birthday"
                          :disabled="disable"></DatePicker>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="性别">
              <Select v-model="form.sex" :disabled="disable">
                <Option value="1">男</Option>
                <Option value="2">女</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="邮件">
              <Input v-model="form.email" placeholder="999@qq.com" type="email" :disabled="disable"></Input>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="8">
            <FormItem label="电话"  prop="phone">
              <Input v-model="form.phone" placeholder="13112345678" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="部门" prop="deptId">
              <Treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" :normalizer="normalizer"
                          :disabled="disable"/>
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

import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'user-info',
  components: {
    Treeselect
  },
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      passwordDisable: false,
      ruleValidate: {
        account: [
          { required: true, message: '账户不允许为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不允许为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '手机号不允许为空', trigger: 'blur' }
        ]
      },
      roles: [],
      roleList: [],
      deptOptions: [],
      normalizer (node) {
        return {
          id: node.id,
          label: node.fullName
        }
      }
    }
  },
  methods: {
    openModel (type, data) {
      this.isShow = true
      this.type = type
      if (data) {
        this.form = data
        this.form.sex = this.form.sex + ''
      } else {
        this.form = {}
      }
      this.disable = type === 'detail'
      this.passwordDisable = type !== 'detail'
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.$refs.userForm.validate((valid) => {
        if (valid) {
          this.form.access = JSON.stringify(this.roles)
          switch (this.type) {
            case 'create':
              this.C('admin/user', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('admin/user', this.form).then(data => {
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
  beforeCreate: function () {
    this.P('admin/dept/tree').then(data => {
      this.deptOptions = data
    })
    this.P('admin/role/list').then(data => {
      this.roleList = data.rows
    })
  }
}
</script>
