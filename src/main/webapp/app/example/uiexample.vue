<template>
  <div>
    <ui-view v-bind:menuid="dymenuid" ref="mainRef"></ui-view>
  </div>
</template>

<script>
import View from '@/core/ui/view.vue';

export default {
  data() {
    return {
      // 该菜单主要用作测试
      dymenuid: '0000',
      tabDatas: null,
    };
  },
  components: {
    'ui-view': View,
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    // 自定义加载数据填充列表
    initTableData() {
      // TODO 后台获取数据
      this.tabDatas = [
        {
          name: '张三',
          sex: '男',
          age: '18',
        },
        {
          name: '李四',
          sex: '女',
          age: '16',
        },
        {
          name: '王五',
          sex: '女',
          age: '20',
        },
      ];

      this.$refs.mainRef.setTableDatas('singleTable', this.tabDatas);
    },
    setCurrent() {
      // 操作表格选中一行
      this.$refs.mainRef.setTableCurrentRow('singleTable', this.tabDatas[1]);
    },
    //新增, 按钮需要组件使用者实现
    add() {
      alert('父页面实现新增');
    },
    //编辑
    edit() {
      alert('父页面实现编辑');
    },
    //删除
    delete() {
      alert('父页面实现删除');
    },
    query() {
      // 查询列表数据
      this.$refs.mainRef.setTableDatas('singleTable', this.tabDatas);
    },
  },
};
</script>
