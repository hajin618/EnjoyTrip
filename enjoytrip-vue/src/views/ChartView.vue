<template>
  <div>
    <header-navi-bar></header-navi-bar>

    <div>
      <donut-chart :chartData="chartData" :options="chartOptions"></donut-chart>
    </div>

    <footer-area></footer-area>
  </div>
</template>

<script>
import DonutChart from "../components/layout/DonutChart.vue";
import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue";
import FooterArea from "../components/layout/FooterArea.vue";

export default {
  name: "ChartView",
  components: {
    HeaderNaviBar,
    FooterArea,
    DonutChart,
  },
  data() {
    return {
      chartData: {
        labels: ['Red', 'Blue', 'Yellow'],
        datasets: [
          {
            label: 'My First Dataset',
            data: [300, 50, 100],
            backgroundColor: [
              'rgb(255, 99, 132)',
              'rgb(54, 162, 235)',
              'rgb(255, 205, 86)',
            ],
            hoverOffset: 4,
          },
        ],
      },
      chartOptions: {
        // 차트 옵션 설정
      },
    };
  },
  created() {

  }
}
</script>

<style>
</style>
