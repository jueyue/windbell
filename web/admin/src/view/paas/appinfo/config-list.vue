<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="类型">
            <Select v-model="form.type">
              <Option :value="item.key" v-for="(item, index) in typeOptions" :key="index">{{item.name}}</Option>
            </Select>
          </FormItem>
          <FormItem label="功能">
            <Input v-model="form.appName"></Input>
          </FormItem>
          <FormItem label="AppCode">
            <Input v-model="form.appCode"></Input>
          </FormItem>
          <FormItem label="AppId">
            <Input v-model="form.appId"></Input>
          </FormItem>
        </Form>
        <div  class="toolbar">
          <Button type="primary" icon="md-add" @click="handleCreate">新增</Button>
          <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
          <Button @click="handleSearch" icon="md-search" class="search-btn" type="primary">搜索 </Button>
        </div>
      </div>
      <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
              @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
    </Card>
    <appinfoconfigInfo ref="appinfoconfigInfoRef"  @handle-search="handleSearch"></appinfoconfigInfo>
  </div>
</template>

<script>
import Tables from '_c/tables'
import appinfoconfigInfo from './config-info'
import {getIds} from '@/libs/util'

export default {
  name: 'appinfoconfig-list',
  components: {
    Tables,
    appinfoconfigInfo
  },
  data () {
    return {
      columns: [
        {title: '类型',
          key: 'type',
          render: (h, params) => {
            return h('span', this.getDictVal('paas_type', params.row.type))
          }},
        {title: 'appCode', key: 'appCode'},
        {title: '功能', key: 'appName'},
        {title: 'appId', key: 'appId'},
        {title: 'APP秘钥', key: 'sercet'},
        {title: '子AppId', key: 'subAppId'},
        {title: '子AppCode', key: 'subAppCode'},
        {title: '子秘钥', key: 'subAppSecret'},
        {title: '创建时间', key: 'crtTime'},
        {title: '修改人', key: 'mdfUserId'},
        {title: '修改时间', key: 'mdfTime'},
        {
          title: '操作',
          key: 'handle',
          minWidth: 200,
          options: ['update', 'detail']
        }
      ],
      tableData: {
        rows: [],
        total: 0
      },
      form: {
        map: {}
      },
      selectedData: [],
      infoIsShow: false
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.appinfoconfigInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.appinfoconfigInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.appinfoconfigInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('paas/appinfoconfig', getIds(this.selectedData)).then(data => {
        this.$Message.success(data)
        this.handleSearch()
      })
    },
    selectionChange (selection) {
      this.selectedData = selection
    },
    handleSearch (page, pageSize) {
      if (isNaN(page)) {
        page = 1
      }
      var param = {
        page: page,
        pageSize: pageSize,
        model: this.form,
        map: this.form.map
      }
      this.L('paas/appinfoconfig', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.dict('paas_type').then(data => {
      this.typeOptions = data
    })
    this.handleSearch()
  }
}
</script>
