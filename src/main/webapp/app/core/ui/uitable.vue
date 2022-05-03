<template>
  <el-row>
    <el-col>
      <div style="margin-top: 20px">
        <el-table ref="singleTable" highlight-current-row :data="tableData" class="table_bsm">
          <el-table-column v-for="(col, index) in cols" :key="index" :prop="col.prop" :label="col.label"> </el-table-column>
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
      // 这里简单测试, menuid不为空才去加载
      axios.get('/json/uitable.json').then(data => {
        let response = data.data.data;
        console.log(response);
        this.cols = response;
      });
    },
    // 父组件不能直接访问子组件, 迂回战术了
    setCurrentRow(tableId, row) {
      console.log('子组件被请求了, 传入数据: ' + row);
      this.$refs.singleTable.setCurrentRow(row);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.table_bsm {
  width: 98%;
  margin: auto;
}
</style>
