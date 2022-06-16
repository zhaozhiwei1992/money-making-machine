<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <p>采集表信息</p>
      <el-table :data="tableData" style="width: 100%" max-height="500">
        <el-table-column label="表中文名" prop="tabCname"> </el-table-column>
        <el-table-column label="表英文名" prop="tabEname"> </el-table-column>
        <el-table-column label="启用" prop="enable"> </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">增加行</el-button>
        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除行</el-button>
        <el-button size="mini" @click="handleSave(scope.$index, scope.row)">保存</el-button>
      </div>
    </el-col>
    <el-col :span="18">
      <div>
        <p>采集表列信息</p>
        <el-table :data="colData" style="width: 100%" max-height="500">
          <el-table-column label="列中文名" prop="colCname"> </el-table-column>
          <el-table-column label="列英文名" prop="colEname"> </el-table-column>
          <el-table-column label="字段类型" prop="type"> </el-table-column>
          <el-table-column label="可编辑" prop="isEdit"> </el-table-column>
          <el-table-column label="数据源" prop="source"> </el-table-column>
        </el-table>
        <div style="margin-top: 20px">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">增加行</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除行</el-button>
        </div>
      </div>
    </el-col>
  </el-row>
</template>
<script>
export default {
  data() {
    return {
      tableData: [
        {
          tabCname: '表1',
          enable: '是',
        },
        {
          tabCname: '表2',
          enable: '否',
        },
        {
          tabCname: '表3',
          enable: '是',
        },
      ],
      search: '',
      colData: [
        {
          colCname: '列1',
          enable: '是',
        },
        {
          colCname: '列2',
          enable: '否',
        },
        {
          colCname: '列3',
          enable: '是',
        },
      ],
    };
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
  },
};
</script>
