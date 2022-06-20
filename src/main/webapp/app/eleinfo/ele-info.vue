<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <p>基础信息维护</p>
      <div class="grid-content bg-purple">
        <el-input placeholder="输入关键字进行过滤" v-model="filterText"> </el-input>
        <el-tree
          class="filter-tree"
          :data="data"
          :props="defaultProps"
          default-expand-all
          :filter-node-method="filterNode"
          @node-click="handleNodeClick"
          ref="tree"
        >
        </el-tree>
      </div>
    </el-col>
    <el-col :span="18">
      <el-row>
        <el-button type="primary">增加一行</el-button>
        <el-button type="primary">删除一行</el-button>
        <el-button type="primary">保存</el-button>
      </el-row>
      <el-row>
        <div class="grid-content bg-purple">
          <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="eleCode" label="要素编码"> </el-table-column>
            <el-table-column prop="eleName" label="要素名称"> </el-table-column>
            <el-table-column prop="parentId" label="父节点id"> </el-table-column>
            <el-table-column prop="levelNo" label="级次"> </el-table-column>
            <el-table-column prop="isLeaf" label="是否末级"> </el-table-column>
            <el-table-column prop="isEnabled" label="是否启用"> </el-table-column>
            <el-table-column prop="createTime" label="创建时间"> </el-table-column>
            <el-table-column prop="updateTime" label="更新时间"> </el-table-column>
          </el-table>
        </div>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios';
import qs from 'qs';

const eleUnionsApiUrl = 'api/ele-unions';

export default {
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    handleNodeClick(data) {
      this.initTableData(data);
    },
    deleteRow(index, rows, curRow) {
      rows.splice(index, 1);
      // TODO 用户下线
      console.log('删除id', curRow.userName);
      axios.delete(eleUnionsApiUrl + '/' + curRow.userName).then(res => {
        console.log('删除成功');
      });
    },
    addRow(index, rows, curRow) {
      rows.splice(index, 1);
      // TODO 用户下线
      console.log('删除id', curRow.userName);
      axios.delete(eleUnionsApiUrl + '/' + curRow.userName).then(res => {
        console.log('删除成功');
      });
    },
    initTableData(treeData) {
      // 根据id查询基础要素信息
      axios.get(eleUnionsApiUrl + '/element-info/' + treeData.id).then(res => {
        const response = res.data;
        this.tableData = response;
      });
    },
    initLeftTreeData() {
      axios.get(eleUnionsApiUrl + '/left-tree').then(res => {
        const response = res.data;
        this.data = response;
      });
    },
  },
  data() {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'children',
        label: 'label',
      },
      tableData: [],
    };
  },
  mounted() {
    this.initLeftTreeData();
  },
};
</script>
