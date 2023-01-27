<script setup></script>

<template>
  <button :class="$style.round_button">
    <slot></slot>
  </button>
</template>

<style lang="css" module>
@import "./RoundButton.module.css";
</style>
