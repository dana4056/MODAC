<script setup></script>

<template>
  <button :class="$style.button_style">
    <slot></slot>
  </button>
</template>

<style lang="css" module>
@import "./SideBarToggleButton.module.css";
</style>
