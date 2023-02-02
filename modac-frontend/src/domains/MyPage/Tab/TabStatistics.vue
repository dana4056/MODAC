<template lang="">
  <div :class="$style.tabs_flex">
    <div :class="$style.statistics_item">
      <h1 :class="$style.statistics_title">TabStatistics 1</h1>
      <div :class="$style.statisics_div"></div>
    </div>
    
    <div :class="$style.statistics_item">
      <h1 :class="$style.statistics_title">TabStatistics 2</h1>
      <div :class="$style.statisics_div"></div>
    </div>
  </div>

</template>

<script setup>

</script>

<style lang="css" module>
  @import "./TabStatistics.module.css";
</style>