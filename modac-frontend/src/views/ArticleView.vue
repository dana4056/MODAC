<script setup>
import ArticleHeader from "@/domains/Article/ArticleHeader.vue";
import ArticleBody from "@/domains/Article/ArticleBody.vue";
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
