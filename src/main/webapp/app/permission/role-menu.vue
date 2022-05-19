<template>
  <el-row>
    <h2 class="jh-entity-heading" data-cy="menuDetailsHeading">
      <span>角色菜单维护</span>
    </h2>
    <el-button type="primary" @click="save()">保存</el-button>
    <el-col :span="6">
      <div class="grid-content bg-purple">
        <el-tree :data="role_data" show-checkbox node-key="label" :default-expand-all="true" ref="roles"> </el-tree>
      </div>
    </el-col>
    <el-col :span="18"
      ><div class="grid-content bg-purple-light">
        <el-tree :data="menu_data" show-checkbox node-key="id" :default-expanded-keys="[2]" :accordion="true" ref="menus"> </el-tree></div
    ></el-col>
  </el-row>
</template>

<script>
import axios from 'axios';

const roleBaseApiUrl = 'api/roles';
const menuBaseApiUrl = 'api/menus';

export default {
  data() {
    return {
      multipleSelection: [],
      role_data: [
        {
          id: 0,
          label: '全部',
          children: [
            // {
            //   id: 3,
            //   label: '二级 2-1',
            // },
            // {
            //   id: 2,
            //   label: '二级 2-2',
            // },
          ],
        },
      ],
      menu_data: [
        {
          id: 0,
          label: '全部',
          children: [
            // {
            //   id: 3,
            //   label: '二级 2-1',
            // },
            // {
            //   id: 2,
            //   label: '二级 2-2',
            // },
          ],
        },
      ],
      defaultProps: {
        children: 'children',
        label: 'label',
      },
    };
  },
  mounted() {
    this.initRoleDatas();
    this.initMenuDatas();
  },
  methods: {
    initRoleDatas() {
      axios.get(roleBaseApiUrl + '/tree').then(res => {
        const response = res.data;
        console.log('角色树: {}', response);
        this.role_data[0].children = response;
      });
    },
    initMenuDatas() {
      axios.get(menuBaseApiUrl + '/tree').then(res => {
        const response = res.data;
        console.log('菜单树: {}', response);
        this.menu_data[0].children = response;
      });
    },
    save() {
      // 1. 获取角色和菜单选中情况
      console.log(this.$refs.roles.getCheckedKeys());
      console.log(this.$refs.menus.getCheckedKeys());
      // 2. 调用后台方法保存, 先删后插
    },
  },
};
</script>
