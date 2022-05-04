<template>
  <el-row>
    <el-col>
      <el-form
        :model="fieldObj"
        :ref="innercomponentid"
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
          <!-- 查询区date一般为区间 -->
          <template v-if="item.type === 'daterange'">
            <el-form-item :key="index" :prop="item.code" :label="item.name">
              <el-date-picker
                v-model="fieldObj[item.code]"
                :name="item.code"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions"
              >
              </el-date-picker>
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
        </template>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios';

const baseApiUrl = 'api/ui-queryforms';

export default {
  props: ['componentid', 'menuid'],
  data() {
    return {
      innercomponentid: 'singleQueryform',
      labelPosition: 'left',
      // 表单字段集合
      fieldArray: [],
      fieldObj: {},
    };
  },
  mounted() {
    // 通过this.$refs.自己定义的componentid就可以获取到el-form对象
    this.innercomponentid = this.componentid;

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
              this.$set(this.fieldObj, item.code, item.showValue);
            }
          })
          .catch(err => {
            console.log('异常信息: ' + err);
          });
      }
    },
  },
};
</script>

<style scoped></style>
