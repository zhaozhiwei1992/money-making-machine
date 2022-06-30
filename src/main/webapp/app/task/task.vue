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
      <el-table-column prop="enable" label="是否启用" :formatter="formatter" width="160"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button @click.native.prevent="logs(scope.$index, tableData)" type="text" size="small"> 日志查看 </el-button>
        </template>
      </el-table-column>
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

    <!-- 表达式配置弹出界面 -->
    <el-dialog title="表达式配置" :visible.sync="genCronExpressionVisible">
      <el-card class="box-card">
        <el-tabs v-model="activeName" type="card" @tab-click="handleTabClick">
          <el-tab-pane label="秒" name="second">
            <CrontabSecond @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronsecond" />
          </el-tab-pane>

          <el-tab-pane label="分钟" name="min">
            <CrontabMin @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronmin" />
          </el-tab-pane>

          <el-tab-pane label="小时" name="hour">
            <CrontabHour @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronhour" />
          </el-tab-pane>

          <el-tab-pane label="日" name="day">
            <CrontabDay @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronday" />
          </el-tab-pane>

          <el-tab-pane label="月" name="month">
            <CrontabMonth @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronmonth" />
          </el-tab-pane>

          <el-tab-pane label="周" name="week">
            <CrontabWeek @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronweek" />
          </el-tab-pane>

          <el-tab-pane label="年" name="year">
            <CrontabYear @update="updateCrontabValue" :check="checkNumber" :cron="crontabValueObj" ref="cronyear" />
          </el-tab-pane>
        </el-tabs>
      </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>表达式</span>
        </div>
        <el-row :gutter="40">
          <el-col v-for="item of tabTitles" :span="2" :key="item">{{ item }}</el-col>
          <el-col :span="10">Cron表达式</el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col v-for="(value, key) in crontabValueObj" :span="2" :key="key">
            <el-input v-model="crontabValueObj[key]" :disabled="true"></el-input>
          </el-col>
          <el-col :span="10">
            <el-input v-model="crontabValueString" :disabled="true"></el-input>
          </el-col>
        </el-row>
      </el-card>

      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <CrontabResult :ex="crontabValueString"></CrontabResult>
        </div>
      </el-card>

      <span slot="footer" class="dialog-footer">
        <el-button size="small" type="primary" @click="submitFill">确定</el-button>
        <el-button size="small" type="warning" @click="clearCron">重置</el-button>
        <el-button size="small" @click="genCronExpressionVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </el-col>
</template>

<script>
import axios from 'axios';

import CrontabSecond from './second.vue';
import CrontabMin from './min.vue';
import CrontabHour from './hour.vue';
import CrontabDay from './day.vue';
import CrontabMonth from './month.vue';
import CrontabWeek from './week.vue';
import CrontabYear from './year.vue';
import CrontabResult from './result.vue';

const baseApiUrl = 'api/task-params';

export default {
  components: {
    CrontabSecond,
    CrontabMin,
    CrontabHour,
    CrontabDay,
    CrontabMonth,
    CrontabWeek,
    CrontabYear,
    CrontabResult,
  },
  data() {
    return {
      tabTitles: ['秒', '分钟', '小时', '日', '月', '周', '年'],
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
      crontabValueObj: {
        second: '*',
        min: '*',
        hour: '*',
        day: '*',
        month: '*',
        week: '?',
        year: '',
      },
    };
  },
  mounted() {
    this.initTableData();
  },
  computed: {
    // 计算 配置结果
    crontabValueString: function () {
      let obj = this.crontabValueObj;
      let str =
        obj.second +
        ' ' +
        obj.min +
        ' ' +
        obj.hour +
        ' ' +
        obj.day +
        ' ' +
        obj.month +
        ' ' +
        obj.week +
        (obj.year == '' ? '' : ' ' + obj.year);
      return str;
    },
  },
  methods: {
    initTableData() {
      // 根据id查询基础要素信息
      axios.get(baseApiUrl).then(res => {
        const response = res.data;
        console.log('定时任务配置: ', response);
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
            callback: action => {
              this.initTableData();
            },
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
            callback: action => {
              this.initTableData();
            },
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
    handleTabClick(tab, event) {
      console.log('标签点击', tab, event);
    },
    // 由子组件触发，更改表达式组成的字段值
    updateCrontabValue(name, value, from) {
      'updateCrontabValue', name, value, from;
      this.crontabValueObj[name] = value;
      if (from && from !== name) {
        console.log(`来自组件 ${from} 改变了 ${name} ${value}`);
        this.changeRadio(name, value);
      }
    },
    // 赋值到组件
    changeRadio(name, value) {
      let arr = ['second', 'min', 'hour', 'month'],
        refName = 'cron' + name,
        insValue;

      if (!this.$refs[refName]) return;

      if (arr.includes(name)) {
        if (value === '*') {
          insValue = 1;
        } else if (value.indexOf('-') > -1) {
          let indexArr = value.split('-');
          isNaN(indexArr[0]) ? (this.$refs[refName].cycle01 = 0) : (this.$refs[refName].cycle01 = indexArr[0]);
          this.$refs[refName].cycle02 = indexArr[1];
          insValue = 2;
        } else if (value.indexOf('/') > -1) {
          let indexArr = value.split('/');
          isNaN(indexArr[0]) ? (this.$refs[refName].average01 = 0) : (this.$refs[refName].average01 = indexArr[0]);
          this.$refs[refName].average02 = indexArr[1];
          insValue = 3;
        } else {
          insValue = 4;
          this.$refs[refName].checkboxList = value.split(',');
        }
      } else if (name == 'day') {
        if (value === '*') {
          insValue = 1;
        } else if (value == '?') {
          insValue = 2;
        } else if (value.indexOf('-') > -1) {
          let indexArr = value.split('-');
          isNaN(indexArr[0]) ? (this.$refs[refName].cycle01 = 0) : (this.$refs[refName].cycle01 = indexArr[0]);
          this.$refs[refName].cycle02 = indexArr[1];
          insValue = 3;
        } else if (value.indexOf('/') > -1) {
          let indexArr = value.split('/');
          isNaN(indexArr[0]) ? (this.$refs[refName].average01 = 0) : (this.$refs[refName].average01 = indexArr[0]);
          this.$refs[refName].average02 = indexArr[1];
          insValue = 4;
        } else if (value.indexOf('W') > -1) {
          let indexArr = value.split('W');
          isNaN(indexArr[0]) ? (this.$refs[refName].workday = 0) : (this.$refs[refName].workday = indexArr[0]);
          insValue = 5;
        } else if (value === 'L') {
          insValue = 6;
        } else {
          this.$refs[refName].checkboxList = value.split(',');
          insValue = 7;
        }
      } else if (name == 'week') {
        if (value === '*') {
          insValue = 1;
        } else if (value == '?') {
          insValue = 2;
        } else if (value.indexOf('-') > -1) {
          let indexArr = value.split('-');
          isNaN(indexArr[0]) ? (this.$refs[refName].cycle01 = 0) : (this.$refs[refName].cycle01 = indexArr[0]);
          this.$refs[refName].cycle02 = indexArr[1];
          insValue = 3;
        } else if (value.indexOf('#') > -1) {
          let indexArr = value.split('#');
          isNaN(indexArr[0]) ? (this.$refs[refName].average01 = 1) : (this.$refs[refName].average01 = indexArr[0]);
          this.$refs[refName].average02 = indexArr[1];
          insValue = 4;
        } else if (value.indexOf('L') > -1) {
          let indexArr = value.split('L');
          isNaN(indexArr[0]) ? (this.$refs[refName].weekday = 1) : (this.$refs[refName].weekday = indexArr[0]);
          insValue = 5;
        } else {
          this.$refs[refName].checkboxList = value.split(',');
          insValue = 6;
        }
      } else if (name == 'year') {
        if (value == '') {
          insValue = 1;
        } else if (value == '*') {
          insValue = 2;
        } else if (value.indexOf('-') > -1) {
          insValue = 3;
        } else if (value.indexOf('/') > -1) {
          insValue = 4;
        } else {
          this.$refs[refName].checkboxList = value.split(',');
          insValue = 5;
        }
      }
      this.$refs[refName].radioValue = insValue;
    },
    // 表单选项的子组件校验数字格式（通过-props传递）
    checkNumber(value, minLimit, maxLimit) {
      // 检查必须为整数
      value = Math.floor(value);
      if (value < minLimit) {
        value = minLimit;
      } else if (value > maxLimit) {
        value = maxLimit;
      }
      return value;
    },
    // 填充表达式
    submitFill() {
      this.formObj.cronExpression = this.crontabValueString;
      this.genCronExpressionVisible = false;
    },
    clearCron() {
      // 还原选择项
      this.crontabValueObj = {
        second: '*',
        min: '*',
        hour: '*',
        day: '*',
        month: '*',
        week: '?',
        year: '',
      };
      for (let j in this.crontabValueObj) {
        this.changeRadio(j, this.crontabValueObj[j]);
      }
    },
    formatter(row, column) {
      return row.enable === true ? '是' : '否';
    },
    logs(index, row) {
      // 查看某个定时任务日志信息
      console.log('查看日志', row);
    },
  },
};
</script>
