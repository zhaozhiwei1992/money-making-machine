<template>
  <el-row :gutter="20">
    <el-col :span="24">
      <el-row>
        <el-button type="primary" @click="addRow">增加行</el-button>
        <el-button type="primary" @click="deleteRow">删除行</el-button>
        <el-button type="primary" @click="saveData">保存</el-button>
      </el-row>
      <el-row>
        <p>采集表信息</p>
        <el-table :data="tableData" style="width: 100%" max-height="550" ref="singleTable">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column label="表中文名" prop="tabCname">
            <template slot-scope="scope">
              <!-- 可编辑场景下特殊处理 -->
              <el-input size="small" v-model="scope.row['tabCname']" placeholder="请输入表中文名" />
            </template>
          </el-table-column>
          <el-table-column label="表英文名" prop="tabEname">
            <template slot-scope="scope">
              <!-- 可编辑场景下特殊处理 -->
              <el-input size="small" v-model="scope.row['tabEname']" placeholder="为空则后台自动产生" />
            </template>
          </el-table-column>
          <el-table-column label="是否启用" prop="enable">
            <template slot-scope="scope">
              <!-- 可编辑场景下特殊处理 -->
              <el-select size="small" v-model="scope.row['enable']" placeholder="请选择内容" value="">
                <el-option v-for="option in trueFalse" :value="option.value" :key="option.code" :label="option.name" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-col>
  </el-row>
</template>
<script>
import axios from 'axios';
import qs from 'qs';

const sysCollTabsApiUrl = 'api/sys-collect-tabs';

export default {
  methods: {
    deleteRow() {
      // 1. 获取列表选中行
      const selectedData = this.$refs.singleTable.selection;
      // 2. 不存在id, 直接删除tableData中数据
      selectedData.forEach((selected, i) => {
        this.tableData = this.tableData.filter(function (item, index) {
          // 根据eleCode来删除数据
          return item.tabEname != selected.tabEname;
        });
      });
    },
    addRow() {
      console.log('新增一行');
      this.tableData.push({});
    },
    saveData() {
      // 保存列表数据
      axios
        .post(sysCollTabsApiUrl + '/save/update', this.tableData)
        .then(res => {
          console.log('保存成功', res);
          alert('保存成功');
        })
        .catch(err => {
          console.log(err);
        });
    },
    initTableData() {
      // 根据id查询基础要素信息
      axios.get(sysCollTabsApiUrl).then(res => {
        const response = res.data;
        this.tableData = response;
      });
    },
  },
  data() {
    return {
      tableData: [],
      trueFalse: [
        { value: true, code: true, name: '是' },
        { value: false, code: false, name: '否' },
      ],
    };
  },
  mounted() {
    this.initTableData();
  },
};
</script>
