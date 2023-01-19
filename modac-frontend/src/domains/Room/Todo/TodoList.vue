<template lang="">
  <div>
    <!-- <TodolistItem :todos="todos" /> -->
    <p>{{ todos }}</p>
  </div>
</template>

<script setup>
import { useTodoStore } from "../../../stores/todo";
import { ref } from "vue";
// import TodolistItem from "./TodolistItem.vue";

const store = useTodoStore();
// const todos = store.todos;
const todos = ref([]);

todos.value = store.todos;
</script>

<style lang=""></style>
