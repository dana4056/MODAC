<script setup>
import TodoListItem from "./TodoListItem.vue";
import { useTodoStore } from "../../../stores/todo";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import todoAPI from "@/api/todo";
import { useRouter } from "vue-router";

const todoStore = useTodoStore();
const { todos } = storeToRefs(todoStore);

const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

(async () => {
  todos.value = await todoAPI.findTodoList(loginUser.value.seq);
})();

const routeToArticlePage = () => {
  const router = useRouter();
  router.push("/article");
};
</script>

<template>
  <div :class="$style.todo_body_wrapper">
    <div :class="$style.todolist_wrapper">
      <div v-if="todos.length !== 0">
        <TodoListItem
          v-for="todo in todos"
          :key="todo.seq"
          :todo="todo"
          class="w-full"
        />
      </div>
      <div v-else>오늘 할 일을 등록하세요</div>
    </div>
    <div style="text-align: right">
      <button @click="routeToArticlePage" :class="$style.send_button">
        오늘 공부 정리하기
      </button>
    </div>
  </div>
</template>

<style lang="css" module>
@import "./TodoList.module.css";
</style>
