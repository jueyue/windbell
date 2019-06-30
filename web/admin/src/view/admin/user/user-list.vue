<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="账户">
            <Input v-model="form.account" placeholder=""></Input>
          </FormItem>
          <FormItem label="用户">
            <Input v-model="form.name" placeholder="张三"></Input>
          </FormItem>
          <FormItem label="电话">
            <Input v-model="form.phone" placeholder="13112345678"></Input>
          </FormItem>
          <FormItem label="部门">
            <Input v-model="form.phone" placeholder="13112345678"></Input>
          </FormItem>
          <FormItem label="创建时间">
            <Row>
              <Col span="11">
                <DatePicker type="date" placeholder="开始" v-model="form.map.crtTime_st"></DatePicker>
              </Col>
              <Col span="2" style="text-align: center">-</Col>
              <Col span="11">
                <DatePicker type="date" placeholder="结束" v-model="form.map.crtTime_ed"></DatePicker>
              </Col>
            </Row>
          </FormItem>
          <Button @click="handleSearch" class="search-btn" type="primary">
            <Icon type="search"/>
            搜索
          </Button>
        </Form>
        <div class="toolbar">
          <Button type="primary" icon="md-add" @click="handleCreate">新增</Button> &nbsp;&nbsp;
          <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
        </div>
      </div>
      <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
              @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
    </Card>
    <userInfo ref="userInfoRef" @handle-search="handleSearch"></userInfo>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { L, D } from '@/libs/api.request'
import userInfo from './user-info'
import { getIds } from '@/libs/util'
import { getDictVal } from '@/libs/common.request'

export default {
  name: 'tables_page',
  components: {
    Tables,
    userInfo
  },
  data () {
    return {
      columns: [
        { title: '', key: 'id', type: 'selection', width: 60 },
        { title: '账户', key: 'account' },
        { title: '名称', key: 'name' },
        { title: '手机', key: 'phone' },
        { title: '部门', key: 'deptId' },
        { title: '客户', key: 'tenantId' },
        { title: '管理员',
          key: 'isAdmin',
          render: (h, params) => {
            return h('span', getDictVal('yes_no', params.row.isAdmin))
          } },
        { title: '创建时间', key: 'crtTime', sortType: 'desc' },
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
      this.$refs.userInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.userInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.userInfoRef.openModel('create')
    },
    handleDelete () {
      D('admin/user', getIds(this.selectedData)).then(data => {
        this.$Message.success(data)
        this.handleSearch()
      })
    },
    selectionChange (selection) {
      this.selectedData = selection
    },
    exportExcel () {
      this.$refs.tables.exportCsv({
        filename: `table-${(new Date()).valueOf()}.csv`
      })
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
      L('admin/user', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
  }
}
</script>

<style>

</style>
