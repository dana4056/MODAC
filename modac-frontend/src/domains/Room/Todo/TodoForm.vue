<template lang="">
  <div>
    <p>할 일을 입력하세요 :</p>
    <form @submit.prevent="submitTodoHandler(newTodo)">
      <label for="title">Todo 제목 : </label>
      <input id="title" type="text" v-model="newTodo" />
      <label for="category">todo 카테고리 : </label>
      <input id="category" type="text" v-model="newTodo" />
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTodoStore } from "../../../stores/todo";
const newTodo = ref("");
const store = useTodoStore();

const submitTodoHandler = store.addTodoItem;

// const buttonClickHandler = () => {
//   const document.querySelector("#dropdownMenuButton1")
// }
</script>

<style lang=""></style>
