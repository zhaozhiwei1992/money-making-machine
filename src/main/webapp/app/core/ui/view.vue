<template>
  <div>
    <!-- 配置的界面都由该view.vue组件动态渲染 -->
    <!-- 动态加载组件 -->
    <component
      v-for="item in componentArray"
      v-bind:is="item.componentName"
      v-bind:menuid="xx"
      v-bind:tableData="tabDatas"
      :ref="item.componentName"
    ></component>
  </div>
</template>

<script>
// 所有能提供的组件在这里维护好
import Editform from '@/core/ui/editform.vue';
import Table from '@/core/ui/table.vue';
import Toolbutton from '@/core/ui/toolbutton.vue';

import axios from 'axios';

export default {
  data() {
    return {
      xx: 'test',
      tabDatas: null,
      componentArray: [],
    };
  },
  components: {
    uieditform: Editform,
    uitable: Table,
    uitoolbutton: Toolbutton,
  },
  mounted() {
    this.initComponents();
  },
  methods: {
    initComponents() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取编辑区信息
      console.log('父级传入menuid为: ' + this.menuid);
      // 这里简单测试, menuid不为空才去加载
      axios.get('/json/components.json').then(data => {
        let response = data.data.data;
        console.log(response);
        this.componentArray = response;
      });
    },
    setTableDatas(tableId, datas) {
      this.tabDatas = datas;
    },
    setTableCurrentRow(tableId, row) {
      // 操作指定表格选中一行
      console.log('refs: ' + this.$refs);
      // 动态构建的组件, refs这个玩意儿变成了数组
      this.$refs.uitable[0].setCurrentRow(tableId, row);
    },
  },
};
</script>
