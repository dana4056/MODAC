<script setup>
import TodoListItem from "./TodoListItem.vue";
import { useTodoStore } from "../../../stores/todo";
import { useUserStore } from "@/stores/user";
import { ref } from "vue";
import { storeToRefs } from "pinia";

const todoStore = useTodoStore();
const { todos } = storeToRefs(todoStore);

const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

const response = await todoStore.api.findTodoList(loginUser.value.seq);
todos.value = response.data;
// console.log("response: ", response);

const TodoListItemComponent = ref();

let nowPlayState = -1;
const nowPlay = (index) => {
  if (nowPlayState != -1) {
    console.log("===========");
  }

  nowPlayState = index;
};

const deleteTodoItem = (deleteTodoItem) => {
  todoStore.deleteTodoItem(deleteTodoItem);
};

const sendTodoItemToArticlePage = () => {
  const completedTodos = todos.value.filter((elem) => {
    return elem.status === 2;
  });

  if (confirm("TIL을 작성하러 가시겠습니까?") === true) {
    for (const completed in completedTodos) {
      deleteTodoItem(completedTodos[completed]);
    }
  }
};
</script>

<template>
  <div class="todo_body_wrapper">
    <div :class="$style.todolist_wrapper">
      <div v-if="todos.length !== 0">
        <TodoListItem
          v-for="todo in todos"
          :key="todo.seq"
          :todo="todo"
          class="w-full"
          @nowPlay="nowPlay"
          :id="`todo_` + todo.seq"
          ref="TodoListItemComponent"
        />
      </div>
      <div v-else>오늘 할 일을 등록하세요</div>
    </div>
    <div style="text-align: right">
      <br />
      <button @click="sendTodoItemToArticlePage" :class="$style.send_button">
        오늘 공부 정리하기
      </button>
    </div>
  </div>
</template>

<style lang="css" module>
@import "./TodoList.module.css";
</style>
