<template>
  <div>
    <ui-view v-bind:menuid="dymenuid" ref="mainRef"></ui-view>
  </div>
</template>

<script>
import View from '@/core/ui/view.vue';

import axios from 'axios';
const baseApiUrl = 'api/leave-slips';

export default {
  data() {
    return {
      // 该菜单主要用作测试
      dymenuid: '5',
      tabDatas: null,
    };
  },
  components: {
    'ui-view': View,
  },
  methods: {
    save() {
      console.log('业务自定义实现保存方法');
      // 将编辑区录入数据, 写入到列表中
      // 1. 获取编辑区数据
      let editformObj = this.$refs.mainRef.$refs.uieditform[0].fieldObj;
      console.log('编辑区信息 {}', editformObj);
      // 2. 写入
      axios
        .post(`${baseApiUrl}`, editformObj)
        .then(res => {
          console.log('保存后返回 {}', res.data);
          editformObj = null;
          alert('保存成功, 请等待主管审核!');
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
};
</script>
