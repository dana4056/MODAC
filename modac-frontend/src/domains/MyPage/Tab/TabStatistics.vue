<template lang="">
  <OverflowDiv class="h-full">
    <!-- <div :class="$style.tabs_flex"> -->
      <div :class="$style.statistics_item_bar">
        <h1 :class="$style.statistics_title">TabStatistics 1</h1>
        <div :class="$style.barChart">
          <BarChart/>
        </div>
      </div>
    <!-- </div> -->

    <div :class="$style.tabs_flex">
      <div :class="$style.statistics_item">
        <h1 :class="$style.statistics_title">TabStatistics 2</h1>
        <RadarChart/>
      </div>
      
      <div :class="$style.statistics_item">
        <h1 :class="$style.statistics_title">TabStatistics 3</h1>
        <DoughnutChart/>
      </div>
    </div>
  </OverflowDiv>
</template>

<script setup>
import OverflowDiv from '@/components/OverflowDiv.vue';
import BarChart from './Statisics/BarChart.vue';
import RadarChart from './Statisics/RadarChart.vue';
import DoughnutChart from './Statisics/DoughnutChart.vue';




</script>

<style lang="css" module>
@import "./TabStatistics.module.css";
</style>