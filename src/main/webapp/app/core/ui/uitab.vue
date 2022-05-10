<template>
  <div style="margin-top: 20px">
    <el-tabs v-model="tabValue" type="card" @tab-click="handleClick">
      <el-tab-pane :key="item.code" v-for="item in tabs" :label="item.name" :name="item.code"> </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from 'axios';

const baseApiUrl = 'api/ui-tabs';

export default {
  props: ['componentid', 'menuid'],
  data() {
    return {
      tabValue: '',
      tabs: [],
    };
  },

  mounted() {
    // 组件id赋值, 可以通过ref找到自己添加的组件
    // this.innercomponentid = this.componentid;
    this.initComponent();
  },
  methods: {
    initComponent() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取页签区信息
      console.log('页签区父级传入menuid为: ' + this.menuid);
      // 根据菜单id,后端获取配置信息
      if (!!this.menuid) {
        axios
          .get(baseApiUrl + '/menu/' + this.menuid)
          .then(res => {
            let response = res.data;
            console.log('页签信息', response);
            this.tabs = response;
            // 默认激活第一个
            this.tabValue = this.tabs[0].code;
          })
          .catch(err => {
            console.log('异常信息: ' + err);
          });
      }
    },
    handleClick(tab, event) {
      console.log('页签点击', this.tabValue);
      // 触发父级,页签点击事件
      this.$parent.$parent.tabClick(this.tabValue);
    },
  },
};
</script>
