<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="标题">
                        <Input v-model="form.title"></Input>
                    </FormItem>
                    <FormItem label="类型">
                      <Select v-model="form.type">
                        <Option :value="item.key" v-for="(item, index) in typeOptions" :key="index">{{item.name}}</Option>
                      </Select>
                    </FormItem>
                    <FormItem label="内容">
                        <Input v-model="form.content"></Input>
                    </FormItem>
                    <FormItem label="发送时间">
                        <Input v-model="form.sendTime"></Input>
                    </FormItem>
                    <FormItem label="地址列表">
                        <Input v-model="form.address"></Input>
                    </FormItem>
                    <FormItem label="渠道(APP)">
                        <Input v-model="form.channel"></Input>
                    </FormItem>
                </Form>
                <div  class="toolbar">
                    <Button type="primary" icon="md-add" @click="handleCreate">发送</Button>
                   <Button @click="handleSearch" icon="md-search" class="search-btn" type="primary">搜索 </Button>
                </div>
            </div>
            <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
                    @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
        </Card>
        <noticeInfo ref="noticeInfoRef"  @handle-search="handleSearch"></noticeInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import noticeInfo from './notice-info'
import {getIds} from '@/libs/util'

export default {
  name: 'notice-list',
  components: {
    Tables,
    noticeInfo
  },
  data () {
    return {
      columns: [
        {title: '标题', key: 'title'},
        {title: '类型', key: 'type'},
        {title: '内容', key: 'content'},
        {title: '发送时间', key: 'sendTime'},
        {title: '角色列表', key: 'rolesIds'},
        {title: '用户列表', key: 'userIds'},
        {title: '部门列表', key: 'deptIds'},
        {title: '地址列表', key: 'address'},
        {title: '渠道(APP', key: 'channel'},
        {title: '创建时间', key: 'crtTime'},
        {title: '修改时间', key: 'mdfTime'}
      ],
      tableData: {
        rows: [],
        total: 0
      },
      form: {
        map: {}
      },
      selectedData: [],
      typeOptions: [],
      infoIsShow: false
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.noticeInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.noticeInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.noticeInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('/paas/notice', getIds(this.selectedData)).then(data => {
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
      this.L('/paas/notice', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
    this.dict('msg_type').then(data => {
      this.typeOptions = data
    })
  }
}
</script>
