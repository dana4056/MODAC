<template>
  <div>
    <h1 class="my_color my_size">Hi my Name is</h1>
    {{ articles }}
    <ArticleListItem
        class="article-item"
        v-for="(article, index) in articles"
        :key="index"
        :article="article"
        />
    <form @submit.prevent="store.addArticleItem(test)">
      <input v-model="test" type="text">
      <button>추가하기</button>
    </form>
  </div>
  <div class="article-editor">

  </div>
</template>

<script setup>
import "./ArticleList.css"
import ArticleListItem from "./ArticleListItem.vue"
import { useTodoStore } from "../../stores/article";
import { ref } from "vue"
const store = useTodoStore();
const articles = store.articles
const test = ref("")
// store에 저장된 함수 불러오기
store.getArticleList()
store.createArticleListItem()
store.addArticleItem()  //삭제 예정
</script>

<style>


</style>