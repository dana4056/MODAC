<script setup>
import TodoListTitle from "@/domains/Room/Todo/TodoListTitle.vue";
import TodoList from "@/domains/Room/Todo/TodoList.vue";
import TodoForm from "@/domains/Room/Todo/TodoForm.vue";
</script>

<template>
  <div style="width: 330px;
          border-radius: 5px;
          flex-direction: column;
          align-items: flex-start;
          padding: 10px;
          gap: 7px;
          height: 85vh;
          font-family: 'Pretendard';
          font-style: normal;
          font-weight: 400;"
          class="shadow-xl relative flex bg-gray-50 py-6 sm:py-12"
          >
          <!--  min-h-screen -->
    <TodoListTitle />
    <TodoForm />
    <TodoList />
  </div>
</template>

<style lang="css" module>
/* @import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.6/dist/web/static/pretendard.css"); */
</style>
