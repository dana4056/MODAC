<script setup></script>

<template>
  <button :class="$style.btn_flip">
    <slot></slot>
  </button>
</template>

<style lang="css" module>
@import "./FollowButton.module.css";
</style>
