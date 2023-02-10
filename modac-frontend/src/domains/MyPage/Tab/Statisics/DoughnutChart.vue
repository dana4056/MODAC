<template>
  <div>
    <canvas id="doughnut-chart" class=""></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js'
import DougnutChartData from './doughnutchart-data.js'

export default {
  name: 'DougnutChart',
  data(){
    return {
      DougnutChartData: DougnutChartData
    }
  },
  mounted(){
    const ctx = document.getElementById('doughnut-chart');
    new Chart(ctx, this.DougnutChartData)
  }
}
</script>