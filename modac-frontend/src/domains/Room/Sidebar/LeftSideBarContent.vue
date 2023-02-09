<script setup>
import TodoListTitle from "@/domains/Room/Todo/TodoListTitle.vue";
import TodoList from "@/domains/Room/Todo/TodoList.vue";
import TodoForm from "@/domains/Room/Todo/TodoForm.vue";
</script>

<template>
  <div :class="$style.content">
    <!--  min-h-screen -->
    <TodoListTitle />
    <TodoForm />
    <TodoList />
  </div>
</template>

<style lang="css" module>
@import "./LeftSideBarContent.module.css";
</style>
