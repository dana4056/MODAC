<template lang="">
  <div>
    <p>할 일을 입력하세요 :</p>
    <form @submit.prevent="submitTodoHandler(newTodo)">
      <input type="text" v-model="newTodo" />
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTodoStore } from "../../stores/todo";
const newTodo = ref("");
const store = useTodoStore();

const submitTodoHandler = store.addTodoItem;
</script>

<style lang=""></style>
