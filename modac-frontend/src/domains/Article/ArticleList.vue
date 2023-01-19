<template>
  <div>
    <h1 class="my_color my_size">Hi my Name is</h1>
    {{ computedArticleList }}
    <ArticleListItem
        class="article-item"
        v-for="(article, index) in computedArticleList"
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
import { ref, computed } from "vue"
const store = useTodoStore();
const test = ref("")
// store에서 불러 와야 할 아이들
const computedArticleList = computed(() => {
  return store.articles
})

// 화면 생성 시 실행되어야 할 함수
store.getArticleList()

</script>

<style>


</style>