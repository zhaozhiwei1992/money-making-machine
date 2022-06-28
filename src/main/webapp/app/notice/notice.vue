<template>
  <el-col :span="24">
    <div style="margin-top: 20px">
      <el-button type="primary" @click="addOrUpdate()">新增修改</el-button>
      <el-button type="primary" @click="del()">删除</el-button>
      <el-button type="primary" @click="sendNotice()">立即发布</el-button>
    </div>
    <el-table ref="singleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="urgent" label="紧急程度" width="160"> </el-table-column>
      <el-table-column prop="title" label="标题" width="160"> </el-table-column>
      <el-table-column prop="creater" label="发送人" width="160"> </el-table-column>
      <el-table-column prop="notiType" label="通知类型" width="160"> </el-table-column>
      <el-table-column prop="recType" label="接收类型" width="160"> </el-table-column>
      <el-table-column prop="receiver" label="接收对象" width="160"> </el-table-column>
      <el-table-column prop="createTime" label="发送时间" width="160"> </el-table-column>
    </el-table>

    <!-- 新增或者修改界面 -->
    <el-dialog title="新增/修改" :visible.sync="addUpdateDialogFormVisible">
      <el-form :model="formObj" label-width="80px">
        <el-form-item label="通知类型">
          <el-radio-group v-model="formObj.notiType">
            <el-radio :label="1">通知</el-radio>
            <el-radio :label="2">通知公告</el-radio>
            <el-radio :label="3">规章制度</el-radio>
            <el-radio :label="4">政策文件</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="通知对象">
          <el-radio-group v-model="formObj.recType">
            <el-radio :label="1">所有用户</el-radio>
            <el-radio :label="2">指定用户</el-radio>
            <el-radio :label="3">角色</el-radio>
            <el-radio :label="4">单位</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="接收对象">
          <el-input v-model="formObj.receiver" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="紧急程度">
          <el-radio-group v-model="formObj.urgent">
            <el-radio :label="true">是</el-radio>
            <el-radio :label="false">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="通知标题">
          <el-input v-model="formObj.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="通知内容">
          <el-input type="textarea" v-model="formObj.content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addUpdateDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
  </el-col>
</template>

<script>
import axios from 'axios';

const baseApiUrl = 'api/sys-notices';

export default {
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      addUpdateDialogFormVisible: false,
      formObj: {
        notiType: '',
        recType: '',
        receiver: '',
        urgent: '',
        title: '',
        content: '',
      },
    };
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      // 根据id查询基础要素信息
      axios.get(baseApiUrl).then(res => {
        const response = res.data;
        this.tableData = response;
      });
    },
    addOrUpdate() {
      // 打开新增修改的界面dialog
      this.addUpdateDialogFormVisible = true;
      if (this.multipleSelection.length > 0) {
        this.formObj = this.multipleSelection[0];
      }
    },
    save() {
      // 保存数据
      axios
        .post(baseApiUrl, this.formObj)
        .then(res => {
          console.log('保存成功', res);
          this.$alert('保存成功', '提示', {
            confirmButtonText: '确定',
            callback: action => {
              this.initTableData();
            },
          });
        })
        .catch(err => {
          console.log(err);
        });
      this.addUpdateDialogFormVisible = false;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    del() {
      console.log('删除的数据', this.multipleSelection);
      axios
        .post(baseApiUrl + '/del', this.multipleSelection)
        .then(res => {
          console.log('删除成功', res);
          this.$alert('删除成功', '提示', {
            confirmButtonText: '确定',
            callback: action => {
              this.initTableData();
            },
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    sendNotice() {
      // 立即发放通知
      axios
        .post(baseApiUrl + '/send/notice', this.multipleSelection)
        .then(res => {
          console.log('发放成功', res);
          this.$alert('发放成功', '提示', {
            confirmButtonText: '确定',
            callback: action => {
              this.$message({
                type: 'info',
                message: `action: ${action}`,
              });
            },
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
};
</script>
