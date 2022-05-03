<template>
  <div style="margin-top: 20px">
    <el-row>
      <el-col>
        <el-button
          :type="item.type"
          plain
          v-for="(item, i) in buttonArray"
          :key="i"
          v-on:[eventName]="handleClick(item.click)"
          :size="item.size"
          class="right-btn"
          >{{ item.name }}</el-button
        >
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  props: ['menuid'],
  data() {
    return {
      // 按钮组
      buttonArray: [],
      eventName: 'click',
    };
  },
  mounted() {
    this.initComponent();
  },
  methods: {
    initComponent() {
      // 父页面传入菜单id, 这里根据菜单id自己去后台获取编辑区信息
      console.log('父级传入menuid为: ' + this.menuid);
      axios.get('/json/uitoolbutton.json').then(data => {
        let response = data.data.data;
        console.log(response);
        this.buttonArray = response;
      });
    },
    //动态绑定操作按钮的点击事件, 父页面实现方法
    handleClick(i) {
      let onClick = i;
      // this[onClick]();
      // 子组件里用$emit向父组件触发一个事件，父组件监听这个事件就行了。(不灵活,这里永不了)
      // this.$emit("'"+onClick+"'");
      // 在子组件中通过this.$parent.event来调用父组件的方法
      this.$parent.$parent[onClick]();
    },
  },
};
</script>

<style scoped>
.right-btn {
  float: right;
}
</style>
