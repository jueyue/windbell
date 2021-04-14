<template>
  <Modal v-model="isShow" width="65%" :closable="false" :mask-closable="false">
    <div slot="header" class="info-header">
      <div class="ivu-modal-header-inner">菜单管理</div>
      <a class="ivu-modal-close" @click="closeModel"><i class="ivu-icon ivu-icon-ios-close"></i></a>
    </div>
    <div>
      <Form ref="menuForm" :model="form" :label-width="80" :rules="ruleValidate">
        <Row>
          <Col span="8">
              <FormItem label="前端" prop="webType">
              <Select v-model="form.webType" :disabled="disable">
                <Option :value="item.key" v-for="(item, index) in webTypeOptions" :key="index">{{item.name}}</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="权限" prop="code">
              <Input v-model="form.code" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="父菜单" prop="pidName">
              <Treeselect v-model="form.pid" :options="menuOptions" :show-count="true" :normalizer="normalizer"/>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="名称" prop="name">
              <Input v-model="form.name" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="地址" prop="url">
              <Input v-model="form.url" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="图标" prop="icon">
              <Input v-model="form.icon" :disabled="disable" @on-focus="selectIcon" :prefix="form.icon"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="排序号" prop="num">
              <Input v-model="form.num" :disabled="disable" type="number"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="菜单" prop="ismenu">
              <Dict dict="menu-type" v-model="form.ismenu" :disabled="disable"></Dict>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="备注" prop="tips">
              <Input v-model="form.tips" :disabled="disable"></Input>
            </FormItem>
          </Col>
          <Col span="8">
            <FormItem label="归属产品" prop="proCode">
              <Select v-model="form.proCode" :disabled="disable" filterable>
                <Option :value="item.proCode" v-for="(item, index) in products" :key="index">{{item.proName}}</Option>
              </Select>
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
            <FormItem label="打开" prop="isopen">
              <Select v-model="form.isopen" :disabled="disable">
                <Option :value="item.key" v-for="(item, index) in yesNoOptions" :key="index">{{item.name}}</Option>
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
    <IconSelect ref="iconSelect" @on-select="iconSelected"></IconSelect>
  </Modal>
</template>

<script>

import { C, U, P } from '@/libs/api.request'
import { dict } from '@/libs/common.request'

import Treeselect from '@riophae/vue-treeselect'
import IconSelect from '@/components/icon-select/icon-select'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'menu-info',
  components: {
    Treeselect,
    IconSelect
  },
  data () {
    return {
      form: {
        icon: ''
      },
      isShow: false,
      type: 'add',
      disable: false,
      webTypeOptions: [],
      statusOptions: [],
      yesNoOptions: [],
      menuType: [],
      menuOptions: [],
      products: [],
      normalizer (node) {
        return {
          id: node.id,
          label: node.name
        }
      },
      ruleValidate: {
        webType: [
          { required: true, message: '显示类型不允许为空', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '编号不允许为空', trigger: 'blur' }
        ],
        pid: [
          { required: true, message: '父编号不允许为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '名称不允许为空', trigger: 'blur' }
        ],
        url: [
          { required: true, message: 'URL地址不允许为空', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '排序号不允许为空', trigger: 'blur', type: 'number' }
        ],
        ismenu: [
          { required: true, message: '是否是不允许为空', trigger: 'blur' }
        ],
        proCode: [
          { required: true, message: '归属产品不允许为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '状态不允许为空', trigger: 'blur' }
        ],
        isopen: [
          { required: true, message: '是否打开不允许为空', trigger: 'blur' }
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
        this.form.webType = this.form.webType + ''
      } else {
        this.form = {}
      }
      this.disable = type === 'detail'
      // 需要同步更新
      P('admin/menu/treeList', {}).then(data => {
        this.menuOptions = data
      })
      P('admin/product/list', {'pageSize': 100}).then(data => {
        this.products = data.rows
      })
    },
    closeModel () {
      this.isShow = false
    },
    submit () {
      this.$refs.menuForm.validate((valid) => {
        if (valid) {
          switch (this.type) {
            case 'create':
              C('admin/menu', this.form).then(data => {
                this.isShow = false
                this.$emit('handle-search')
              })
              break
            case 'update':
              U('admin/menu', this.form).then(data => {
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
    selectIcon () {
      this.$refs.iconSelect.select()
    },
    iconSelected (icon) {
      // this.form['icon'] = icon
      this.$set(this.form, 'icon', icon)
    }
  },
  mounted: function () {
    dict('yes_no').then(data => {
      this.yesNoOptions = data
    })
    dict('status').then(data => {
      this.statusOptions = data
    })
    dict('web_type').then(data => {
      this.webTypeOptions = data
    })
    dict('menu-type').then(data => {
      this.menuType = data
    })
  }
}
</script>
