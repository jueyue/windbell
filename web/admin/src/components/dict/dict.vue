<template>
  <Select v-model="selectedValue" @on-change="selectChange" :label-in-value="true" :disabled="disabled">
    <Option value="" selected v-if="needAll">无</Option>
    <Option :value='item.key' v-for="(item, index) in dataList" :key="index">{{item.name}}</Option>
  </Select>
</template>

<script>

import {dict as dictQuery} from '@/libs/common.request'

export default {
  name: 'Dict',
  props: {
    dict: {
      type: String,
      required: true
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
    }
  },
  watch: {
    dict (val, oldVal) {
      dictQuery(this.dict).then(data => {
        this.dataList = data
      })
    },
    value (val, oldVal) {
      if (this.selectedValue !== val) {
        this.selectedValue = val
      }
    }
  },
  mounted () {
    dictQuery(this.dict).then(data => {
      this.dataList = data
    })
    this.selectedValue = ''
  }
}
</script>

<style>

</style>
