<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">角色表</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="roleForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="角色名称" prop="name">
              <Input v-model="form.name" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="上级角色" prop="pid">
              <Treeselect v-model="form.pid" :options="roleOptions" :show-count="true" :normalizer="normalizer"
                          :disabled="disable"/>
            </FormItem>
          </Col>
          <Col span="8">
          <FormItem label="租户" prop="tenantId">
            <Select v-model="form.tenantId" >
              <Option :value="item.tenantId" v-for="(item, index) in tenantOptions" :key="index">{{item.name}}</Option>
            </Select>
          </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="备注" prop="tips">
              <Input v-model="form.tips" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="微信ID">
              <Input v-model="form.wxid" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="钉钉ID">
              <Input v-model="form.ddid" :disabled="disable"></Input>
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

  import { C, U, P } from '@/libs/api.request'
  import { getTenantId } from '@/api/user'
  import Treeselect from '@riophae/vue-treeselect'
  import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  name: 'role-info',
  components: {
    Treeselect
  },
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      tenantOptions: [],
      roleOptions: [],
      ruleValidate: {
        name: [
          { required: true, message: '角色名称不允许为空', trigger: 'blur' }
        ],
        tips: [
          { required: true, message: '备注不允许为空', trigger: 'blur' }
        ],
        pid: [
          { required: true, message: '上级角色不允许为空', trigger: 'blur' }
        ],
        tenantId: [
          { required: true, message: '租户不允许为空', trigger: 'blur' }
        ]
      },
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
      if (type !== 'create') {
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
      this.$refs.roleForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('admin/role', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              U('admin/role', this.form).then(data => {
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
    P('admin/role/tree', {model: { tenantId: getTenantId() }}).then(data => {
      this.roleOptions = data
    })
    P('admin/tenant/list', {pageSize: 1000}).then(data => {
      this.tenantOptions = data.rows
    })

  }
}
</script>
