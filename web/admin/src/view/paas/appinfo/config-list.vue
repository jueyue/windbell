<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="类型">
                        <Input v-model="form.type"></Input>
                    </FormItem>
                    <FormItem label="内部AppId">
                        <Input v-model="form.appCode"></Input>
                    </FormItem>
                    <FormItem label="外部AppId">
                        <Input v-model="form.appId"></Input>
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
        <appinfoconfigInfo ref="appinfoconfigInfoRef" ></appinfoconfigInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import {L, D} from '@/libs/api.request'
import appinfoconfigInfo from './config-info'
import {getIds} from '@/libs/util'

export default {
  name: 'appinfoconfig-list',
  components: {
    Tables,
    appinfoconfigInfo
  },
  data () {
    return {
      columns: [
        {title: '类型', key: 'type'},
        {title: '内部AppID', key: 'appCode'},
        {title: '外部AppID', key: 'appId'},
        {title: 'APP秘钥', key: 'secret'},
        {title: '子AppId', key: 'subAppId'},
        {title: '内部子AppId', key: 'subAppCode'},
        {title: '子秘钥', key: 'subAppSecret'},
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
      this.$refs.appinfoconfigInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.appinfoconfigInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.appinfoconfigInfoRef.openModel('create')
    },
    handleDelete () {
      D('appinfoconfig', getIds(this.selectedData)).then(data => {
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
      L('appinfoconfig', param).then(data => {
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
