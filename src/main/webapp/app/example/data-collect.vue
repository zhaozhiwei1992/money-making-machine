<template>
  <div>
    <ui-view v-bind:menuid="dymenuid" ref="mainRef"></ui-view>
    <!-- 子组件通过 that.$emit('closeUpload'); 触发父组件的closeUpload方法-->
    <upload :uploadUrl="excelImportUrl" :upLoadVisible="dialogExcelImportVisible" @closeUpload="closeUpload"></upload>
  </div>
</template>

<script>
import View from '@/core/ui/view.vue';
import Upload from '@/core/upload/upload.vue';

import axios from 'axios';
import qs from 'qs';

// 采集表界面
const baseApiUrl = 'api/data-collect';

export default {
  data() {
    return {
      // 该菜单主要用作测试
      dymenuid: '36',
      tableData: [],
      dialogExcelImportVisible: false,
      excelImportUrl: baseApiUrl + '/import',
    };
  },
  components: {
    'ui-view': View,
    upload: Upload,
  },
  mounted() {
    this.initTableData();
  },
  methods: {
    initTableData() {
      axios.get(baseApiUrl).then(res => {
        let response = res.data;
        console.log('获取采集表信息, {}', response);
        this.tableData = response;
        this.$refs.mainRef.setTableDatas('singleTable', this.tableData);
      });
    },
    save() {
      // 可编辑列表数据保存
      console.log('采集表保存信息: ', this.tableData);
      axios
        .post(baseApiUrl, this.tableData)
        .then(res => {
          console.log('保存成功', res);
          alert('保存成功');
          this.tableData = res.data;
        })
        .catch(err => {
          console.log(err);
        });
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
        this.tableDatas = response;
        alert('审核成功, 请刷新页面');
      });
    },
    fastQuery(obj) {
      alert('快速查询, 查询参数' + obj);
    },
    addRow() {
      this.tableData.push({});
      this.$refs.mainRef.setTableDatas('singleTable', this.tableData);
    },
    delRow() {
      // 1. 获取列表选中行
      let selectedData = this.$refs.mainRef.$refs.uitable[0].$refs.uitable.selection;
      // 2. 不存在id, 直接删除tableData中数据
      selectedData.forEach((selected, i) => {
        this.tableData = this.tableData.filter(function (item, index) {
          // 根据eleCode来删除数据
          return item.id != selected.id;
        });
      });
      this.$refs.mainRef.setTableDatas('singleTable', this.tableData);
    },
    import() {
      this.dialogExcelImportVisible = true;
    },
    closeUpload() {
      this.initTableData();
      this.dialogExcelImportVisible = false;
    },
    exportTemplate() {
      // 上述直接打开url方式不行, 没有header里的认证信息
      axios({
        method: 'get',
        url: baseApiUrl + '/exportTemplate',
        data: {},
        responseType: 'blob',
      })
        .then(res => {
          console.log('下载信息', res);
          let url = window.URL.createObjectURL(new Blob([res.data]));
          let a = document.createElement('a');
          a.style.display = 'none';
          a.href = url;
          a.setAttribute('download', 'demo采集表导入模板.xlsx');
          document.body.appendChild(a);
          a.click(); //执行下载
          window.URL.revokeObjectURL(a.href);
          document.body.removeChild(a);
        })
        .catch(error => {
          console.log(error);
        });
    },
    export() {
      // window.open(baseApiUrl + '/export');
      // 上述直接打开url方式不行, 没有header里的认证信息
      axios({
        method: 'get',
        url: baseApiUrl + '/export',
        data: {},
        responseType: 'blob',
      })
        .then(res => {
          console.log('下载信息', res);
          let url = window.URL.createObjectURL(new Blob([res.data]));
          let a = document.createElement('a');
          a.style.display = 'none';
          a.href = url;
          a.setAttribute('download', 'demo采集表数据导出.xlsx');
          document.body.appendChild(a);
          a.click(); //执行下载
          window.URL.revokeObjectURL(a.href);
          document.body.removeChild(a);
        })
        .catch(error => {
          console.log(error);
        });
    },
    handlerExcelImportSuccess() {
      alert('导入成功');
    },
  },
};
</script>
