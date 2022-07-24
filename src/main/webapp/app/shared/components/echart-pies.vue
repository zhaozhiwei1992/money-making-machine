<template>
  <el-col :span="12" class="card-box">
    <el-card>
      <div slot="header"><span>饼图/玫瑰图</span></div>
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
        title: {
          text: '接口数据总量',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['预算指标变动', '重大项目预算支出', '本年上级转移支付累计分配情况', '预算结余', '本级转移支付资金累计分配情况'],
        },
        series: [
          {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            roseType: 'radius',
            data: [
              { value: 335, name: '预算指标变动' },
              { value: 310, name: '重大项目预算支出' },
              { value: 234, name: '本年上级转移支付累计分配情况' },
              { value: 135, name: '预算结余' },
              { value: 368, name: '本级转移支付资金累计分配情况' },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)',
              },
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
