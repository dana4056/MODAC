<template lang="">
  <div>
    <p>{{ todos }}</p>
  </div>
</template>
<script setup>
import { useTodoStore } from "../../stores/todo";

const store = useTodoStore();
const todos = store.todos;
</script>
<style lang=""></style>
