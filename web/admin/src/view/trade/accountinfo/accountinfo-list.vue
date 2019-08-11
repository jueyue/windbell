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
                    <FormItem label="应用">
                        <Input v-model="form.appid"></Input>
                    </FormItem>
                    <FormItem label="类型">
                        <Input v-model="form.type"></Input>
                    </FormItem>
                    <FormItem label="余额">
                        <Input v-model="form.balance"></Input>
                    </FormItem>
                    <FormItem label="不可提现余额">
                        <Input v-model="form.frozenBalance"></Input>
                    </FormItem>
                    <FormItem label="不可提现余额校验值">
                        <Input v-model="form.frozenBalCheck"></Input>
                    </FormItem>
                    <FormItem label="币种">
                        <Input v-model="form.currency"></Input>
                    </FormItem>
                    <FormItem label="开户时间">
                        <Input v-model="form.openTime"></Input>
                    </FormItem>
                    <FormItem label="是否允许充值">
                        <Input v-model="form.isRecharge"></Input>
                    </FormItem>
                    <FormItem label="是否允许提现">
                        <Input v-model="form.isCash"></Input>
                    </FormItem>
                    <FormItem label="是否允许余额为负">
                        <Input v-model="form.isBalanceNegative"></Input>
                    </FormItem>
                    <FormItem label="用户名称">
                        <Input v-model="form.userName"></Input>
                    </FormItem>
                    <FormItem label="余额校验值">
                        <Input v-model="form.balCheck"></Input>
                    </FormItem>
                    <FormItem label="用户手机">
                        <Input v-model="form.userPhone"></Input>
                    </FormItem>
                    <FormItem label="创建时间">
                        <Input v-model="form.crtTime"></Input>
                    </FormItem>
                    <FormItem label="修改时间">
                        <Input v-model="form.mdfTime"></Input>
                    </FormItem>
                    <FormItem label="修改用户">
                        <Input v-model="form.mdfUserId"></Input>
                    </FormItem>
                    <FormItem label="Unique">
                        <Input v-model="form.unique"></Input>
                    </FormItem>
                    <FormItem label="Unique">
                        <Input v-model="form.unique"></Input>
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
        {title: '不可提现余额', key: 'frozenBalance'},
        {title: '不可提现余额校验值', key: 'frozenBalCheck'},
        {title: '币种', key: 'currency'},
        {title: '开户时间', key: 'openTime'},
        {title: '是否允许充值', key: 'isRecharge'},
        {title: '是否允许提现', key: 'isCash'},
        {title: '是否允许余额为负', key: 'isBalanceNegative'},
        {title: '用户名称', key: 'userName'},
        {title: '余额校验值', key: 'balCheck'},
        {title: '用户手机', key: 'userPhone'},
        {title: '创建时间', key: 'crtTime'},
        {title: '修改时间', key: 'mdfTime'},
        {title: '修改用户', key: 'mdfUserId'},
        {title: 'Unique', key: 'unique'},
        {title: 'Unique', key: 'unique'},
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
      this.$refs.accountinfoInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.accountinfoInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.accountinfoInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('accountinfo', getIds(this.selectedData)).then(data => {
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
      this.L('accountinfo', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
  }
}
</script>
