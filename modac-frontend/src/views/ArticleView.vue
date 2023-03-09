<script setup>
import ArticleHeader from "@/domains/Article/ArticleHeader.vue";
import ArticleBody from "@/domains/Article/ArticleBody.vue";
import { useArticleStore } from "@/stores/article";
import {  toRefs } from "vue";

const articleStore = useArticleStore();

const {  completeWriteArticleState } = toRefs(articleStore);
completeWriteArticleState.value = false;
console.log(completeWriteArticleState.value)

</script>

<template>
  <!-- v-if="tempArticle.length !== 0" -->
  <div :class="$style.article_container_above">
    <ArticleHeader />
    <ArticleBody />
  </div>
</template>

<style lang="css" module>
@import "./ArticleView.module.css";
</style>
