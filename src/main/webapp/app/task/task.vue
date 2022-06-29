<template>
  <el-col :span="24">
    <div style="margin-top: 20px">
      <el-button type="primary" @click="addOrUpdate()">新增修改</el-button>
      <el-button type="primary" @click="del()">删除</el-button>
      <el-button type="primary" @click="start()">启用</el-button>
      <el-button type="primary" @click="stop()">停用</el-button>
    </div>
    <el-table ref="singleTable" :data="tableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="name" label="任务名称" width="300"> </el-table-column>
      <el-table-column prop="cronExpression" label="表达式" width="350"> </el-table-column>
      <el-table-column prop="startClass" label="任务入口" width="400"> </el-table-column>
      <el-table-column prop="enable" label="是否启用" width="160"> </el-table-column>
    </el-table>

    <!-- 新增或者修改界面 -->
    <el-dialog title="新增/修改" :visible.sync="addUpdateDialogFormVisible">
      <el-form :model="formObj" label-width="80px">
        <el-form-item label="任务名称">
          <el-input v-model="formObj.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="18">
            <el-form-item label="表达式">
              <el-input v-model="formObj.cronExpression" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <!-- 这里紧跟着 横着来两按钮, 一个是表达式配置, 一个是预览 -->
            <el-button @click="genCronExpression">生成表达式</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="任务入口">
            <!-- 列出代码列表, 选择填入即可 -->
            <el-input v-model="formObj.startClass" autocomplete="off"></el-input>
          </el-form-item>
        </el-row>
        <el-switch
          style="display: block"
          v-model="formObj.enable"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="启用"
          inactive-text="停用"
        >
        </el-switch>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addUpdateDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="表达式配置" :visible.sync="genCronExpressionVisible">
      <el-card class="box-card">
        <el-tabs v-model="activeName" type="card" @tab-click="handleTabClick">
          <el-tab-pane label="秒" name="second">
            <div>
              <el-radio v-model="second" label="*">秒,允许的通配符[,-*/]</el-radio>
            </div>
            <div>
              <el-radio v-model="second" label="2"
                >周期从 <el-input-number size="small" v-model="num2"></el-input-number> -
                <el-input-number size="small" v-model="num2"></el-input-number>秒</el-radio
              >
            </div>
            <div>
              <el-radio v-model="second" label="3"
                >从<el-input-number size="small" v-model="num2"></el-input-number>秒开始, 每<el-input-number
                  size="small"
                  v-model="num2"
                ></el-input-number
                >秒执行一次</el-radio
              >
            </div>
            <div>
              <el-radio v-model="second" label="4"
                >指定
                <el-select v-model="value1" multiple placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                </el-select>
              </el-radio>
            </div>
          </el-tab-pane>
          <el-tab-pane label="分钟" name="minutes">分钟</el-tab-pane>
          <el-tab-pane label="小时" name="hour">小时</el-tab-pane>
          <el-tab-pane label="日" name="day">日</el-tab-pane>
          <el-tab-pane label="月" name="month">月</el-tab-pane>
          <el-tab-pane label="周" name="week">周</el-tab-pane>
          <el-tab-pane label="年" name="year">年</el-tab-pane>
        </el-tabs>
      </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>表达式</span>
        </div>
        <el-row :gutter="40">
          <el-col :span="2">秒</el-col>
          <el-col :span="2">分钟</el-col>
          <el-col :span="2">小时</el-col>
          <el-col :span="2">日</el-col>
          <el-col :span="2">月</el-col>
          <el-col :span="2">周</el-col>
          <el-col :span="2">年</el-col>
          <el-col :span="10">Cron表达式</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="2">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
          <el-col :span="10">
            <el-input v-model="second" :disabled="true"></el-input>
          </el-col>
        </el-row>
      </el-card>

      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>最近5次运行时间</span>
        </div>
      </el-card>
    </el-dialog>
  </el-col>
</template>

<script>
import axios from 'axios';

const baseApiUrl = 'api/task-params';

export default {
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      addUpdateDialogFormVisible: false,
      genCronExpressionVisible: false,
      formObj: {
        name: '',
        cronExpression: '',
        startClass: '',
        enable: false,
      },
      activeName: 'second',
      second: '',
      num2: '',
      options: [
        {
          value: '选项1',
          label: '黄金糕',
        },
        {
          value: '选项2',
          label: '双皮奶',
        },
        {
          value: '选项3',
          label: '蚵仔煎',
        },
        {
          value: '选项4',
          label: '龙须面',
        },
        {
          value: '选项5',
          label: '北京烤鸭',
        },
      ],
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
    start() {
      // 立即发放通知
      axios
        .post(baseApiUrl + '/start', this.multipleSelection)
        .then(res => {
          console.log('启用成功', res);
          this.$alert('启用成功', '提示', {
            confirmButtonText: '确定',
            callback: action => {},
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    stop() {
      // 立即发放通知
      axios
        .post(baseApiUrl + '/stop', this.multipleSelection)
        .then(res => {
          console.log('停用成功', res);
          this.$alert('停用成功', '提示', {
            confirmButtonText: '确定',
            callback: action => {},
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    genCronExpression() {
      // 表达式选择界面
      this.genCronExpressionVisible = true;
    },
    preview() {
      // 预览表达式执行规则
    },
    handleTabClick(tab, event) {
      console.log('标签点击', tab, event);
    },
  },
};
</script>
