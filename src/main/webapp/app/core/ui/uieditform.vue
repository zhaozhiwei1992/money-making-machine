<template>
  <el-row>
    <el-col>
      <el-form
        :model="fieldObj"
        ref="uieditform"
        label-width="180px"
        :label-position="labelPosition"
        :inline="true"
        class="demo-form-inline"
      >
        <template v-for="(item, index) of fieldArray">
          <template v-if="item.type === 'input'">
            <el-form-item :key="index" :label="item.name">
              <el-input v-model="fieldObj[item.code]" :max="item.fieldLength" :placeholder="item.placeholder" show-word-limit></el-input>
            </el-form-item>
          </template>
          <template v-if="item.type === 'textarea'">
            <el-form-item :key="index" :label="item.name">
              <el-input
                type="textarea"
                rows="4"
                :placeholder="item.placeholder"
                v-model="fieldObj[item.code]"
                :maxlength="item.fieldLength"
                show-word-limit
              ></el-input>
            </el-form-item>
          </template>
          <template v-if="item.type === 'date'">
            <el-form-item :key="index" :prop="item.code" :label="item.name">
              <el-date-picker
                v-model="fieldObj[item.code]"
                :name="item.code"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                :placeholder="item.placeholder"
              ></el-date-picker>
            </el-form-item>
          </template>
          <template v-if="item.type === 'select'">
            <el-form-item :key="index" :label="item.name">
              <el-select v-model="fieldObj[item.code]" :placeholder="item.placeholderribe">
                <el-option v-for="items in item.mapping" :key="items.name" :label="items.name" :value="items.value"> </el-option>
              </el-select>
            </el-form-item>
          </template>
          <template v-if="item.type === 'cascader'">
            <el-form-item :key="index" :label="item.name">
              <el-cascader
                v-model="fieldObj[item.code]"
                :options="item.mapping"
                :props="{ expandTrigger: 'hover' }"
                @change="handleCascaderChange"
              ></el-cascader>
            </el-form-item>
          </template>
          <template v-if="item.type === 'radio'">
            <el-form-item :key="index" :label="item.name">
              <template v-for="(child, index) in item.mapping">
                <el-radio :key="index" v-model="fieldObj[item.code]" :label="child.value">{{ child.name }}</el-radio>
              </template>
            </el-form-item>
          </template>
          <template v-if="item.type === 'checkbox'">
            <el-form-item :key="index" :label="item.name">
              <el-checkbox-group v-model="fieldObj[item.code]">
                <template v-for="(child, index) of item.mapping">
                  <el-checkbox :key="index" :label="child.name"></el-checkbox>
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

const baseApiUrl = 'api/ui-editforms';

export default {
  props: ['menuid'],
  data() {
    return {
      labelPosition: 'left',
      // 表单字段集合
      fieldArray: [],
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
      // 通过菜单id获取编辑区配置信息
      if (!!this.menuid) {
        axios
          .get(baseApiUrl + '/menu/' + this.menuid)
          .then(res => {
            let response = res.data;
            console.log(response);
            this.fieldArray = response;
            for (let i = 0; i < response.length; i++) {
              let item = response[i];
              if (item.type === 'checkbox') {
                this.$set(this.fieldObj, item.code, []);
              } else {
                // 这里可以设置配置默认值
                console.log(this.fieldObj);
                this.$set(this.fieldObj, item.code, item.showValue == undefined ? null : item.showValue);
              }
            }
          })
          .catch(err => {
            console.log('异常信息: ' + err);
          });
      }
    },
    handleCascaderChange(value) {
      // 级联选择器变更, 留个入口
      console.log(value);
    },
  },
};
</script>

<style scoped></style>
