##用到的插件

##vue 使用中遇到的问题
--------
###问题1: 其中proCode是 int 类型,products的proCode是string类型会照成无法自动选中
```html
<Select v-model="form.proCode" :disabled="disable" filterable>
                <Option :value="item.proCode" v-for="(item, index) in products" :key="index">{{item.proName}}</Option>
              </Select>
```
解决办法: 尽量使用一致的类型,不行就在界面强制转换


###全局样式
在main.js引入的样式就是全局样式
