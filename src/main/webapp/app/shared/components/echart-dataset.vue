<template>
  <el-col :span="12" class="card-box">
    <el-card>
      <div slot="header"><span>柱状图</span></div>
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
        tooltip: {},
        dataset: {
          dimensions: ['product', '2021', '2022', '2023'],
          source: [
            { product: '预算指标变动', 2021: 433, 2022: 858, 2023: 937 },
            { product: '项目预算支出', 2021: 831, 2022: 734, 2023: 551 },
            { product: '预算结余', 2021: 864, 2022: 652, 2023: 825 },
            { product: '转移支付分配', 2021: 724, 2022: 539, 2023: 391 },
          ],
        },
        xAxis: { type: 'category' },
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }],
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
