<template>
  <div>
    <Card>
      <div class="search-con search-con-top">
        <Form :model="form" :label-width="80" inline>
          <FormItem label="产品代码">
            <Input v-model="form.proCode"></Input>
          </FormItem>
          <FormItem label="产品类型">
            <Select v-model="form.proType">
              <Option value="">-全部-</Option>
              <Option :value="item.key" v-for="(item, index) in proTypeOptions" :key="index">{{item.name}}</Option>
            </Select>
          </FormItem>
          <FormItem label="产品名称">
            <Input v-model="form.proName"></Input>
          </FormItem>
          <FormItem label="状态">
            <Select v-model="form.status">
              <Option value="">-全部-</Option>
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
          <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
        </div>
      </div>
      <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
              @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
    </Card>
    <productInfo ref="productInfoRef" @handle-search="handleSearch"></productInfo>
  </div>
</template>

<script>
import Tables from '_c/tables'
import { L, D } from '@/libs/api.request'
import productInfo from './product-info'
import { getDictVal, Dict } from '@/libs/common.request'
import { getIds } from '@/libs/util'

export default {
  name: 'product-list',
  components: {
    Tables,
    productInfo
  },
  data () {
    return {
      columns: [
        { title: '产品代码', key: 'proCode' },
        {
          title: '产品类型',
          key: 'proType',
          render: (h, params) => {
            return h('span', getDictVal('product_type', params.row.proType))
          }
        },
        { title: '产品名称', key: 'proName' },
        {
          title: '状态',
          key: 'status',
          render: (h, params) => {
            return h('span', getDictVal('product_status', params.row.status))
          }
        },
        { title: '备注', key: 'memo' },
        { title: '创建人', key: 'crtUserId' },
        { title: '创建时间', key: 'crtTime' },
        { title: '修改人', key: 'mdfUserId' },
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
      proTypeOptions: [],
      statusOptions: []
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.productInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.productInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.productInfoRef.openModel('create')
    },
    handleDelete () {
      D('admin/product', getIds(this.selectedData)).then(data => {
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
      L('admin/product', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
    Dict('product_type').then(data => {
      this.proTypeOptions = data
    })
    Dict('product_status').then(data => {
      this.statusOptions = data
    })
  }
}
</script>

<style>

</style>
