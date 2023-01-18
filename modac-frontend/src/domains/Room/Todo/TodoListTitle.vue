<template lang="">
  <div>
    <h1 class="text-3xl font-bold underline">TodoList Title</h1>
    <h1 class="custom_title">TITLE2</h1>
    <h1 class="my_title">TITLE3</h1>
    <h1 :class="$style.modules_title">TITLE 4</h1>
  </div>
</template>
<script setup></script>
<style module>
@import "./TodoListTitle.css";
</style>
