<template>
  <div>
    <ui-view v-bind:menuid="dymenuid" ref="mainRef"></ui-view>
  </div>
</template>

<script>
import View from '@/core/ui/view.vue';

import axios from 'axios';
import qs from 'qs';

const baseApiUrl = 'api/examples';

export default {
  data() {
    return {
      // 该菜单主要用作测试
      dymenuid: '0000',
      tabDatas: null,
      page: 1,
      size: 5,
    };
  },
  components: {
    'ui-view': View,
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    // 自定义加载数据填充列表
    initTableData() {
      // 后台获取数据
      // 全部数据方式
      // axios.get(baseApiUrl).then(res => {
      //   let response = res.data;
      //   console.log('获取example信息, {}', response);
      //   this.tabDatas = response;
      //   this.$refs.mainRef.setTableDatas('singleTable', this.tabDatas);
      // });
      console.log('列表查询获取菜单id', this.$route.meta.menuid);
      // 分页查询方式
      axios.get(baseApiUrl + '/page/' + this.page + '/size/' + this.size).then(res => {
        let response = res.data;
        console.log('获取example信息, {}', response);
        this.tabDatas = response.content;
        this.$refs.mainRef.setTableDatas('singleTable', this.tabDatas, response.totalElements);
      });
    },
    setCurrent() {
      // 操作表格选中一行
      this.$refs.mainRef.setTableCurrentRow('singleTable', this.tabDatas[1]);
    },
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
          alert('保存成功, 刷新页面查看结果!');
        })
        .catch(err => {
          console.log(err);
        });
      // 3. 查询列表
      // this.query();
    },
    //编辑
    edit() {
      alert('父页面实现编辑');
    },
    //删除
    delete() {
      console.log('业务实现删除');
      // 1. 获取列表选中行
      let selectedDatas = this.$refs.mainRef.$refs.uitable[0].$refs.uitable.selection;
      console.log('列表选中数据, {}', selectedDatas);
      // 2. 删除单条
      // axios.delete(baseApiUrl + '/' + selectedDatas[0].id).then(res => {
      //   console.log('删除后返回 {}', res);
      //   alert('删除成功, 请刷新页面');
      // });

      // 批量删除
      let postData = qs.stringify(
        {
          idList: selectedDatas.map(item => item.id),
        },
        { arrayFormat: 'repeat' }
      );
      axios.post(baseApiUrl + '/delete/', postData).then(res => {
        console.log('删除后返回 {}', res);
        this.initTableData();
        alert('删除成功');
      });
    },
    fastQuery(obj) {
      console.log('快速查询, 查询参数' + obj);
      let url = baseApiUrl + '/search/' + obj;
      if (!!!obj) {
        url = baseApiUrl;
      }
      axios.get(url).then(res => {
        let response = res.data;
        console.log('获取example信息, {}', response);
        this.tabDatas = response;
        this.$refs.mainRef.setTableDatas('singleTable', this.tabDatas);
      });
    },
    // 分页测试
    tableCurrentChange(page, size) {
      console.log('父页面, 当前页', page, '每页条数', size);
      this.page = page;
      this.size = size;
      this.initTableData();
    },
    tableSizeChange(page, size) {
      console.log('父页面, 当前页', page, '每页条数', size);
      this.page = page;
      this.size = size;
      this.initTableData();
    },
    tabClick(tabValue) {
      console.log('当前选中页签', tabValue);
      // 根据选中页签过滤数据, 可以参考上边查询区等
    },
  },
};
</script>
