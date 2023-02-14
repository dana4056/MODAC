<script setup>
import ArticleListItem from "./ArticleListItem.vue";
import { useTodoStore } from "@/stores/todo";
import { ref } from "vue";

const todoStore = useTodoStore();
const todayTodos = ref([...todoStore.todos]);

const getCompleteTodos = () => {
  const filterTodos = todayTodos.value.filter((elem) => {
    return elem.status === 2;
  });

  const newTodos = filterTodos.map((elem) => {
    elem.public_type = 0;
    elem.isSubmit = false;
    return elem;
  });

  return newTodos;
};

const completeTodos = ref(getCompleteTodos());
</script>

<template>
  <div v-if="completeTodos.length !== 0">
    <ArticleListItem
      :class="$style.article_item"
      v-for="completeTodo in completeTodos"
      :key="completeTodo.seq"
      :completeTodo="completeTodo"
    />
  </div>
  <div v-else>완료한 할 일이 없습니다!</div>
</template>

<style lang="css" module>
@import "./ArticleList.module.css";
</style>
