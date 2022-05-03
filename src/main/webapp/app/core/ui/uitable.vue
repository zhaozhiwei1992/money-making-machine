<template>
  <el-row>
    <el-col>
      <div style="margin-top: 20px">
        <el-table ref="singleTable" highlight-current-row :data="tableData" class="uitable">
          <el-table-column v-for="(col, index) in cols" :key="index" :prop="col.code" :label="col.name"> </el-table-column>
        </el-table>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios';
export default {
  props: ['menuid', 'tableData'],
  data() {
    return {
      cols: [],
    };
  },

  mounted() {
    this.initComponent();
  },
  methods: {
    initComponent() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取编辑区信息
      console.log('父级传入menuid为: ' + this.menuid);
      // 根据菜单id,后端获取配置信息
      if (!!!this.menuid) {
        axios.get('/json/uitable.json').then(data => {
          let response = data.data.data;
          console.log(response);
          this.cols = response;
        });
      }
    },
    // 父组件不能直接访问子组件, 组件提供方法给外部调用, 不提供的不建议直接在基础组件中使用
    setCurrentRow(tableId, row) {
      console.log('子组件被请求了, 传入数据: ' + row);
      this.$refs.singleTable.setCurrentRow(row);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.uitable {
  width: 98%;
  margin: auto;
}
</style>
