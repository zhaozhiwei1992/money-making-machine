<template>
  <!-- 根据左侧组建名称, 动态生成右侧组件 -->
  <el-row :gutter="20">
    <!-- 左边是自定义的一些组件, 缩略图 -->
    <el-col :span="6"
      ><div class="grid-content bg-purple">
        <p>组件区</p>
        <draggable v-model="leftArray" group="components" @start="drag = true" @end="drag = false">
          <div v-for="element in leftArray" :key="element.id">{{ element.name }}</div>
        </draggable>
      </div></el-col
    >
    <!-- 右边拖过来以后就展现 -->
    <el-col :span="18"
      ><div class="grid-content bg-purple">
        <p>显示区</p>
        <draggable v-model="rightArray" group="components" @start="drag = true" @end="drag = false">
          <!-- <div v-for="element in rightArray" :key="element.id">{{ element.name }}</div> -->
          <!-- 动态加载组件 -->
          <component
            v-for="item in rightArray"
            :key="item.id"
            v-bind:is="item.id"
            v-bind:menuid="xx"
            v-bind:componentid="item.id"
          ></component>
        </draggable></div
    ></el-col>
  </el-row>
</template>

<script>
// 导入所有组件
// 以".vue"结尾
const allComponents = require.context('@/shared/components', false, /.*\.vue$/);
let res_components = { draggable };
allComponents.keys().forEach(fileName => {
  let comp = allComponents(fileName);
  res_components[fileName.replace(/^\.\/(.*)\.\w+$/, '$1')] = comp.default;
});

console.log('res_components', res_components);

import draggable from 'vuedraggable';

export default {
  components: res_components,
  data() {
    return {
      width: 0,
      height: 0,
      top: 0,
      left: 0,
      // 左侧为系统提供的组件id,及名称
      leftArray: [
        { id: 'hello1', name: '组件1' },
        { id: 'hello2', name: '组件2' },
        { id: 'hello3', name: '组件3' },
      ],
      rightArray: [{ id: 3, name: 'wangwu' }],
    };
  },

  methods: {
    resize(newRect) {
      this.width = newRect.width;
      this.height = newRect.height;
      this.top = newRect.top;
      this.left = newRect.left;
    },
  },
};
</script>
