<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="显示类型">
            <Select v-model="form.webType">
              <Option :value="Item.key" v-for="Item in webTypeOptions" :key="Item.key">{{Item.name}}</Option>
            </Select>
          </FormItem>
          <FormItem label="名称">
            <Input v-model="form.name"></Input>
          </FormItem>
          <FormItem label="归属产品">
            <Input v-model="form.proCode"></Input>
          </FormItem>
          <FormItem label="状态">
            <Select v-model="form.status">
              <Option :value="item.key" v-for="(item, index) in statusOptions" :key="index">{{item.name}}</Option>
            </Select>
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
            <Button type="primary" icon="ios-create-outline" @click="handleUpdate(scope.row)">修改</Button> &nbsp;&nbsp;
            &nbsp;&nbsp;
            <Button type="primary" icon="md-trash" @click="handleDelete(scope.row.id)">删除</Button>
          </div>
        </template>
        <template slot="webType" slot-scope="scope">
          {{ dictVal('menu_web_type', scope.row.webType) }}
        </template>
        <template slot="ismenu" slot-scope="scope">
          {{ dictVal('yes_no', scope.row.ismenu) }}
        </template>
        <template slot="isopen" slot-scope="scope">
          {{ dictVal('yes_no', scope.row.isopen) }}
        </template>
        <template slot="menuStatus" slot-scope="scope">
          {{ dictVal('status', scope.row.status) }}
        </template>
      </zk-table>
    </Card>
    <menuInfo ref="menuInfoRef" @handle-search="handleSearch"></menuInfo>
  </div>
</template>

<script>
import ZkTable from 'vue-table-with-tree-grid'
import { D, P } from '@/libs/api.request'
import { getDictVal, Dict } from '@/libs/common.request'
import menuInfo from './menu-info'

export default {
  name: 'menu-list',
  components: {
    ZkTable,
    menuInfo
  },
  data () {
    return {
      columns: [
        { label: '名称', prop: 'name' },
        { label: '编号', prop: 'code' },
        { label: '显示类型',
          prop: 'webType',
          type: 'template',
          template: 'webType'
        },
        { label: 'URL地址', prop: 'url' },
        { label: '排序号', prop: 'num' },
        { label: '归属产品', prop: 'proCode' },
        { label: '状态',
          prop: 'status',
          type: 'template',
          template: 'menuStatus' },
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
      webTypeOptions: [],
      statusOptions: [],
      infoIsShow: false
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.menuInfoRef.openModel('update', param)
    },
    handleDetail (param) {
      this.$refs.menuInfoRef.openModel('detail', param)
    },
    handleCreate () {
      this.$refs.menuInfoRef.openModel('create')
    },
    handleDelete (id) {
      D('admin/menu', [id]).then(data => {
        this.$Message.success(data)
        this.handleSearch()
      })
    },
    dictVal (dict, val) {
      return getDictVal(dict, val)
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
      P('admin/menu/treeList', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
    Dict('menu_web_type').then(data => {
      this.webTypeOptions = data
    })
    Dict('status').then(data => {
      this.statusOptions = data
    })
  }
}
</script>

<style>

</style>
