<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">产品配置</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="productForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="产品代码" prop="proCode">
              <Input v-model="form.proCode" :disabled="type !== 'create'"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="产品类型" prop="proType">
              <Select v-model="form.proType" :disabled="disable">
                <Option :value="item.key" v-for="(item, index) in proTypeOptions" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="产品名称" prop="proName">
              <Input v-model="form.proName" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="状态" prop="status">
              <Select v-model="form.status" :disabled="disable">
                <Option :value="item.key" v-for="(item, index) in statusOptions" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="备注" prop="memo">
              <Input v-model="form.memo" :disabled="disable"></Input>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </div>
    <div slot="footer">
      <Button @click="closeModel">取消</Button>
      <Button type="primary" @click="submit" v-show="!disable">提交</Button>
    </div>
  </Modal>
</template>

<script>

import { C, U } from '@/libs/api.request'
import { dict } from '@/libs/common.request'

export default {
  name: 'product-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        proCode: [
          { required: true, message: '产品代码不允许为空', trigger: 'blur' }
        ],
        proType: [
          { required: true, message: '产品类型不允许为空', trigger: 'blur' }
        ],
        proName: [
          { required: true, message: '产品名称不允许为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '状态不允许为空', trigger: 'blur' }
        ],
        memo: [
          { required: true, message: '备注不允许为空', trigger: 'blur' }
        ]
      },
      proTypeOptions: [],
      statusOptions: []
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
      this.$refs.productForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('admin/product', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              U('admin/product', this.form).then(data => {
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
    dict('product_type').then(data => {
      this.proTypeOptions = data
    })
    dict('product_status').then(data => {
      this.statusOptions = data
    })
  }
}
</script>
