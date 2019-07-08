<template>
    <div>
        <Card>
            <div class="search-con search-con-top">
                <Form :model="form" :label-width="80" inline>
                    <FormItem label="角色名称">
                        <Input v-model="form.name"></Input>
                    </FormItem>
                  <FormItem label="租户">
                    <Input v-model="form.tenantId"></Input>
                  </FormItem>
                    <FormItem label="备注">
                        <Input v-model="form.tips"></Input>
                    </FormItem>
                    <Button @click="handleSearch" class="search-btn" type="primary">
                        <Icon type="search"/>
                        搜索
                    </Button>
                </Form>
                <div class="toolbar">
                    <Button type="primary" icon="md-add" @click="handleCreate">新增</Button>&nbsp;&nbsp;
                    <Button type="primary" icon="md-trash" @click="handleDelete">删除</Button>
                </div>
            </div>
            <Row>

              <Col span="18">
                <tables ref="tables" v-model="tableData" :columns="columns" @on-search="handleSearch"
                        @on-update="handleUpdate" @on-detail="handleDetail" @on-selection-change="selectionChange"/>
              </Col>
              <Col span="6" v-if="authRole != null">
                <Tree :data="menuData" show-checkbox multiple empty-text=""></Tree>
                <Divider />
                <div>
                  <span style="padding-left: 20px;padding-right: 40px;">{{authRole.name}}的权限:          </span>
                  <Button @click="saveAuth" class="search-btn" type="primary">
                    <Icon type="ios-checkmark-circle-outline"/>
                    保存
                  </Button>
                </div>
              </Col>
            </Row>
        </Card>
        <roleInfo ref="roleInfoRef" @handle-search="handleSearch"></roleInfo>
    </div>
</template>

<script>
import Tables from '_c/tables'
import { L, D, P } from '@/libs/api.request'
import roleInfo from './role-info'
import { getIds, getListByFieldJudge } from '@/libs/util'
import { Message } from 'iview'

export default {
  name: 'role-list',
  components: {
    Tables,
    roleInfo
  },
  data () {
    return {
      columns: [
        { title: '', key: 'id', type: 'selection', width: 60 },
        { title: '角色名称', key: 'name' },
        { title: '租户', key: 'tenantId' },
        { title: '备注', key: 'tips' },
        {
          title: '操作',
          key: 'handle',
          minWidth: 300,
          options: ['update', 'detail'],
          button: [
            (h, params, vm) => {
              return h('Button', {
                props: {
                  type: 'primary',
                  icon: 'md-construct'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.authRole = params.row
                    this.loadMenuData(params.row)
                  }
                }
              }, '菜单授权')
            }
          ]
        }
      ],
      tableData: {
        rows: [],
        total: 0
      },
      form: {
        map: {}
      },
      tenantOptions: [],
      selectedData: [],
      authRole: null,
      menuData: []
    }
  },
  methods: {
    handleUpdate (param) {
      this.$refs.roleInfoRef.openModel('update', param.row)
    },
    handleDetail (param) {
      this.$refs.roleInfoRef.openModel('detail', param.row)
    },
    handleCreate () {
      this.$refs.roleInfoRef.openModel('create')
    },
    handleDelete () {
      P('admin/role/getUserByRole?roleId=' + getIds(this.selectedData), {}).then(data => {
        if(data ===""){
          D('admin/role', getIds(this.selectedData)).then(data => {
            this.$Message.success(data)
            this.handleSearch()
          })
         } else {
          Message.warning('有用户正在使用该角色请核实解除使用权限后删除')

        }
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
      L('admin/role', param).then(data => {
        this.tableData = data
      })
    },
    loadMenuData (data) {
      this.menuData = []
      P('admin/menu/tree?roleId=' + data.id, {}).then(data => {
        this.menuData = data
      })
    },
    saveAuth () {
      let authArray = []
      getListByFieldJudge(authArray, this.menuData, 'children', 'checked', true)
      getIds(authArray)
      P('admin/role/saveAuth/' + this.authRole.id, getIds(authArray)).then(data => {
        Message.success('授权完成')
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
