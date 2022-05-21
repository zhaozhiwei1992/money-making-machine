<template>
  <el-row>
    <el-row>
      <h2 class="jh-entity-heading" data-cy="menuDetailsHeading">
        <span>按钮权限分配</span>
      </h2>
    </el-row>
    <el-row type="flex" justify="end">
      <el-button type="primary" @click="save()">保存</el-button>
    </el-row>

    <el-col :span="8">
      <div class="grid-content bg-purple">
        <el-tree :data="role_data" show-checkbox node-key="label" :default-expand-all="true" ref="roles" @node-click="handleRoleNodeClick">
        </el-tree>
      </div>
    </el-col>
    <el-col :span="8"
      ><div class="grid-content bg-purple-light">
        <el-tree
          :data="menu_data"
          show-checkbox
          node-key="id"
          :default-expanded-keys="[2]"
          :accordion="true"
          ref="menus"
          @node-click="handleMenuNodeClick"
        >
        </el-tree></div
    ></el-col>

    <el-col :span="8"
      ><div class="grid-content bg-purple-light">
        <el-tree :data="tool_button_data" show-checkbox node-key="id" :default-expand-all="true" ref="toolbuttons"> </el-tree></div
    ></el-col>
  </el-row>
</template>

<script>
import axios from 'axios';
import qs from 'qs';

const roleBaseApiUrl = 'api/roles';
const menuBaseApiUrl = 'api/menus';
const baseApiUrl = 'api/role-menu-tool-buttons';

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
      tool_button_data: [
        {
          id: 0,
          label: '全部',
          children: [],
        },
      ],
      defaultProps: {
        children: 'children',
        label: 'label',
      },
      buttonSel: [],
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
      // 2. 调用后台方法保存, 先删后插
      let postData = qs.stringify(
        {
          roleIdList: this.$refs.roles.getCheckedKeys(),
          menuIdList: this.$refs.menus.getCheckedKeys(),
          toolButtonIdList: this.$refs.toolbuttons.getCheckedKeys(),
        },
        { arrayFormat: 'repeat' }
      );
      axios.post(baseApiUrl + '/save/', postData).then(res => {
        alert('保存成功');
      });
    },
    handleRoleNodeClick(data) {
      console.log('选中角色信息', data);
      this.$refs.roles.setCheckedKeys([data.label]);
      // 根据角色点击信息，重新渲染菜单选中信息
      axios.get(baseApiUrl + '/menu/by/role/' + data.label).then(res => {
        // 选中菜单
        this.$refs.menus.setCheckedKeys(res.data);
      });
    },
    handleMenuNodeClick(data) {
      this.$refs.menus.setCheckedKeys([data.id]);
      // 根据角色 + 菜单点击信息，重新渲染按钮选中信息
      axios.get(baseApiUrl + '/toolbutton/by/role/' + this.$refs.roles.getCheckedKeys()[0] + '/menu/' + data.id).then(res => {
        // 选中菜单
        const response = res.data;
        // 显示菜单下所有按钮
        this.tool_button_data[0].children = response.buttonTree;
        // 现有配置勾选
        console.log('按钮选中: {}', response.buttonSelect);
        // 这里注意搞点延时, 否则tree反应不过来不会选中
        setTimeout(() => {
          this.$refs.toolbuttons.setCheckedKeys(response.buttonSelect);
        }, 100);
      });
    },
  },
};
</script>
