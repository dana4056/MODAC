<script setup>
import NavbarTop from "./components/NavbarTop.vue";
import Wrapper from "./components/Wrapper.vue";
</script>

<template>
  <Wrapper :class="$style.app_wrapper">
    <div id="header" :class="$style.app_navbar">
      <NavbarTop />
    </div>
    <div id="body" :class="$style.app_section">
      <RouterView />
    </div>
    <div id="footer"></div>
  </Wrapper>
</template>

<style lang="css" module>
@import "./App.module.css";
</style>
