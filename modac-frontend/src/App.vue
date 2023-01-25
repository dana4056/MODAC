<script setup>
import NavbarTop from './components/NavbarTop.vue';
</script>

<template>
  <div id="header">
    <NavbarTop/>
  </div>
  <div id="body">
    <RouterView/>
  </div>
  <div id="footer">
  </div>
</template>

<style scoped>
</style>
