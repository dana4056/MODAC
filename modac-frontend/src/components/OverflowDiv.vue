<script setup></script>

<template>
  <div :class="$style.overflow_container">
    <slot></slot>
  </div>
</template>

<style lang="css" module>
@import "OverflowDiv.module.css";
</style>
