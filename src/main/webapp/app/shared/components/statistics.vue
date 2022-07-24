<template>
  <el-col :span="24">
    <el-row>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>用户访问量</span>
            <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
          </div>
          <div>
            <div>4,056</div>
            <div>
              比昨天 <a style="color: #1aa094">▲0.12</a><br />
              比七日 <a style="color: #bd3004">▼0.06</a>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>接口访问量</span>
          </div>
          <div>
            <div>4,056,000</div>
            <div>
              比昨天 <a style="color: #1aa094">▲0.12</a><br />
              比七日 <a style="color: #bd3004">▼0.06</a>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>接口访问异常量</span>
          </div>
          <div>
            <div>4,056,000</div>
            <div>
              比昨天 <a style="color: #1aa094">▲0.12</a><br />
              比七日 <a style="color: #bd3004">▼0.06</a>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>登录异常量</span>
          </div>
          <div>
            <div>4,056</div>
            <div>
              比昨天 <a style="color: #1aa094">▲0.12</a><br />
              比七日 <a style="color: #bd3004">▼0.06</a>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-col>
</template>
