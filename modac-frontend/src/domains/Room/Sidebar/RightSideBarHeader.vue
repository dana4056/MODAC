<script setup></script>

<template>
  <div :class="$style.setting_box_header">
    <h1 :class="$style.title">
      <slot></slot>
    </h1>
    <slot name="button"></slot>
  </div>
</template>

<style lang="css" module>
@import "RightSideBarHeader.module.css";
</style>
