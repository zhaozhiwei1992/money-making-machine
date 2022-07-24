<template>
  <el-col :span="12" class="card-box">
    <el-card>
      <div slot="header"><span>接口数据量统计</span></div>
      <div class="el-table el-table--enable-row-hover el-table--medium">
        <!-- <div ref="commandstats" style="height: 412px" /> -->
        <div ref="records" style="background-color: #ffffff; min-height: 350px; padding: 10px"></div>
      </div>
    </el-card>
  </el-col>
</template>

<script>
import * as echarts from 'echarts';

export default {
  data() {
    return {
      records: null,
      options: {
        // title: {
        //   text: '每周接口数据量',
        // },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985',
            },
          },
        },
        legend: {
          data: ['预算指标变动', '重大项目预算支出', '本年上级转移支付累计分配情况', '预算结余', '本级转移支付资金累计分配情况'],
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
          },
        ],
        yAxis: [
          {
            type: 'value',
          },
        ],
        series: [
          {
            name: '预算指标变动',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            data: [120, 132, 101, 134, 90, 230, 210],
          },
          {
            name: '重大项目预算支出',
            type: 'line',
            areaStyle: {},
            data: [220, 182, 191, 234, 290, 330, 310],
          },
          {
            name: '本年上级转移支付累计分配情况',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            data: [150, 232, 201, 154, 190, 330, 410],
          },
          {
            name: '预算结余',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            data: [320, 332, 301, 334, 390, 330, 320],
          },
          {
            name: '本级转移支付资金累计分配情况',
            type: 'line',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'top',
              },
            },
            areaStyle: {},
            data: [820, 932, 901, 934, 1290, 1330, 1320],
          },
        ],
      },
    };
  },
  mounted() {
    this.initData();
  },
  methods: {
    initData() {
      console.log('显示echart div', this.$refs.records);
      this.records = echarts.init(this.$refs.records, 'walden');
      this.records.setOption(this.options);
    },
  },
};
</script>
