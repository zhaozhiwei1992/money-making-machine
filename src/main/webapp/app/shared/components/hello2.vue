<template>
  <div class="hello">
    <el-col :span="6">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>卡片2</span>
          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div v-for="o in 4" :key="o" class="text item">
          {{ '列表内容 ' + o }}
        </div>
      </el-card>
    </el-col>
  </div>
</template>
