<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <p>采集表信息</p>
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
        <el-button type="primary" @click="toFormulaIn">表内公式</el-button>
        <el-button type="primary" @click="toFormulaOut">表间公式</el-button>
      </el-row>
      <el-row>
        <p>采集表列信息</p>
        <el-table :data="tableData" style="width: 100%" max-height="500" ref="singleTable">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column label="列中文名" prop="colCname">
            <template slot-scope="scope">
              <span v-if="treeSelected == true">{{ scope.row['colCname'] }}</span>
              <!-- 可编辑场景下特殊处理 -->
              <el-input size="small" v-model="scope.row['colCname']" placeholder="请输入列中文名" />
            </template>
          </el-table-column>
          <el-table-column label="列英文名" prop="colEname">
            <template slot-scope="scope">
              <span v-if="treeSelected == true">{{ scope.row['colEname'] }}</span>
              <!-- 可编辑场景下特殊处理 -->
              <el-input size="small" v-model="scope.row['colEname']" placeholder="为空则后台自动产生" />
            </template>
          </el-table-column>
          <el-table-column label="字段类型" prop="type">
            <template slot-scope="scope">
              <el-select size="small" v-model="scope.row['type']" placeholder="请选择内容" value="">
                <el-option v-for="option in colType" :value="option.value" :key="option.code" :label="option.name" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="可编辑" prop="isEdit">
            <template slot-scope="scope">
              <!-- 可编辑场景下特殊处理 -->
              <el-select size="small" v-model="scope.row['isEdit']" placeholder="请选择内容" value="">
                <el-option v-for="option in trueFalse" :value="option.value" :key="option.code" :label="option.name" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="是否必填" prop="requirement">
            <template slot-scope="scope">
              <!-- 可编辑场景下特殊处理 -->
              <el-select size="small" v-model="scope.row['requirement']" placeholder="请选择内容" value="">
                <el-option v-for="option in trueFalse" :value="option.value" :key="option.code" :label="option.name" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="数据源" prop="source">
            <template slot-scope="scope">
              <!-- 选择数据源, 支持根据ele_搜索 -->
              <el-select size="small" v-model="scope.row['source']" filterable placeholder="请选择内容" value="">
                <el-option v-for="option in sourceData" :value="option.value" :key="option.code" :label="option.name" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="toFormulaIn(scope.$index, scope.row)">表内公式</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-col>

    <!-- 表内公式配置界面 -->
    <el-dialog title="表内公式配置" :visible.sync="dialogFormulaInVisible">
      <el-row>
        <el-form ref="form" :model="formula" label-width="80px">
          <el-form-item label="舍入方式">
            <el-select v-model="form.roundType" placeholder="请选择">
              <el-option label="四舍五入" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公式预览">
            <el-input type="textarea" v-model="form.calFormulaDes"></el-input>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row>
        <el-col>
          <!-- 列信息 -->
          <el-tree class="filter-tree" :data="colTreeData" :props="defaultProps" default-expand-all ref="colTree"> </el-tree>
        </el-col>
        <el-col>
          <el-row>
            <!-- 计算器 点击tag需要获取到tag的值 ? -->
            <el-tag @click="handleTagClick">1</el-tag>
            <el-tag>2</el-tag>
            <el-tag>3</el-tag>
            <el-tag>+</el-tag>
          </el-row>
          <el-row>
            <el-tag>4</el-tag>
            <el-tag>5</el-tag>
            <el-tag>6</el-tag>
            <el-tag>-</el-tag>
          </el-row>
          <el-row>
            <el-tag>7</el-tag>
            <el-tag>8</el-tag>
            <el-tag>9</el-tag>
            <el-tag>*</el-tag>
          </el-row>

          <el-row>
            <el-tag>.</el-tag>
            <el-tag>(</el-tag>
            <el-tag>)</el-tag>
            <el-tag>/</el-tag>
          </el-row>
        </el-col>
      </el-row>
    </el-dialog>
  </el-row>
</template>
<script>
import axios from 'axios';
import qs from 'qs';

const sysCollColsApiUrl = 'api/sys-collect-cols';

const sysCollTabsApiUrl = 'api/sys-collect-tabs';

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
          return item.colEname != selected.colEname;
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
        .post(sysCollColsApiUrl + '/save/update', this.tableData)
        .then(res => {
          console.log('保存成功', res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    initTableData(treeData) {
      // 根据id查询基础要素信息
      axios.get(sysCollColsApiUrl + '/' + treeData.id).then(res => {
        const response = res.data;
        this.tableData = response;
      });
    },
    initCollTabsTreeData() {
      // 获取左侧采集表选择信息
      axios.get(sysCollTabsApiUrl + '/tab-tree').then(res => {
        const response = res.data;
        this.data = response;
      });
    },
    toFormulaIn() {
      // 弹出表内公式配置界面
      this.dialogFormulaInVisible = true;
    },
    toFormulaOut() {
      // 弹出表内公式配置界面
    },
    initSourceTreeData() {
      axios.get(eleUnionsApiUrl + '/left-tree').then(res => {
        const response = res.data;
        this.sourceData = response;
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
      colType: [
        // tree, select, input
        { value: tree, code: tree, name: '树型' },
        { value: select, code: select, name: '下拉选择' },
        { value: input, code: input, name: '文本框' },
      ],
      // 数据源信息
      sourceData: [],
      // dialog默认不显示
      dialogFormulaInVisible: false,
      formula: {
        tabEname: '',
        tabCname: '',
        colEname: '',
        colCname: '',
        calFormula: '',
        calFormulaDes: '',
        roundType: '',
        weight: '',
      },
      colTreeData: [],
    };
  },
  mounted() {
    this.initCollTabsTreeData();
  },
};
</script>
