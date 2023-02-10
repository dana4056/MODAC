<script setup>
import ArticleList from "@/domains/Article/ArticleList.vue";
import ArticleEditor from "@/domains/Article/ArticleEditor.vue";
import OverflowDiv from "@/components/OverflowDiv.vue";
</script>

<template>
  <div :class="$style.flex_row_div_edit">
    <div :class="$style.article_container_below">
      <OverflowDiv>
        <div :class="$style.article_list">
          <ArticleList />
        </div>
      </OverflowDiv>
      <div :class="$style.editor">
        <ArticleEditor />
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "ArticleBody.module.css";
</style>
