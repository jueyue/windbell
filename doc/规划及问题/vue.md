##用到的插件
https://github.com/MisterTaki/vue-table-with-tree-grid

##vue 使用中遇到的问题
--------
###问题1: 其中proCode是 int 类型,products的proCode是string类型会照成无法自动选中
```html
<Select v-model="form.proCode" :disabled="disable" filterable>
    <Option :value="item.proCode" v-for="(item, index) in products" :key="index">{{item.proName}}</Option>
</Select>
```
解决办法: 尽量使用一致的类型,不行就在界面强制转换

###问题2: async-validator无法验证数字的解决办法
```json
rules: {
   pwd: [{ required: true, message: '请填写密码', trigger: 'blur' }],
   group:[{ required: true, type: 'number', message: '请选择分组', trigger: 'change' }]
}
```
type:number 解决数字类型不修改提示问题

###全局样式
在main.js引入的样式就是全局样式


iview 校验的坑
https://www.jianshu.com/p/ffbd55a2c866



