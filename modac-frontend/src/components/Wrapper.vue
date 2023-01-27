<script setup></script>

<template>
  <div :class="$style.wrapper">
    <slot></slot>
  </div>
</template>

<style lang="css" module>
@import "./Wrapper.module.css";
</style>
