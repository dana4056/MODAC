<template>
  <div>
    <ArticleListItem
        :class="$style.article_item"
        v-for="(article, index) in computedArticleList"
        :key="index"
        :article="article"
        />
  </div>
  <div :class="$style.article-editor">

  </div>
</template>

<script setup>
import ArticleListItem from "./ArticleListItem.vue"
import { useArticleStore } from "../../stores/article";
import { ref, computed } from "vue"
const store = useArticleStore()



// store에서 불러 와야 할 아이들
const computedArticleList = computed(() => {
  return store.articles
})

// 화면 생성 시 실행되어야 할 함수
store.getArticles()
store.getArticleList()

</script>

<style lang="css" module>
@import "./ArticleList.module.css"

</style>