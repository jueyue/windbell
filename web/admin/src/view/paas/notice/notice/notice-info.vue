<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">通知表</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="noticeForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="类型" prop="type">
              <Select v-model="form.type">
                <Option :value="item.key" v-for="(item, index) in typeOptions" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="地址列表（泛化，邮件，电话）" prop="address">
              <Input v-model="form.address" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="模板" prop="templateId">
              <Select v-model="form.templateId" @on-change="templateChange" filterable>
                <Option :value="item.id" v-for="(item, index) in templateList" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="24">
            <FormItem label="模板内容" prop="content" style="width: 90%">
              <Input v-model="content" type="textarea" :autosize="{minRows: 4,maxRows: 10}"  disabled="ture"></Input>
            </FormItem>
          </Col>
          <Col span="24" v-for="k in keys" :key="k">
            <FormItem :label="k">
              <Input :name="k" @on-change="keyChange($event)"></Input>
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
  name: 'notice-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        type: [
          { required: true, message: '类型不允许为空' }
        ],
        address: [
          { required: true, message: '地址列表（泛化，邮件，电话）不允许为空' }
        ]
      },
      content: '',
      typeOptions: [],
      templateList: [],
      keys: [],
      keysVal: []
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
      this.content = ''
      this.disable = type === 'detail'
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.form.tenantId = this.getTenantId()
      this.$refs.noticeForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('/paas/notice', this.form).then(data => {
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
    templateChange () {
      if (!this.form.templateId) {
        return
      }
      this.P('/paas/notice/template/getParseDetail/' + this.form.templateId).then(data => {
        this.content = data.content
        this.keys = data.keys
      })
    },
    keyChange (e) {
      e.target.name = e.data
    }
  },
  mounted: function () {
    this.dict('msg_type').then(data => {
      this.typeOptions = data
    })
    this.P('/paas/notice/template/allList').then(data => {
      this.templateList = data
    })
  }
}
</script>
