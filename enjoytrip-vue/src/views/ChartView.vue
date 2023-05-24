<template>
  <div class="main">
    <header-navi-bar></header-navi-bar>

    <div style="display: flex; justify-content: space-evenly; max-height:650px; margin-top: 60px;">
      <canvas id="chart1" width="50" height="50"></canvas>
      <canvas id="chart2" width="50" height="50"></canvas>
    </div>

    <footer-area></footer-area>
  </div>
</template>

<script>
import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue";
import FooterArea from "../components/layout/FooterArea.vue";
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

export default {
  name: "ChartView",
  components: {
    HeaderNaviBar,
    FooterArea,
  },
  data(){
    return {
      myChart: null,
      sido: null,
      attraction: null,
    }
  },
  methods: {
    fillData(){
      const ctx = document.getElementById('chart1').getContext('2d');
      this.myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          // 시도 이름
          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
          datasets: [
            {
              label: '많이 검색된 도시',
              // 검색된 횟수
              data: [12, 19, 3, 5, 2, 3],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 186, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 186, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
              ],
              borderWidth: 1
            }
          ]
        },
        option: {
          maintainAspectRatio: false,
          responsive: false,
        }
      });

      const ctx2 = document.getElementById('chart2').getContext('2d');
      this.myChart = new Chart(ctx2, {
        type: 'doughnut',
        data: {
          // 관광지 이름
          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
          datasets: [
            {
              label: '많이 검색된 관광지',
              // 관광지 검색 횟수
              data: [6, 5, 4, 3, 2, 1],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 186, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 186, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
              ],
              borderWidth: 1
            }
          ]
        },
        option: {
          maintainAspectRatio: false,
          responsive: false, // 차트 크기를 고정
        }
      });
    }
  },
  mounted(){
    this.fillData();
        const canvas = document.getElementById('chart1');
        canvas.style.width = '500px'; // 원하는 가로 크기로 변경
        canvas.style.height = '500px'; // 원하는 세로 크기로 변경
  },
  created() {
    // getMapping 시도
    // sido에 데이터 넣기
    // getMapping 관광지
    // attraction에 데이터 넣기
  }
}
</script>

<style>
</style>
