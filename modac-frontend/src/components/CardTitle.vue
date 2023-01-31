<script setup></script>

<template>
  <h1 :class="$style.card_title_style">
    <slot></slot>
  </h1>
</template>

<style lang="css" module>
@import "./CardTitle.module.css";
</style>
