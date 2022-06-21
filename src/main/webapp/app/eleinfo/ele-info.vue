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
        <el-button type="primary" @click="addRow">增加行</el-button>
        <el-button type="primary" @click="deleteRow">删除行</el-button>
        <el-button type="primary" @click="saveData">保存</el-button>
      </el-row>
      <el-row>
        <div class="grid-content bg-purple">
          <el-table :data="tableData" style="width: 100%" ref="singleTable">
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column prop="eleCatCode" label="分类编码">
              <template slot-scope="scope">
                <span v-if="treeSelected == true">{{ scope.row['eleCatCode'] }}</span>
                <!-- 可编辑场景下特殊处理 -->
                <el-input size="small" v-model="scope.row['eleCatCode']" placeholder="请输入要素分类编码" />
              </template>
            </el-table-column>
            <el-table-column prop="eleCatName" label="分类名称">
              <template slot-scope="scope">
                <span v-if="treeSelected == true">{{ scope.row['eleCatName'] }}</span>
                <!-- 可编辑场景下特殊处理 -->
                <el-input size="small" v-model="scope.row['eleCatName']" placeholder="请输入要素分类名称" />
              </template>
            </el-table-column>
            <el-table-column prop="eleCode" label="要素编码">
              <template slot-scope="scope">
                <!-- 可编辑场景下特殊处理 -->
                <el-input size="small" v-model="scope.row['eleCode']" placeholder="请输入要素编码" />
              </template>
            </el-table-column>
            <el-table-column prop="eleName" label="要素名称">
              <template slot-scope="scope">
                <!-- 可编辑场景下特殊处理 -->
                <el-input size="small" v-model="scope.row['eleName']" placeholder="请输入要素名称" />
              </template>
            </el-table-column>
            <el-table-column prop="parentId" label="父节点id">
              <template slot-scope="scope">
                <!-- 可编辑场景下特殊处理 -->
                <el-input size="small" v-model="scope.row['parentId']" placeholder="请输入父级id" />
              </template>
            </el-table-column>
            <el-table-column prop="levelNo" label="级次"> </el-table-column>
            <el-table-column prop="isLeaf" label="是否末级">
              <template slot-scope="scope">
                <!-- 可编辑场景下特殊处理 -->
                <el-select size="small" v-model="scope.row['isLeaf']" placeholder="请选择内容" value="">
                  <el-option v-for="option in trueFalse" :value="option.value" :key="option.code" :label="option.name" />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column prop="isEnabled" label="是否启用">
              <template slot-scope="scope">
                <!-- 可编辑场景下特殊处理 -->
                <el-select size="small" v-model="scope.row['isEnabled']" placeholder="请选择内容" value="">
                  <el-option v-for="option in trueFalse" :value="option.value" :key="option.code" :label="option.name" />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column prop="updateTime" label="更新时间"></el-table-column>
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
    deleteRow() {
      // 1. 获取列表选中行
      const selectedData = this.$refs.singleTable.selection;
      // 2. 不存在id, 直接删除tableData中数据
      selectedData.forEach((selected, i) => {
        this.tableData = this.tableData.filter(function (item, index) {
          // 根据eleCode来删除数据
          return item.eleCode != selected.eleCode;
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
        .post(eleUnionsApiUrl + '/save/update', this.tableData)
        .then(res => {
          console.log('保存成功', res);
        })
        .catch(err => {
          console.log(err);
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
      // 默认不选中
      treeSelected: false,
      trueFalse: [
        { value: true, code: true, name: '是' },
        { value: false, code: false, name: '否' },
      ],
    };
  },
  mounted() {
    this.initLeftTreeData();
  },
};
</script>
