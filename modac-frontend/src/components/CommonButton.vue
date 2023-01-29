<script setup></script>

<template>
  <button :class="$style.common_button">
    <slot></slot>
  </button>
</template>

<style lang="css" module>
@import "./CommonButton.module.css";
</style>
