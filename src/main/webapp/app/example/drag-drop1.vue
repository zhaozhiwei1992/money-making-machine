<template>
  <div class="hello">
    <!-- <h1>这个目录下主要用于增加自定义组件配置</h1>
    <p>支持拖拽的方式, 构建成一个组件的聚合界面</p> -->
    <VueDragResize :isActive="true" :w="200" :h="200" v-on:resizing="resize" v-on:dragging="resize" :parentH="2000">
      <!-- <h3>Hello World!</h3>
      <p>{{ top }} х {{ left }}</p>
      <p>{{ width }} х {{ height }}</p> -->
      <el-card class="box-card" :style="{ height: height }">
        <div v-for="o in 4" :key="o" class="text item">
          {{ '列表内容 ' + o }}
        </div>
      </el-card>
    </VueDragResize>

    <VueDragResize :isActive="true" :w="200" :h="200" v-on:resizing="resize" v-on:dragging="resize">
      <!-- <h3>Hello World!</h3>
      <p>{{ top }} х {{ left }}</p>
      <p>{{ width }} х {{ height }}</p> -->
      <el-card class="box-card" style="height: 200">
        <div v-for="o in 4" :key="o" class="text item">
          {{ '列表内容 ' + o }}
        </div>
      </el-card>
    </VueDragResize>

    <!-- <draggable v-model="myArray" group="people" @start="drag = true" @end="drag = false">
      <div v-for="element in myArray" :key="element.id">{{ element.name }}</div>
    </draggable> -->
    <draggable v-model="myArray" group="people" @start="drag = true" @end="drag = false">
      <div v-for="element in myArray" :key="element.id">{{ element.name }}</div>
    </draggable>
  </div>
</template>

<script>
import VueDragResize from 'vue-drag-resize';
import draggable from 'vuedraggable';

export default {
  components: {
    VueDragResize,
    draggable,
  },

  data() {
    return {
      width: 0,
      height: 0,
      top: 0,
      left: 0,
      myArray: [
        { id: 1, name: 'zhangsan' },
        { id: 2, name: 'lisi' },
      ],
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
