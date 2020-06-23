<template>
  <div>
    <Row :gutter="16">
      <Col span="8">
        <Card>
          <h4>CPU利用率(10秒)</h4>
          <Divider class="divider"></Divider>
          <div style="height: 300px;" ref="cpuDom"></div>
          <Divider class="divider"></Divider>
          <p>CPU核心: {{cpu.cpuNum}}</p>
          <Divider class="divider"></Divider>
          <p>CPU主频: {{cpu.name}}</p>
        </Card>
      </Col>
      <Col span="8">
        <Card>
          <h4>内存利用率</h4>
          <Divider class="divider"></Divider>
          <div style="height: 300px;" ref="memDom"></div>
          <Divider class="divider"></Divider>
          <p>总内存: {{memory.total}}G</p>
          <Divider class="divider"></Divider>
          <p>已用内存: {{memory.used}}G</p>
        </Card>
      </Col>
      <Col span="8">
        <Card>
          <h4>JVM内存利用率</h4>
          <Divider class="divider"></Divider>
          <div style="height: 300px;" ref="jvmDom"></div>
          <Divider class="divider"></Divider>
          <p>总JVM内存: {{jvm.total}}M</p>
          <Divider class="divider"></Divider>
          <p>已用JVM内存: {{jvm.used}}M</p>
        </Card>
      </Col>
    </Row>
    <Card style="margin-top: 20px">
      <h4>系统信息</h4>
      <Table :disabled-hover=true :show-header=false :data="sysdata" :columns="[{
                        title: '名称',
                        key: 'title'
                    },
                    {
                        title: '属性值',
                        key: 'key'
                    }]"></Table>
    </Card>
    <Card style="margin-top: 20px">
      <h4>磁盘信息</h4>
      <Table :disabled-hover=true :data="disk" :columns="diskColumn" style="overflow:hidden;"></Table>
    </Card>
  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'monitor',
  data () {
    return {
      timer: null,
      cpu: {},
      memory: {},
      jvm: {},
      disk: [],
      diskColumn: [{
        title: '类型',
        key: 'sysTypeName'
      },
      {
        title: '名称',
        key: 'typeName'
      },
      {
        title: '大小',
        key: 'total'
      },
      {
        title: '已用',
        key: 'used'
      }, {
        title: '使用率',
        key: 'usage',
        render: (h, params) => {
          return h('Progress', {props: { percent: params.row.usage }})
        }
      }],
      sysdata: [],
      cpuDom: null,
      memDom: null,
      jvmDom: null
    }
  },
  methods: {
    loaddata () {
      this.G('admin/system/monitor').then(data => {
        this.cpu = data.cpu
        this.memory = data.memory
        this.jvm = data.jvm
        this.disk = data.disk
        this.sysdata = []
        this.sysdata.push({title: '系统名称', key: data.osName})
        this.sysdata.push({title: '系统版本', key: data.osArch})
        this.sysdata.push({title: '目录', key: data.dir})
        this.sysdata.push({title: '主机名称', key: data.localhost})
        this.sysdata.push({title: 'IP', key: data.ip})
        this.sysdata.push({title: 'JDK', key: data.jvm.name})
        this.sysdata.push({title: 'JDK版本', key: data.jvm.version})
        this.sysdata.push({title: 'JDK路径', key: data.jvm.home})
        this.cpuDom = echarts.init(this.$refs.cpuDom)
        this.cpuDom.setOption(this.getGaugeOption(data.cpu.usage))
        this.memDom = echarts.init(this.$refs.memDom)
        this.memDom.setOption(this.getGaugeOption(data.memory.usage))
        this.jvmDom = echarts.init(this.$refs.jvmDom)
        this.jvmDom.setOption(this.getGaugeOption(data.jvm.usage))
      })
    },
    getGaugeOption (data) {
      return {
        tooltip: {
          formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
          feature: {
            restore: {},
            saveAsImage: {}
          }
        },
        series: [
          {
            axisLine: {
              lineStyle: {
                width: 10
              }
            },
            type: 'gauge',
            detail: {formatter: '{value}%'},
            data: [{value: data}]
          }
        ]
      }
    }
  },
  created () {
    this.loaddata()
    this.timer = setInterval(this.loaddata, 10 * 1000)
  },
  beforeDestroy () {
    clearInterval(this.timer)
  }
}
</script>
<style scoped>
  .divider{
    margin: 5px 0;
  }
  .ivu-table-overflowX{ overflow-x: hidden;}
</style>
