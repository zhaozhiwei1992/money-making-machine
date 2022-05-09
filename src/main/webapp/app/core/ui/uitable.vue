<template>
  <div style="margin-top: 20px">
    <el-row>
      <el-col :span="6">
        <el-input v-model="search" placeholder="请输入搜索内容" style="margin-bottom: 15px"></el-input>
      </el-col>
      <el-col :span="3">
        <el-button type="primary" icon="el-icon-search" @click="handleClick()">搜索</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-table :ref="innercomponentid" highlight-current-row :data="tableData" class="uitable">
          <el-table-column type="selection" width="40" align="center"></el-table-column>
          <el-table-column v-for="(col, index) in cols" :key="index" :prop="col.code" :label="col.name"> </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios';

const baseApiUrl = 'api/ui-tables';

export default {
  props: ['componentid', 'menuid', 'tableData'],
  data() {
    return {
      search: '',
      cols: [],
      innercomponentid: 'singleTable',
    };
  },

  mounted() {
    // 组件id赋值, 可以通过ref找到自己添加的组件
    this.innercomponentid = this.componentid;
    this.initComponent();
  },
  methods: {
    initComponent() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取编辑区信息
      console.log('父级传入menuid为: ' + this.menuid);
      // 根据菜单id,后端获取配置信息
      if (!!this.menuid) {
        axios
          .get(baseApiUrl + '/menu/' + this.menuid)
          .then(res => {
            let response = res.data;
            console.log(response);
            this.cols = response;
          })
          .catch(err => {
            console.log('异常信息: ' + err);
          });
      }
    },
    // 父组件不能直接访问子组件, 组件提供方法给外部调用, 不提供的不建议直接在基础组件中使用
    setCurrentRow(tableId, row) {
      console.log('子组件被请求了, 传入数据: ' + row);
      this.$refs.singleTable.setCurrentRow(row);
    },

    handleClick() {
      // 父组件实现快速查询方法
      this.$parent.$parent.fastQuery(this.search);
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
