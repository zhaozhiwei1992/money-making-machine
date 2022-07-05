<template>
  <el-row>
    <el-row>
      <h3>卡片配置界面</h3>
      <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-delete" @click="clear">清空</el-button>
      <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-download" @click="exportVue">导出vue</el-button>
      <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-document-copy" @click="copy">复制代码</el-button>
    </el-row>
    <el-row :gutter="20">
      <!-- 左边是自定义的一些组件, 缩略图 -->
      <el-col :span="6"
        ><div class="grid-content bg-purple">
          <p>组件区</p>
          <draggable v-model="leftArray" group="components" @start="drag = true" @end="drag = false">
            <!-- <div v-for="element in leftArray" :key="element.id">{{ element.name }}</div> -->
            <el-tag v-for="element in leftArray" :key="element.id" style="margin: 0 3px">{{ element.name }}</el-tag>
          </draggable>
        </div></el-col
      >
      <!-- 右边拖过来以后就展现 -->
      <el-col :span="18"
        ><div class="grid-content bg-purple" style="border-left: 1px solid #000">
          <p>显示区</p>
          <draggable v-model="rightArray" group="components" @start="drag = true" @end="drag = false">
            <!-- 动态加载组件 -->
            <component v-for="item in rightArray" :key="item.id" v-bind:is="item.id" v-bind:componentid="item.id"></component>
            <!-- 点击导出时,需要将rightArray部分生成一个可用的vue文件, 放到任意地方配置即可使用 -->
          </draggable>
        </div></el-col
      >
    </el-row>
  </el-row>
</template>

<script>
// 导入所有组件
// 以".vue"结尾
const allComponents = require.context('@/shared/components', false, /.*\.vue$/);
let res_components = { draggable, VueDragResize };
allComponents.keys().forEach(fileName => {
  let comp = allComponents(fileName);
  res_components[fileName.replace(/^\.\/(.*)\.\w+$/, '$1')] = comp.default;
});

console.log('res_components', res_components);

import draggable from 'vuedraggable';
import VueDragResize from 'vue-drag-resize';

export default {
  components: res_components,
  data() {
    return {
      colsOfRow: 3,
      width: 0,
      height: 0,
      top: 0,
      left: 0,
      // 左侧为系统提供的组件id,及名称
      leftArray: [
        { id: 'hello1', name: '组件1' },
        { id: 'hello2', name: '组件2' },
        { id: 'hello3', name: '组件3' },
        { id: 'calendar', name: '日历组件' },
        // 这里增加组件, 组件需要在app/shared/components目录下存在
      ],
      rightArray: [],
    };
  },

  methods: {
    clear() {
      this.rightArray.forEach(value => {
        this.leftArray.push(value);
      });
      this.rightArray = [];
    },
    exportVue() {
      //1. 生成vue代码
      //2. 导出
    },
    copy() {
      //1. 生成vue代码
      //2. 填充到系统剪贴板
    },
    generalVueCode() {
      // 根据渲染的组件导出vue文件, 字符串拼接
      // <template><el-col :span='18'> 这里放组件根据rightArray遍历产生 </el-col></template>
    },
  },
};
</script>
