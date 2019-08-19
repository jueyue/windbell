<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">通知模板</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="noticeTemplateForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
            <FormItem label="模板名称" prop="name">
              <Input v-model="form.name" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="模板编码" prop="code">
              <Input v-model="form.code" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="信息类型" prop="msgType">
              <Input v-model="form.msgType" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="营销短信" prop="isMarketingMsg">
              <Input v-model="form.isMarketingMsg" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="模板内容" prop="content">
              <Input v-model="form.content" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="链接" prop="link">
              <Input v-model="form.link" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="指定渠道" prop="channelCode">
              <Input v-model="form.channelCode" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="业务子通道" prop="extSubCode">
              <Input v-model="form.extSubCode" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="状态" prop="status">
              <Input v-model="form.status" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="三方模板" prop="thirdTemplateCode">
              <Input v-model="form.thirdTemplateCode" :disabled="disable"></Input>
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
  name: 'noticetemplate-info',
  data () {
    return {
      form: {},
      isShow: false,
      type: 'add',
      disable: false,
      ruleValidate: {
        name: [
          {required: true, message: '名称不允许为空', trigger: 'blur'}
        ],
        code: [
          {required: true, message: 'Code不允许为空', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '内容不允许为空', trigger: 'blur'}
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
      this.$refs.noticeTemplateForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              this.C('/paas/notice/template', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              this.U('/paas/notice/template', this.form).then(data => {
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
