<script setup></script>

<template>
  <button :class="$style.squareButtonStyle">
    <slot></slot>
  </button>
</template>

<style lang="css" module>
@import "SquareButton.module.css";
</style>
