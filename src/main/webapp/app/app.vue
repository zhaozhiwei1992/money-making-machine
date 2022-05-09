<template>
  <div id="app">
    <ribbon></ribbon>
    <el-container>
      <el-header>
        <div id="app-header">
          <jhi-navbar></jhi-navbar>
        </div>
      </el-header>
      <el-container>
        <el-aside width="230px">
          <div class="grid-content bg-purple">
            <!-- 动态左侧树 -->
            <el-menu
              :default-active="activeIndex"
              class="el-menu-vertical-demo"
              @select="handleSelect"
              style="margin-top: 20px"
              :collapse="isCollapse"
            >
              <template v-for="(item, index) in homeMenu">
                <el-submenu :index="item.index.toString()" v-if="item.children.length != 0" :key="index * 30">
                  <template slot="title">
                    <i :class="item.icons"></i>
                    <span slot="title">{{ item.name }}</span>
                  </template>
                  <el-menu-item :index="items.index.toString()" v-for="(items, indexs) in item.children" :key="indexs">
                    {{ items.name }}
                  </el-menu-item>
                </el-submenu>
                <el-menu-item :index="item.index.toString()" v-else :key="index * 2">
                  <i :class="item.icons"></i>
                  <span slot="title">{{ item.name }}</span>
                </el-menu-item>
              </template>
            </el-menu>
          </div>
        </el-aside>
        <el-container>
          <el-main>
            <div class="grid-content bg-purple-light">
              <div class="card jh-card">
                <router-view></router-view>
              </div>
            </div>

            <b-modal id="login-page" hide-footer lazy>
              <span data-cy="loginTitle" slot="modal-title" id="login-title" v-text="$t('login.title')">Sign in</span>
              <login-form></login-form>
            </b-modal>
          </el-main>
        </el-container>
      </el-container>

      <el-footer>
        <jhi-footer></jhi-footer>
      </el-footer>
    </el-container>
  </div>
</template>

<script lang="ts" src="./app.component.ts"></script>

<style scoped>
html,
body,
#app,
.el-container {
  height: 100%;
}

/* el-header和el-foolter默认都是60px */
.el-aside {
  height: calc(100vh - 120px);
  margin-left: 20px;
}

.el-main {
  padding: 0 !important;
  margin-right: 20px;
  height: calc(100vh - 120px);
}
</style>
