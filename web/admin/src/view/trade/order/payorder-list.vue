<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="订单号">
                        <Input v-model="form.orderid"></Input>
                    </FormItem>
                    <FormItem label="购买用户ID">
                        <Input v-model="form.userId"></Input>
                    </FormItem>
                    <FormItem label="用户类型">
                        <Input v-model="form.userType"></Input>
                    </FormItem>
                    <FormItem label="商品描述">
                        <Input v-model="form.goodsDesc"></Input>
                    </FormItem>
                    <FormItem label="产品类型">
                        <Input v-model="form.type"></Input>
                    </FormItem>
                    <FormItem label="订单名称">
                        <Input v-model="form.subject"></Input>
                    </FormItem>
                    <FormItem label="支付渠道">
                        <Input v-model="form.channel"></Input>
                    </FormItem>
                    <FormItem label="上游订单号">
                        <Input v-model="form.upOrderId"></Input>
                    </FormItem>
                    <FormItem label="上游渠道">
                        <Input v-model="form.upChannel"></Input>
                    </FormItem>
                    <FormItem label="购买者">
                        <Input v-model="form.buyerId"></Input>
                    </FormItem>
                    <FormItem label="产品价格">
                        <Input v-model="form.price"></Input>
                    </FormItem>
                    <FormItem label="状态1">
                        <Input v-model="form.status"></Input>
                    </FormItem>
                    <FormItem label="Memo">
                        <Input v-model="form.memo"></Input>
                    </FormItem>
                    <FormItem label="支付相关信息">
                        <Input v-model="form.info"></Input>
                    </FormItem>
                    <FormItem label="支付时间">
                        <Input v-model="form.payTime"></Input>
                    </FormItem>
                    <FormItem label="创建时间">
                        <Input v-model="form.crtTime"></Input>
                    </FormItem>
                    <FormItem label="修改人">
                        <Input v-model="form.mdfUserId"></Input>
                    </FormItem>
                    <FormItem label="修改时间">
                        <Input v-model="form.mdfTime"></Input>
                    </FormItem>
                    <FormItem label="Unique">
                        <Input v-model="form.unique"></Input>
                    </FormItem>
                    <Button @click="handleSearch" class="search-btn" type="primary">
                        <Icon type="search"/>
                        搜索
                    </Button>
                </Form>
                <div>
                    <Button type="primary" icon="md-add" @click="handleCreate">新增</Button>
                    <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
                </div>
            </div>
            <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
                    @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
        </Card>
        <payorderInfo ref="payorderInfoRef" ></payorderInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import payorderInfo from './payorder-info'
import {getIds} from '@/libs/util'

export default {
  name: 'payorder-list',
  components: {
    Tables,
    payorderInfo
  },
  data () {
    return {
      columns: [
        {title: '订单号', key: 'orderid'},
        {title: '购买用户ID', key: 'userId'},
        {title: '用户类型', key: 'userType'},
        {title: '商品描述', key: 'goodsDesc'},
        {title: '产品类型', key: 'type'},
        {title: '订单名称', key: 'subject'},
        {title: '支付渠道', key: 'channel'},
        {title: '上游订单号', key: 'upOrderId'},
        {title: '上游渠道', key: 'upChannel'},
        {title: '购买者', key: 'buyerId'},
        {title: '产品价格', key: 'price'},
        {title: '状态1', key: 'status'},
        {title: 'Memo', key: 'memo'},
        {title: '支付相关信息', key: 'info'},
        {title: '支付时间', key: 'payTime'},
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
      this.$refs.payorderInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.payorderInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.payorderInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('trade/payorder', getIds(this.selectedData)).then(data => {
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
      this.L('trade/payorder', param).then(data => {
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
