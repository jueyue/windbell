<template>
  <Select v-model="selectedValue" @on-change="selectChange" :label-in-value="true" :disabled="disabled" clearable filterable>
    <Option value="" selected v-if="needAll">无</Option>
    <Option :value='item.id' v-for="(item, index) in dataList" :key="index">{{item.name}}</Option>
  </Select>
</template>

<script>

import {L} from '../../libs/api.request'

export default {
  name: 'User',
  props: {
    role: {
      type: String,
      required: false
    },
    dept: {
      type: String,
      required: false
    },
    needAll: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    },
    value: {
      type: String | Number | Object
    }
  },
  data () {
    return {
      dataList: [],
      selectedValue: ''
    }
  },
  methods: {
    selectChange (obj) {
      this.$emit('on-change', obj.value, obj.label)
      this.$emit('input', obj.value)
    },
    userQuery () {
      let param = {
        page: 1,
        pageSize: 1000,
        model: {
          roleId: this.role,
          deptId: this.dept
        }
      }
      L('admin/user', param).then(data => {
        this.dataList = data.rows
      })
    }
  },
  watch: {
    role (val, oldVal) {
      this.userQuery()
    },
    dept (val, oldVal) {
      this.userQuery()
    },
    value (val, oldVal) {
      if (this.selectedValue !== val) {
        this.selectedValue = val
      }
    }
  },
  mounted () {
    this.userQuery()
    this.selectedValue = ''
  }
}
</script>

<style>

</style>
