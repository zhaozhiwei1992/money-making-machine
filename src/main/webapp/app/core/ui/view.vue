<template>
  <div>
    <!-- 配置的界面都由该view.vue组件动态渲染 -->
    <!-- 动态加载组件 -->
    <component
      v-for="(item, index) in componentArray"
      :key="index"
      v-bind:is="item.componentid"
      v-bind:menuid="xx"
      v-bind:componentid="item.componentid"
      v-bind:tableData="tabDatas"
      :ref="item.componentid"
    ></component>
  </div>
</template>

<script>
// 所有能提供的组件在这里维护好
import Editform from '@/core/ui/uieditform.vue';
import Table from '@/core/ui/uitable.vue';
import Toolbutton from '@/core/ui/uitoolbutton.vue';
import Tab from '@/core/ui/uitab.vue';

import axios from 'axios';

const baseApiUrl = 'api/ui-components';

export default {
  props: ['menuid'],
  data() {
    return {
      xx: null,
      tabDatas: null,
      componentArray: [],
    };
  },
  components: {
    uieditform: Editform,
    uitable: Table,
    uitoolbutton: Toolbutton,
    uitab: Tab,
    // uieditform: () => import('@/core/ui/uieditform.vue'),
    // uitable: () => import('@/core/ui/uitable.vue'),
    // uitoolbutton: () => import('@/core/ui/uitoolbutton.vue'),
  },
  mounted() {
    this.initComponents();
  },
  methods: {
    initComponents() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取编辑区信息
      console.log('父级传入menuid为: ' + this.menuid);
      this.xx = this.menuid;
      // 这里简单测试, menuid不为空才去加载
      if (!!this.menuid) {
        axios
          .get(baseApiUrl + '/menu/' + this.menuid)
          .then(res => {
            let response = res.data;
            console.log(response);
            this.componentArray = response;
          })
          .catch(err => {
            console.log('异常信息: ' + err);
          });
      }
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
