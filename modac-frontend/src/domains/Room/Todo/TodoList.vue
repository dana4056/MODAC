<template lang="">
  <div>
    <p>{{ todos }}</p>
  </div>
</template>

<script setup>
import { useTodoStore } from "../../../stores/todo";
import { ref } from "vue";

const store = useTodoStore();
// const todos = store.todos;
const todos = ref([]);

store.getTodoList();
</script>

<style lang=""></style>
