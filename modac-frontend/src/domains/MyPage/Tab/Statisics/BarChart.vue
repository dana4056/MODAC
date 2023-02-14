<template>
  <div>
    <canvas id="bar-chart" class=""></canvas>
  </div>
</template>

<script setup>
import Chart from 'chart.js'
// import barChartData from './barchart-data.js'
import { useStatsStore } from '@/stores/statistics';
import { storeToRefs } from "pinia"
import { onMounted } from 'vue';
const statsStore = useStatsStore();
const { barData } = storeToRefs(statsStore)

const barChartData = {
  type: "bar",
  data: {
    labels: [
      barData.value[0].dateInfo,
      barData.value[1].dateInfo,
      barData.value[2].dateInfo,
      barData.value[3].dateInfo,
      barData.value[4].dateInfo,
      barData.value[5].dateInfo,
      barData.value[6].dateInfo, 
    ],
    datasets: [{
      label: '일주일 간 TIL 작성 횟수',
      data: [
        barData.value[0].countArticles,
        barData.value[1].countArticles,
        barData.value[2].countArticles,
        barData.value[3].countArticles,
        barData.value[4].countArticles,
        barData.value[5].countArticles,
        barData.value[6].countArticles,
      ],
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(255, 205, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(201, 203, 207, 0.2)'
      ],
      borderColor: [
        'rgb(255, 99, 132)',
        'rgb(255, 159, 64)',
        'rgb(255, 205, 86)',
        'rgb(75, 192, 192)',
        'rgb(54, 162, 235)',
        'rgb(153, 102, 255)',
        'rgb(201, 203, 207)'
      ],
      borderWidth: 1
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
    },
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true,
          stepSize:1
        }
      }]
    }
  },
};

onMounted(() => {
  const ctx = document.getElementById('bar-chart');
  new Chart(ctx, barChartData)
})








</script>
