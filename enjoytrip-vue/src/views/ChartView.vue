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
import http from "../api/http.js";

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
      sido: [],
      attraction: [],
      sidoLabel : [],
      sidoCount : [],
      attractionLabel: [],
      attractionCount: [],
    }
  },
  methods: {
    fillData(){
      const ctx = document.getElementById('chart1').getContext('2d');

      if (this.myChart) {
        this.myChart.destroy();
      }

      this.myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          // 시도 이름
          labels: [this.sidoLabel[0], this.sidoLabel[1], this.sidoLabel[2], this.sidoLabel[3], this.sidoLabel[4], this.sidoLabel[5]],
          datasets: [
            {
              label: '많이 검색된 도시',
              // 검색된 횟수
              data: [this.sidoCount[0], this.sidoCount[1], this.sidoCount[2], this.sidoCount[3], this.sidoCount[4], this.sidoCount[5]],
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
        options: {
          // maintainAspectRatio: false,
          // responsive: false,
          plugins: {
            title: {
              display: true,
              text: '가장 많이 검색된 도시',
              font: {
                size: 25
              }
            }
          }
        }
      });

      const ctx2 = document.getElementById('chart2').getContext('2d');
      this.myChart = new Chart(ctx2, {
        type: 'doughnut',
        data: {
          // 관광지 이름
          labels: [this.attractionLabel[0], this.attractionLabel[1], this.attractionLabel[2], this.attractionLabel[3], this.attractionLabel[4], this.attractionLabel[5]],
          datasets: [
            {
              label: '많이 검색된 관광지',
              // 관광지 검색 횟수
              data: [this.attractionCount[0], this.attractionCount[1], this.attractionCount[2], this.attractionCount[3], this.attractionCount[4], this.attractionCount[5]],
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
        options: {
          // maintainAspectRatio: false,
          // responsive: false, // 차트 크기를 고정
          plugins: {
            title: {
              display: true,
              text: '가장 많이 검색된 관광지',
              font: {
                size: 25
              }
            }
          }
        }
      });
    }
  },
  mounted(){
    const canvas = document.getElementById('chart1');
    canvas.style.width = '500px'; // 원하는 가로 크기로 변경
    canvas.style.height = '500px'; // 원하는 세로 크기로 변경
  },
  created() {
    // getMapping 시도
    // sido에 데이터 넣기
    // getMapping 관광지
    // attraction에 데이터 넣기
    http.get(`/topSido`)
    .then((response) => {
      console.log(response.status);
      if(response.status == 200){
        this.sido = response.data;
        console.log(response.data);
        console.log(this.sido);
        for(let i = 0; i < 6; i++){
          this.sidoLabel[i] = this.sido[i].sido_name;
          this.sidoCount[i] = this.sido[i].cnt;
        }
        console.log(this.sidoLabel);
        console.log(this.sidoCount);
      }
    })
    .catch((error) =>{
      console.log(error);
    })

    http.get(`/topAtt`)
    .then((response) => {
      console.log(response.status);
      if(response.status == 200){
        this.attraction = response.data;
        for(let i = 0; i < 6; i++){
          this.attractionLabel[i] = this.attraction[i].attraction_name;
          this.attractionCount[i] = this.attraction[i].cnt;
        }
        console.log(this.attractionLabel);
        console.log(this.attractionCount);

        setTimeout(() => this.fillData(), 200);
        
      }
    })
    .catch((error) =>{
      console.log(error);
    })


  }
}
</script>

<style>
</style>
