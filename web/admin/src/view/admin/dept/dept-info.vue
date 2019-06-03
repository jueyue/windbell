<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">部门管理</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="deptForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="12">
            <FormItem label="全称" prop="fullName">
              <Input v-model="form.fullName" placeholder=""></Input>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="简称" prop="simpleName">
              <Input v-model="form.simpleName" placeholder=""></Input>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="上级" prop="pidName">
              <Treeselect v-model="form.pid" :options="deptOptions" :show-count="true" :normalizer="normalizer"/>
            </FormItem>
          </Col>
          <Col span="12">
            <FormItem label="备注">
              <Input v-model="form.tips"></Input>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </div>
    <div slot="footer">
      <Button @click="closeModel">取消</Button>
      <Button type="primary" @click="submit" :disabled="submitIsDisabled">提交</Button>
    </div>
  </Modal>
</template>

<script>

import { C, U, P } from '@/libs/api.request'
import { getTenantId } from '@/api/user'

import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'dept-info',
  components: {
    Treeselect
  },
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      ruleValidate: {
        fullName: [
          { required: true, message: '全称不允许为空', trigger: 'blur' }
        ],
        simpleName: [
          { required: true, message: '简称不允许为空', trigger: 'blur' }
        ],
        pid: [
          { required: true, message: '上级不允许为空', trigger: 'blur' }
        ]
      },
      submitIsDisabled: false,
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
      this.submitIsDisabled = false
      this.type = type
      if (type !== 'create') {
        this.form = data
      } else {
        this.form = {}
      }
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.submitIsDisabled = true
      this.$refs.deptForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('admin/dept', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              U('admin/dept', this.form).then(data => {
                this.$emit('handle-search')
                this.isShow = false
              })
              break
          }
        } else {
          this.$Message.error('请检查填写的数据')
          this.submitIsDisabled = false
        }
      })
    }
  },
  mounted: function () {
    P('admin/dept/tree', { model: { tenantId: getTenantId() } }).then(data => {
      this.deptOptions = data
    })
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
