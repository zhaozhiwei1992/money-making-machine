<template>
  <div style="margin-top: 20px">
    <el-row>
      <el-col :span="4" :offset="17">
        <!-- <el-input v-model="search" placeholder="请输入搜索内容" style="margin-bottom: 15px"></el-input> -->
        <el-autocomplete
          class="inline-input"
          v-model="search"
          :fetch-suggestions="querySearch"
          placeholder="请输入搜素内容"
          :trigger-on-focus="false"
          @select="handleSelect"
        ></el-autocomplete>
      </el-col>
      <el-col :span="3">
        <el-button type="primary" icon="el-icon-search" @click="handleClick()">搜索</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-table :ref="innercomponentid" highlight-current-row :data="tableData" class="uitable">
          <el-table-column type="selection" width="40" align="center"></el-table-column>
          <el-table-column v-for="(col, index) in cols" :key="index" :prop="col.code" :label="col.name">
            <!-- 调整可编辑列表开始 -->
            <!-- 设计思路, 先之支持下拉, 输入, 树形, 日期 -->
            <!-- //根据不同类型显示不同的输入框 可以是下拉选择 /按钮 /输入框 还可以自己定义更多 -->
            <template slot-scope="scope">
              <!-- 非编辑直接显示 -->
              <span v-if="col.isedit == false">{{ scope.row[col.code] }}</span>
              <!-- 可编辑场景下特殊处理 -->
              <el-input
                v-else-if="col.isedit == true && col.type === 'input'"
                size="small"
                v-model="scope.row[col.code]"
                placeholder="请输入内容"
                @change="handleEdit(scope.$index, scope.row)"
              />
              <el-select
                v-else-if="col.type === 'select'"
                size="small"
                v-model="scope.row[col.code]"
                @change="handleEdit(scope.$index, scope.row)"
                placeholder="请选择内容"
                value=""
              >
                <el-option v-for="option in col.mapping" :value="option.code" :key="option.code" :label="option.name" />
              </el-select>
              <el-button v-else-if="col.type === 'btn'" @click="onSelected(scope.$index, scope.row)" type="primary" size="small">
                {{ col.text }}
              </el-button>
              <el-date-picker
                v-else-if="col.type === 'date'"
                v-model="scope.row[col.code]"
                :name="col.code"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="请选择日期"
              ></el-date-picker>
              <el-cascader
                v-else-if="col.type === 'cascader'"
                v-model="scope.row[col.code]"
                :options="col.mapping"
                :show-all-levels="false"
                :props="{ expandTrigger: 'hover' }"
                clearable
              ></el-cascader>
            </template>
            <!-- 调整可编辑列表结束, 如果没有此类需求, 删掉开头结尾内容即可 -->
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row type="flex" justify="end">
      <!-- 使用flex直接布局, 否则内部组建动态无法指定精确值 -->
      <!-- <el-col :span="8" :offset="10"> -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50, 100, 200, 500, 1000]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
      <!-- </el-col> -->
    </el-row>
  </div>
</template>

<script>
import axios from 'axios';

const baseApiUrl = 'api/ui-tables';

export default {
  props: ['componentid', 'menuid', 'tableData', 'total'],
  data() {
    return {
      search: '',
      cols: [],
      innercomponentid: 'singleTable',
      currentPage: 1,
      pageSize: 5,
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
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.$parent.$parent.tableSizeChange(this.currentPage, this.pageSize);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.$parent.$parent.tableCurrentChange(this.currentPage, this.pageSize);
    },
    // 快速查询组件选中
    querySearch(queryString, cb) {
      // 1. 获取列表区列配置信息
      var tableCols = this.cols;

      // 2. 数据拼装为 col | queryString形式, 如显示 性别 | 2, 实际后台查询 sex:2
      // console.log("列信息", tableCols);
      var queryView = [];
      tableCols.forEach(colObj => {
        queryView.push({ value: colObj.name + ' | ' + queryString, realValue: colObj.code + ':' + queryString });
      });

      // 调用 callback 返回建议列表的数据
      cb(queryView);
    },
    handleSelect(item) {
      // console.log('选中的item', item);
      this.search = item.realValue;
      this.handleClick();
    },
    handleClick() {
      // 父组件实现快速查询方法
      this.$parent.$parent.fastQuery(this.search);
    },
    handleEdit(index, row) {
      console.log('列表编辑', index, row);
      // this.$emit('handleEdit', { index: index, row: row });
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
