<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="账户">
                        <Input v-model="form.accountNo"></Input>
                    </FormItem>
                    <FormItem label="订单号">
                        <Input v-model="form.tradeNo"></Input>
                    </FormItem>
                    <FormItem label="交易时间">
                        <Input v-model="form.tradeTime"></Input>
                    </FormItem>
                    <FormItem label="科目">
                        <Input v-model="form.subject"></Input>
                    </FormItem>
                    <FormItem label="关联订单号">
                        <Input v-model="form.outerTradeNo"></Input>
                    </FormItem>
                    <FormItem label="状态">
                        <Input v-model="form.status"></Input>
                    </FormItem>
                </Form>
                <div  class="toolbar">
                   <Button @click="handleSearch" icon="md-search" class="search-btn" type="primary">搜索 </Button>
                </div>
            </div>
            <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
                    @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
        </Card>
        <accountflowInfo ref="accountflowInfoRef"  @handle-search="handleSearch"></accountflowInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import accountflowInfo from './accountflow-info'
import {getIds} from '@/libs/util'

export default {
  name: 'accountflow-list',
  components: {
    Tables,
    accountflowInfo
  },
  data () {
    return {
      columns: [
        {title: '账户', key: 'accountNo'},
        {title: '订单号', key: 'tradeNo'},
        {title: '三方交易时间', key: 'thirdTradeTime'},
        {title: '交易时间', key: 'tradeTime'},
        {title: '科目', key: 'subject'},
        {title: '三方订单号', key: 'outerTradeNo'},
        {title: '备注', key: 'memo'},
        {title: '币种', key: 'currency'},
        {title: '金额', key: 'money'},
        {title: '手续费', key: 'fee'},
        {title: '到账金额', key: 'realMoney'},
        {title: '交易信息', key: 'tradeInfo'},
        {title: '完成时间', key: 'completeTime'},
        {title: '更新时间', key: 'updateTime'},
        {title: '状态', key: 'status'},
        {title: '当前余额', key: 'balance'},
        {title: '原余额', key: 'beforeBalance'}
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
      this.$refs.accountflowInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.accountflowInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.accountflowInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('trade/accountflow', getIds(this.selectedData)).then(data => {
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
      this.L('trade/accountflow', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
  }
}
</script>
