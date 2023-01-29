<script setup></script>

<template>
  <div :class="$style.card">
    <slot></slot>
  </div>
</template>

<style lang="css" module>
@import "./Card.module.css";
</style>
