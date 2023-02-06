<template>
  <!-- 로딩 동그라미 -->
  <div :class="$style.sk_circle">
    <div :class="`${$style.sk_circle1} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle2} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle3} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle4} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle5} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle6} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle7} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle8} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle9} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle10} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle11} ${$style.sk_child}`"></div>
    <div :class="`${$style.sk_circle12} ${$style.sk_child}`"></div>
  </div>

  <!-- 스크롤시 붙을 div -->
  <div class="container">
    <div :class="$style.big_box"><h1>Page 2</h1></div>
    <div :class="$style.big_box"><h1>Page 1</h1></div>
  </div>
  
</template>

<script setup>
</script>

<style lang="css" module>
@import './InfiniteScroll.module.css'

</style>