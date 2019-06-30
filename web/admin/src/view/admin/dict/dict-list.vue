<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="Key">
            <Input v-model="form.key" placeholder="sex"></Input>
          </FormItem>
          <FormItem label="名称">
            <Input v-model="form.name" placeholder="性别"></Input>
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
    <dictInfo ref="dictInfoRef"  @handle-search="handleSearch"></dictInfo>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { L, D } from '@/libs/api.request'
import dictInfo from './dict-info'
import { getIds } from '@/libs/util'

export default {
  name: 'tables_page',
  components: {
    Tables,
    dictInfo
  },
  data () {
    return {
      columns: [
        {
          type: 'expand',
          width: 50,
          render: (h, params) => {
            return h('div', [h('Row', params.row.subDict.map(function (item, index) {
              return [h('Col', { attrs: {
                span: 2
              } },
              index + 1), h('Col', { attrs: {
                span: 6
              } },
              'Key:  ' + item.key), h('Col', { attrs: {
                span: 6
              } },
              '名称:  ' + item.name), h('Col', { attrs: {
                span: 10
              } },
              '备注:' + item.tips)]
            }))])
          }
        },
        { title: '', key: 'id', type: 'selection', width: 60 },
        { title: 'Key', key: 'key' },
        { title: '名称', key: 'name' },
        { title: '创建时间', key: 'crtTime', sortType: 'desc' },
        { title: '修改时间', key: 'mdfTime' },
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
      infoIsShow: false,
      subDict: []
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.dictInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.dictInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.dictInfoRef.openModel('create')
    },
    handleDelete () {
      D('admin/dict', getIds(this.selectedData)).then(data => {
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
      L('admin/dict', param).then(data => {
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
