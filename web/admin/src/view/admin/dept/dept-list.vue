<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="名称">
            <Input v-model="form.name" placeholder="众一"></Input>
          </FormItem>
          <Button @click="handleSearch" class="search-btn" type="primary">
            <Icon type="search"/>
            搜索
          </Button>
        </Form>
        <div>
          <Button type="primary" icon="md-add" @click="handleCreate">新增</Button> &nbsp;&nbsp;
        </div>
      </div>
      <zk-table  style="margin-top: 5px"
        :stripe="true"
        :selection-type="false"
        :expand-type="false"
        :data="tableData"
        :columns="columns">
        <template slot="options" slot-scope="scope">
          <div>
            <Button type="primary" icon="update" @click="handleUpdate(scope.row)">修改</Button> &nbsp;&nbsp;
            <Button type="primary" icon="android-detail" @click="handleDetail(scope.row)">详情</Button>
            &nbsp;&nbsp;
            <Button type="primary" icon="md-trash" @click="handleDelete(scope.row.id)">删除</Button>
          </div>
        </template>
      </zk-table>
    </Card>
    <deptInfo ref="deptInfoRef"  @handle-search="handleSearch"></deptInfo>
  </div>
</template>

<script>
import ZkTable from 'vue-table-with-tree-grid'
import { P, D } from '@/libs/api.request'
import deptInfo from './dept-info'

export default {
  name: 'dept-list',
  components: {
    ZkTable,
    deptInfo
  },
  data () {
    return {
      columns: [
        { label: '全称', prop: 'fullName' },
        { label: '简称', prop: 'simpleName' },
        { label: '微信ID', prop: 'wxid' },
        { label: '钉钉ID', prop: 'ddid' },
        { label: '创建时间',
          prop: 'crtTime',
          sortType: 'desc',
          minWidth: 100 },
        { label: '修改时间', prop: 'mdfTime' },
        {
          label: '操作',
          type: 'template',
          minWidth: 240,
          template: 'options'
        }
      ],
      tableData: [],
      form: {
        map: {}
      },
      selectedData: [],
      infoIsShow: false,
      subDict: []
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.deptInfoRef.openModel('update', param)
    },
    handleDetail (param) {
      this.$refs.deptInfoRef.openModel('detail', param)
    },
    handleCreate () {
      this.$refs.deptInfoRef.openModel('create')
    },
    handleDelete (id) {
      D('admin/dept', [id]).then(data => {
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
      P('admin/dept/tree', param).then(data => {
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
