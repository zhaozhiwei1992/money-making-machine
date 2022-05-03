<template>
  <el-row>
    <el-col>
      <el-form :model="fieldObj" ref="ruleForm" label-width="180px" :label-position="labelPosition" :inline="true" class="demo-form-inline">
        <template v-for="(item, index) of fieldArray">
          <template v-if="item.htmlElements === '输入框'">
            <el-form-item :label="item.showName">
              <el-input v-model="fieldObj[item.showName]" :max="item.fieldLength" :placeholder="item.desc" show-word-limit></el-input>
            </el-form-item>
          </template>
          <template v-if="item.htmlElements === '文本域'">
            <el-form-item :label="item.showName">
              <el-input
                type="textarea"
                rows="4"
                :placeholder="item.desc"
                v-model="fieldObj[item.showName]"
                :maxlength="item.fieldLength"
                show-word-limit
              ></el-input>
            </el-form-item>
          </template>
          <template v-if="item.htmlElements === '日历控件'">
            <el-form-item :prop="item.showName" :label="item.showName">
              <el-date-picker
                v-model="fieldObj[item.showName]"
                :name="item.showName"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                :placeholder="item.desc"
              ></el-date-picker>
            </el-form-item>
          </template>
          <template v-if="item.htmlElements === '下拉框'">
            <el-form-item :label="item.showName">
              <el-select v-model="fieldObj[item.showName]" :placeholder="item.describe">
                <el-option v-for="items in item.mapping" :key="items.name" :label="items.name" :value="items.value"> </el-option>
              </el-select>
            </el-form-item>
          </template>
          <template v-if="item.htmlElements === '单选框'">
            <el-form-item :label="item.showName">
              <template v-for="(child, index) in item.mapping">
                <el-radio v-model="fieldObj[item.showName]" :label="child.value">{{ child.name }}</el-radio>
              </template>
            </el-form-item>
          </template>
          <template v-if="item.htmlElements === '复选框'">
            <el-form-item :label="item.showName">
              <el-checkbox-group v-model="fieldObj[item.showName]">
                <template v-for="(child, index) of item.mapping">
                  <el-checkbox :label="child.name"></el-checkbox>
                </template>
              </el-checkbox-group>
            </el-form-item>
          </template>
        </template>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios';
export default {
  props: ['menuid'],
  data() {
    return {
      labelPosition: 'left',
      fieldArray: [], // 表单字段集合
      fieldObj: {},
    };
  },
  mounted() {
    this.initComponent();
  },
  methods: {
    initComponent() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取编辑区信息
      console.log('父级传入menuid为: ' + this.menuid);
      // 这里简单测试, menuid不为空才去加载
      axios.get('/json/uieditform.json').then(data => {
        let response = data.data.data;
        console.log(response);
        this.fieldArray = response;
        for (let i = 0; i < response.length; i++) {
          let item = response[i];
          if (item.htmlElements === '复选框') {
            this.$set(this.fieldObj, item.showName, []);
          } else {
            this.$set(this.fieldObj, item.showName, item.showValue);
          }
        }
      });
    },
  },
};
</script>

<style scoped></style>
