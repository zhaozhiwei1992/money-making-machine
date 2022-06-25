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
      </el-row>
      <el-row>
        <p>采集表列信息</p>
        <el-table :data="tableData" style="width: 100%" max-height="500" ref="singleTable">
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column label="列中文名" prop="colCname">
            <template slot-scope="scope">
              <!-- 可编辑场景下特殊处理 -->
              <el-input size="small" v-model="scope.row['colCname']" placeholder="请输入列中文名" />
            </template>
          </el-table-column>
          <el-table-column label="列英文名" prop="colEname">
            <template slot-scope="scope">
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
                <el-option v-for="option in sourceData" :value="option.eleCatCode" :key="option.eleCatCode" :label="option.eleCatName" />
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

    <!-- 公式配置界面 
    不选择公式界面表可以只配置表内公式
    选择了表信息, 则可关联外部表进行数据计算
    -->
    <el-dialog title="公式配置" :visible.sync="dialogFormulaInVisible">
      <el-row>
        <el-form ref="form" :model="formula" label-width="80px">
          <el-form-item label="舍入方式">
            <el-select v-model="formula.roundType" placeholder="请选择">
              <el-option label="四舍五入" value="HALF_UP"></el-option>
              <!-- RoundingMode.ROUND_HALF_DOWN：向“最接近的”整数舍入 -->
              <el-option label="五舍六入" value="HALF_DOWN"></el-option>
              <!-- 即四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一。  -->
              <el-option label="银行家舍入" value="HALF_EVEN"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公式预览">
            <el-input type="textarea" v-model="formula.calFormulaDes"></el-input>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6">
          <p>计算表</p>
          <!-- 列信息 -->
          <el-tree
            class="filter-tree"
            :data="data"
            :props="defaultProps"
            default-expand-all
            @node-click="handlerFormulaInTabTreeClick"
            ref="tabTree"
          >
          </el-tree>
        </el-col>
        <el-col :span="6">
          <p>计算列</p>
          <!-- 列信息 -->
          <el-tree
            class="filter-tree"
            :data="colTreeData"
            :props="defaultProps"
            default-expand-all
            @node-click="handlerColTreeClick"
            ref="colTree"
          >
          </el-tree>
        </el-col>
        <el-col :span="12">
          <p>操作符</p>
          <el-row>
            <!-- 计算器 点击tag需要获取到tag的值 ? -->
            <el-button type="primary" size="mini" plain @click="handleOpClick('1')">1</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('2')">2</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('3')">3</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('+')">+</el-button>
          </el-row>
          <el-row>
            <el-button type="primary" size="mini" plain @click="handleOpClick('4')">4</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('5')">5</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('6')">6</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('-')">-</el-button>
          </el-row>
          <el-row>
            <el-button type="primary" size="mini" plain @click="handleOpClick('7')">7</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('8')">8</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('9')">9</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('*')">*</el-button>
          </el-row>

          <el-row>
            <el-button type="primary" size="mini" plain @click="handleOpClick('0')">0</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('.')">.</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('/')">/</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('del')">删</el-button>
          </el-row>

          <el-row>
            <el-button type="primary" size="mini" plain @click="handleOpClick('(')">(</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick(')')">)</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('reset')">置</el-button>
            <el-button type="primary" size="mini" plain @click="handleOpClick('save')">存</el-button>
          </el-row>
        </el-col>
      </el-row>
    </el-dialog>
    <!-- 表内公式结束 -->
  </el-row>
</template>
<script>
import axios from 'axios';
import qs from 'qs';

const sysCollColsApiUrl = 'api/sys-collect-cols';

const sysCollTabsApiUrl = 'api/sys-collect-tabs';

// 公式接口
const sysFormulaTabApiUrl = 'api/sys-formula-tabs';

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
      this.treeSelected = data;
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
      //1. 获取左侧选中的表信息
      //2. 选中表信息填充后增加行
      this.tableData.push({ tabId: this.treeSelected.id });
    },
    saveData() {
      // 保存列表数据
      axios
        .post(sysCollColsApiUrl + '/save/update', this.tableData)
        .then(res => {
          console.log('保存成功', res);
          alert('保存成功');
        })
        .catch(err => {
          console.log(err);
        });
    },
    initTableData(treeData) {
      // 根据表信息查对应的采集表列信息
      axios.get(sysCollColsApiUrl + '/tab-id/' + treeData.id).then(res => {
        const response = res.data;
        this.tableData = response;

        //初始化列tree信息
        this.colTreeData = [];
        for (const key in this.tableData) {
          if (Object.hasOwnProperty.call(this.tableData, key)) {
            const element = this.tableData[key];
            this.colTreeData.push({ id: element.colEname, label: element.colCname });
          }
        }
      });
    },
    initColTreeData(treeData) {
      // 表内公式界面, 根据表信息查对应的采集表列信息树
      axios.get(sysCollColsApiUrl + '/tab-id/' + treeData.id).then(res => {
        const response = res.data;

        //初始化列tree信息
        this.colTreeData = [];
        for (const key in response) {
          if (Object.hasOwnProperty.call(this.tableData, key)) {
            const element = this.tableData[key];
            this.colTreeData.push({ id: element.colEname, label: element.colCname });
          }
        }
      });
    },
    initCollTabsTreeData() {
      // 获取左侧采集表选择信息
      axios.get(sysCollTabsApiUrl + '/left-tree').then(res => {
        const response = res.data;
        this.data = response;
      });
    },
    toFormulaIn(index, row) {
      // 弹出表内公式配置界面
      // 设置公式的表和列信息(公式绑定到哪个表的哪个列上)
      this.formula.tabEname = this.treeSelected.label.split('-')[0];
      this.formula.colEname = row.colEname;

      // 根据 表英文名 和 列英文名 获取公式配置信息
      axios.get(sysFormulaTabApiUrl + '/tab/' + this.formula.tabEname + '/col/' + this.formula.colEname).then(res => {
        const response = res.data;
        this.formula = response;
        // 有没有数据都填充下tabEname和colEname保存后下次就有了
        this.formula.tabEname = this.treeSelected.label.split('-')[0];
        this.formula.colEname = row.colEname;
        this.dialogFormulaInVisible = true;
      });
    },
    initSourceTreeData() {
      // 查询基础数据信息
      axios.get(eleUnionsApiUrl + '/left-tree').then(res => {
        const response = res.data;
        this.sourceData = response;
        console.log('mapping树', this.sourceData);
      });
    },
    handleOpClick(operate) {
      if ('reset' === operate) {
        this.formula.calFormula = '';
        this.formula.calFormulaDes = '';
      } else if ('del' === operate) {
        // TODO 这里需要去掉一个单词或者符号
        this.formula.calFormula = '';
        this.formula.calFormulaDes = '';
      } else if ('save' === operate) {
        // 保存当前列的公式信息
        console.log('当前列的公式信息', this.formula);
        axios
          .post(sysFormulaTabApiUrl, this.formula)
          .then(res => {
            console.log('保存成功', res);
            alert('保存成功');
            this.dialogFormulaInVisible = false;
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        // 填充textarea
        this.formula.calFormula += operate;
        // 这个用来显示
        this.formula.calFormulaDes += operate;
      }
    },
    handlerColTreeClick(data) {
      //如果选择了表, 则增加表.xx字段, 关联数据
      if (this.formulaTabTreeSelected.tabEname != '' && this.formulaTabTreeSelected.tabEname != this.treeSelected.tabEname) {
        // 后边条件表示, 选中表如果是自身，则不增加表前缀, 减少数据长度和后台处理复杂度
        this.formula.calFormula += "#{['" + this.formulaTabTreeSelected.tabEname + '.' + data.id + "']}";
        // 这个用来显示
        this.formula.calFormulaDes += this.formulaTabTreeSelected.tabCname + '.' + data.label;
      } else {
        this.formula.calFormula += "#{['" + data.id + "']}";
        // 这个用来显示
        this.formula.calFormulaDes += data.label;
      }
    },
    handlerFormulaInTabTreeClick(data) {
      // 表内公式, 配置界面表信息点击
      //1. 根据选择的表信息, 查询列信息树
      this.formulaTabTreeSelected = data;
      this.initColTreeData(data);
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
      // 选中信息
      treeSelected: {},
      trueFalse: [
        { value: true, code: true, name: '是' },
        { value: false, code: false, name: '否' },
      ],
      colType: [
        // tree, select, input
        { value: 'tree', code: 'tree', name: '树型' },
        { value: 'select', code: 'select', name: '下拉选择' },
        { value: 'input', code: 'input', name: '文本框' },
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
      formulaTabTreeSelected: {},
    };
  },
  mounted() {
    this.initCollTabsTreeData();
    this.initSourceTreeData();
  },
};
</script>
