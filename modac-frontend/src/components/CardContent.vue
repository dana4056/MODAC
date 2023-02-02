<script setup></script>

<template>
  <p :class="$style.card_content_style">
    <slot></slot>
  </p>
</template>

<style lang="css" module>
@import "./CardContent.module.css";
</style>
