<template>
  <div>
    <h1 class="my_color my_size">Hi my Name is</h1>
    <ArticleListItem
        class="article-item"
        v-for="(article, index) in articles"
        :key="index"
        :article="article"
        />
  </div>
</template>

<script setup>
import "./ArticleList.css"
import "./ArticleListItem.vue"
import { useTodoStore } from "../../stores/article";
import { ref } from "vue";

const store = useTodoStore();
const articles = ref([])

</script>

<style>


</style>