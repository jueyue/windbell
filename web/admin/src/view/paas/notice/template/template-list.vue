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
                        <Input v-model="form.msgType"></Input>
                    </FormItem>
                    <FormItem label="营销短信">
                        <Input v-model="form.isMarketingMsg"></Input>
                    </FormItem>
                    <FormItem label="指定渠道">
                        <Input v-model="form.channelCode"></Input>
                    </FormItem>
                    <FormItem label="状态">
                        <Input v-model="form.status"></Input>
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
        {title: '信息类型', key: 'msgType'},
        {title: '营销短信', key: 'isMarketingMsg'},
        {title: '模板内容', key: 'content'},
        {title: '链接', key: 'link'},
        {title: '指定渠道', key: 'channelCode'},
        {title: '业务子通道', key: 'extSubCode'},
        {title: '状态', key: 'status'},
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
      infoIsShow: false
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
  }
}
</script>
