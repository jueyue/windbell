<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">资源管理</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="resourceForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="资源父编号" prop="pid">
              <Select v-model="form.pid">
                <Option value="" selected>无</Option>
                <Option :value='item.id' v-for="(item, index) in parentList" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="资源名称" prop="name">
              <Input v-model="form.name" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="URL地址" prop="url">
              <Input v-model="form.url" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="子系统" prop="subSystem">
              <Input v-model="form.subSystem" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="排序号" prop="num">
              <Input v-model="form.num" :disabled="disable" type="number"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="类型" prop="type">
              <Select v-model="form.type">
                <Option :value='item.key' v-for="(item, index) in typeOptions" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="备注" prop="tips">
              <Input v-model="form.tips" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="状态" prop="status">
              <Select v-model="form.status">
                <Option :value='item.key' v-for='(item, index) in statusOptions' :key='index'>{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="基础资源" prop="status">
              <Select v-model="form.base">
                <Option :value='item.key' v-for='(item, index) in yesNoOptions' :key='index'>{{item.name}}</Option>
              </Select>
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
  name: 'resource-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      statusOptions: [],
      typeOptions: [],
      yesNoOptions: [],
      parentList: [],
      ruleValidate: {
        name: [
          {required: true, message: '资源名称不允许为空', trigger: 'blur'}
        ],
        url: [
          {required: true, message: 'URL地址不允许为空', trigger: 'blur'}
        ],
        subSystem: [
          {required: true, message: '子系统不允许为空', trigger: 'blur'}
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
        this.form.base = 2
      } else {
        this.form = {
          base: 2
        }
      }
      this.disable = type === 'detail'
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.$refs.resourceForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('admin/resource', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('admin/resource', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
          }
        } else {
          this.$Message.error('请检查填写的数据')
        }
      })
    },
    loadParentResource () {
      this.L('admin/resource', {map: {parentIsNull: true}, pageSize: this.PAGE_SIZE_MAX}).then(data => {
        this.parentList = data.rows
      })
    }
  },
  mounted: function () {
    this.numDict('status').then(data => {
      this.statusOptions = data
    })
    this.numDict('resource_type').then(data => {
      this.typeOptions = data
    })
    this.numDict('yes_no').then(data => {
      this.yesNoOptions = data
    })
    this.loadParentResource()
  }
}
</script>
