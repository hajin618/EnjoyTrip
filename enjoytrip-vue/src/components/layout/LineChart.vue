<template>
  <div>
    <canvas id="chart" width="400" height="400"></canvas>

  </div>
</template>

<script>

import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

export default {
  data(){
    return {
      myChart: null
    }
  },
  mounted(){
    this.fillData();
  },
  methods: {
    fillData(){
      const stx = document.getElementById('chart').getContext('2d');
      this.myChart = new Chart(ctx, {
        type: 'line',
        data: {},
        option: {}
      });
    }
  },
}
</script>

<style>

</style>