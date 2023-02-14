<script setup>
import ArticleListItem from "./ArticleListItem.vue";
import { useTodoStore } from "@/stores/todo";
import { ref } from "vue";

// const todayTodos = [...useTodoStore().todos];
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

console.log(todayTodos.value, completeTodos.value);
</script>

<template>
  <div v-if="completeTodos.length !== 0">
    <ArticleListItem
      :class="$style.article_item"
      v-for="article in completeTodos"
      :key="article.seq"
      :article="article"
    />
  </div>
  <div v-else>완료한 할 일이 없습니다!</div>
</template>

<style lang="css" module>
@import "./ArticleList.module.css";
</style>
