<template>
  <div>
    <ui-view v-bind:menuid="dymenuid" ref="mainRef"></ui-view>
  </div>
</template>

<script>
import View from '@/core/ui/view.vue';

import axios from 'axios';
import qs from 'qs';

// 采集表界面
const baseApiUrl = 'api/data-collect';

export default {
  data() {
    return {
      // 该菜单主要用作测试
      dymenuid: '36',
      tabDatas: null,
    };
  },
  components: {
    'ui-view': View,
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      axios.get(baseApiUrl).then(res => {
        let response = res.data;
        console.log('获取采集表信息, {}', response);
        this.tabDatas = response;
        this.$refs.mainRef.setTableDatas('singleTable', this.tabDatas);
      });
    },
    save() {
      // 可编辑列表数据保存
    },
    audit() {
      console.log('请假审核方法');
      // 1. 获取列表选中数据
      let selectedDatas = this.$refs.mainRef.$refs.uitable[0].$refs.uitable.selection;
      console.log('列表选中数据, {}', selectedDatas);
      // 2. 多条审核
      let postData = qs.stringify(
        {
          idList: selectedDatas.map(item => item.id),
        },
        { arrayFormat: 'repeat' }
      );
      axios.post(baseApiUrl + '/audit/', postData).then(res => {
        console.log('审核后返回 {}', res);
        let response = res.data;
        this.tabDatas = response;
        alert('审核成功, 请刷新页面');
      });
    },

    fastQuery(obj) {
      alert('快速查询, 查询参数' + obj);
    },
  },
};
</script>
