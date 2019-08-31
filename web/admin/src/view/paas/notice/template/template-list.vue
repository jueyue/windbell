<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="模板名称">
                        <Input v-model="form.name"></Input>
                    </FormItem>
                    <FormItem label="模板编码">
                        <Input v-model="form.code"></Input>
                    </FormItem>
                    <FormItem label="信息类型">
                      <Select v-model="form.msgType" clearable="true">
                        <Option :value="item.key" v-for="(item, index) in typeOptions" :key="index">{{item.name}}</Option>
                      </Select>
                    </FormItem>
                    <FormItem label="营销短信">
                        <Select v-model="form.marketingMsg" clearable="true">
                        <Option :value='item.key' v-for='(item, index) in yesNoOptions' :key='index'>{{item.name}}</Option>
                      </Select>
                    </FormItem>
                    <FormItem label="指定渠道">
                        <Input v-model="form.channelCode"></Input>
                    </FormItem>
                    <FormItem label="状态">
                      <Select v-model="form.status"  clearable="true">
                        <Option :value="item.key" v-for="(item, index) in statusOptions" :key="index">{{item.name}}</Option>
                      </Select>
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
        <noticetemplateInfo ref="noticetemplateInfoRef"  @handle-search="handleSearch"></noticetemplateInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import noticetemplateInfo from './template-info'
import {getIds} from '@/libs/util'

export default {
  name: 'noticetemplate-list',
  components: {
    Tables,
    noticetemplateInfo
  },
  data () {
    return {
      columns: [
        {title: '模板名称', key: 'name'},
        {title: '模板编码', key: 'code'},
        {title: '信息类型',
          key: 'msg_type',
          render: (h, params) => {
            return h('span', this.getDictVal('msg_type', params.row.msgType))
          }},
        {title: '营销短信',
          key: 'marketingMsg',
          render: (h, params) => {
            return h('span', this.getDictVal('yes_no', params.row.marketingMsg))
          }},
        {title: '模板内容', key: 'content'},
        {title: '链接', key: 'link'},
        {title: '指定渠道', key: 'channelCode'},
        {title: '业务子通道', key: 'extSubCode'},
        {title: '状态',
          key: 'status',
          render: (h, params) => {
            return h('span', this.getDictVal('status', params.row.status))
          }},
        {title: '三方模板', key: 'thirdTemplateCode'},
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
      infoIsShow: false,
      typeOptions: [],
      yesNoOptions: [],
      statusOptions: []
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.noticetemplateInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.noticetemplateInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.noticetemplateInfoRef.openModel('create')
    },
    handleDelete () {
      this.D('/paas/notice/template', getIds(this.selectedData)).then(data => {
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
      this.L('/paas/notice/template', param).then(data => {
        this.tableData = data
      })
    }
  },
  mounted () {
    this.handleSearch()
    this.dict('msg_type').then(data => {
      this.typeOptions = data
    })
    this.numDict('yes_no').then(data => {
      this.yesNoOptions = data
    })
    this.dict('status').then(data => {
      this.statusOptions = data
    })
  }
}
</script>
