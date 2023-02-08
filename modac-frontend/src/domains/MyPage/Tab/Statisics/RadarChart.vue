<template>
  <div>
    <canvas id="radar-chart"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js'
import radarChartData from './radarchart-data.js'

export default {
  name: 'radarChart',
  data(){
    return {
      radarChartData: radarChartData
    }
  },
  mounted(){
    const ctx = document.getElementById('radar-chart');
    new Chart(ctx, this.radarChartData)
  }
}
</script>