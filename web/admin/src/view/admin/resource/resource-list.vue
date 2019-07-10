<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="资源名称">
                        <Input v-model="form.name"></Input>
                    </FormItem>
                    <FormItem label="URL地址">
                        <Input v-model="form.url"></Input>
                    </FormItem>
                    <FormItem label="类型">
                        <Input v-model="form.type"></Input>
                    </FormItem>
                    <FormItem label="备注">
                        <Input v-model="form.tips"></Input>
                    </FormItem>
                    <FormItem label="状态">
                        <Input v-model="form.status"></Input>
                    </FormItem>
                </Form>
                <div class="toolbar">
                    <Button type="primary" icon="md-add" @click="handleCreate">新增</Button>
                    <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
                    <Button @click="handleSearch" class="search-btn" icon="md-search" type="primary">搜索</Button>
                </div>
            </div>
            <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
                    @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
        </Card>
        <resourceInfo ref="resourceInfoRef"  @handle-search="handleSearch"></resourceInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import resourceInfo from './resource-info'
import {getIds} from '@/libs/util'

export default {
  name: 'resource-list',
  components: {
    Tables,
    resourceInfo
  },
  data () {
    return {
      columns: [
        { title: '', key: 'id', type: 'selection', width: 60 },
        {title: '资源名称', key: 'name'},
        {title: 'URL地址', key: 'url'},
        {title: '排序号', key: 'num'},
        {title: '类型',
          key: 'type',
          render: (h, params) => {
            return h('span', this.getDictVal('resource_type', params.row.type))
          } },
        {title: '状态',
          key: 'status',
          render: (h, params) => {
            return h('span', this.getDictVal('status', params.row.status))
          } },
        {title: '创建时间', key: 'crtTime'},
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
      this.$refs.resourceInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.resourceInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.resourceInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('admin/resource', getIds(this.selectedData)).then(data => {
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
      this.L('admin/resource', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
  }
}
</script>
