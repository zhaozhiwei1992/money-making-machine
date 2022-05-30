<template>
  <el-table :data="tableData" style="width: 100%" max-height="250">
    <el-table-column prop="nowTime" label="登录时间" width="200"> </el-table-column>
    <el-table-column fixed prop="userName" label="用户名" width="200"> </el-table-column>
    <el-table-column prop="ip" label="IP" width="200"> </el-table-column>
    <el-table-column prop="browser" label="浏览器" width="200"> </el-table-column>
    <el-table-column prop="os" label="操作系统" width="300"> </el-table-column>
    <el-table-column fixed="right" label="Operations" width="120">
      <template slot-scope="scope">
        <el-button @click.native.prevent="deleteRow(scope.$index, tableData, scope.row)" type="text" size="small"> Remove </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import axios from 'axios';
import qs from 'qs';

const onlineUserApiUrl = 'api/online/users';

export default {
  methods: {
    deleteRow(index, rows, curRow) {
      rows.splice(index, 1);
      // TODO 用户下线
      console.log('删除id', curRow.userName);
      axios.delete(onlineUserApiUrl + '/' + curRow.userName).then(res => {
        console.log('删除成功');
      });
    },
    initTableData() {
      axios.get(onlineUserApiUrl).then(res => {
        const response = res.data;
        this.tableData = response;
      });
    },
  },
  data() {
    return {
      tableData: [],
    };
  },
  mounted() {
    this.initTableData();
  },
};
</script>
