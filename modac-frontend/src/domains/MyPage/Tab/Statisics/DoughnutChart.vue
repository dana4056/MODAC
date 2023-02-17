<template>
  <div>
    <canvas id="doughnut-chart" class=""></canvas>
  </div>
</template>

<script setup>
import Chart from 'chart.js'
// import DougnutChartData from './doughnutchart-data.js'
import { useStatsStore } from '@/stores/statistics';
import { storeToRefs } from "pinia"
import { onMounted } from 'vue';

const statsStore = useStatsStore();
const { doughnutData } = storeToRefs(statsStore)

const doughnutChartData = {
  type: "doughnut",
  data : {
    labels: [
      doughnutData.value[0].categoryName,
      doughnutData.value[1].categoryName,
      doughnutData.value[2].categoryName,
      doughnutData.value[3].categoryName,
      doughnutData.value[4].categoryName,
    ],
    datasets: [{
      label: 'My First Dataset',
      data: [
        doughnutData.value[0].countArticles,
        doughnutData.value[1].countArticles,
        doughnutData.value[2].countArticles,
        doughnutData.value[3].countArticles,
        doughnutData.value[4].countArticles,
      ],
      backgroundColor: [
        'rgba(255, 99, 132)',
        'rgba(255, 159, 64)',
        'rgba(255, 205, 86)',
        'rgba(75, 192, 192)',
        'rgba(54, 162, 235)',
      ],
      hoverOffset: 4
    }]
  },
};


onMounted(() => {
  const ctx = document.getElementById('doughnut-chart');
    new Chart(ctx, doughnutChartData)

})

</script>