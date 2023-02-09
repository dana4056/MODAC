<script setup></script>

<template lang="">
  <div :class="$style.list_title">
    <h1
      style="
        font-weight: 700;
        font-size: 23px;
        line-height: 20px;
        color: black;
        padding: 5px;
      "
      class="flex justify-between items-center"
    >
      할 일 목록
    </h1>
  </div>
</template>

<style module>
@import "./TodoListTitle.css";
</style>
