<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="账户">
                        <Input v-model="form.accountNo"></Input>
                    </FormItem>
                    <FormItem label="账户名称">
                        <Input v-model="form.name"></Input>
                    </FormItem>
                    <FormItem label="用户名称">
                      <Input v-model="form.userName"></Input>
                    </FormItem>
                    <FormItem label="用户手机">
                      <Input v-model="form.userPhone"></Input>
                    </FormItem>
                    <FormItem label="应用">
                        <Input v-model="form.appid"></Input>
                    </FormItem>
                    <FormItem label="类型">
                        <Input v-model="form.type"></Input>
                    </FormItem>
                    <FormItem label="允许充值">
                      <Select v-model="form.isRecharge" clearable="true">
                        <Option :value='item.key' v-for='(item, index) in yesNoOptions' :key='index'>{{item.name}}</Option>
                      </Select>
                    </FormItem>
                    <FormItem label="允许提现">
                      <Select v-model="form.isCash" clearable="true">
                        <Option :value='item.key' v-for='(item, index) in yesNoOptions' :key='index'>{{item.name}}</Option>
                      </Select>
                    </FormItem>
                    <FormItem label="允许为负">
                      <Select v-model="form.isBalanceNegative" clearable="true">
                        <Option :value='item.key' v-for='(item, index) in yesNoOptions' :key='index'>{{item.name}}</Option>
                      </Select>
                    </FormItem>
                  <FormItem></FormItem>
                  <FormItem></FormItem>
                    <FormItem>
                      <Button @click="handleSearch" icon="md-search" class="search-btn" type="primary">搜索 </Button>
                    </FormItem>
                </Form>
            </div>
            <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
                    @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
        </Card>
        <accountinfoInfo ref="accountinfoInfoRef"  @handle-search="handleSearch"></accountinfoInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import accountinfoInfo from './accountinfo-info'
import {getIds} from '@/libs/util'

export default {
  name: 'accountinfo-list',
  components: {
    Tables,
    accountinfoInfo
  },
  data () {
    return {
      columns: [
        {title: '账户', key: 'accountNo'},
        {title: '账户名称', key: 'name'},
        {title: '应用', key: 'appid'},
        {title: '类型', key: 'type'},
        {title: '余额', key: 'balance'},
        {title: '冻结金额', key: 'frozenBalance'},
        {title: '开户时间', key: 'openTime'},
        {title: '允许充值',
          key: 'isRecharge',
          render: (h, params) => {
            return h('span', this.getDictVal('yes_no', params.row.isRecharge))
          }},
        {title: '允许提现',
          key: 'isCash',
          render: (h, params) => {
            return h('span', this.getDictVal('yes_no', params.row.isCash))
          }},
        {title: '允许为负',
          key: 'isBalanceNegative',
          render: (h, params) => {
            return h('span', this.getDictVal('yes_no', params.row.isBalanceNegative))
          }},
        {title: '用户名称', key: 'userName'},
        {title: '用户手机', key: 'userPhone'},
        {title: '创建时间', key: 'crtTime'},
        {title: '修改时间', key: 'mdfTime'},
        {
          title: '操作',
          key: 'handle',
          minWidth: 100,
          options: ['update']
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
      yesNoOptions: [],
      infoIsShow: false
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.accountinfoInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.accountinfoInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.accountinfoInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('trade/accountinfo', getIds(this.selectedData)).then(data => {
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
      this.L('trade/accountinfo', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.numDict('yes_no').then(data => {
      this.yesNoOptions = data
    })
    this.handleSearch()
  }
}
</script>
