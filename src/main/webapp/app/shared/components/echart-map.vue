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
        legend: {},
        tooltip: {
          trigger: 'axis',
          showContent: false,
        },
        dataset: {
          source: [
            ['product', '2018', '2019', '2020', '2021', '2022', '2023'],
            ['预算指标变动', 411, 304, 651, 533, 838, 987],
            ['重大项目预算支出', 865, 921, 857, 831, 734, 551],
            ['预算结余', 241, 672, 795, 864, 652, 825],
            ['本级转移支付资金累计分配情况', 552, 671, 692, 724, 539, 391],
          ],
        },
        xAxis: { type: 'category' },
        yAxis: { gridIndex: 0 },
        grid: { top: '55%' },
        series: [
          { type: 'line', smooth: true, seriesLayoutBy: 'row' },
          { type: 'line', smooth: true, seriesLayoutBy: 'row' },
          { type: 'line', smooth: true, seriesLayoutBy: 'row' },
          { type: 'line', smooth: true, seriesLayoutBy: 'row' },
          {
            type: 'pie',
            id: 'pie',
            radius: '30%',
            center: ['50%', '25%'],
            label: {
              formatter: '{b}: {@2012} ({d}%)',
            },
            encode: {
              itemName: 'product',
              value: '2012',
              tooltip: '2012',
            },
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
