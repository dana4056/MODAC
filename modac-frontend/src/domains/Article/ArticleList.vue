<script setup>
import ArticleListItem from "./ArticleListItem.vue";
import { useTodoStore } from "@/stores/todo";
import { useArticleStore } from "@/stores/article";
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

const articleStore = useArticleStore();

// 이거 체크해야함
const articles = articleStore.articles;
articles.value = getCompleteTodos();

const selectedArticleItemSeq = articleStore.selectedArticleItemSeq;
selectedArticleItemSeq.value = articles.value.length !== articles.value[0].seq;

const handleClickArticleItem = (seq) => {
  selectedArticleItemSeq.value = seq;
};
</script>

<template>
  <div v-if="articleList.length !== 0">
    <ArticleListItem
      :class="$style.article_item"
      v-for="articleItem in articleList"
      :key="articleItem.seq"
      :articleItem="articleItem"
      :handleClickArticleItem="handleClickArticleItem"
    />
  </div>
  <div v-else>완료한 할 일이 없습니다!</div>
</template>

<style lang="css" module>
@import "./ArticleList.module.css";
</style>
