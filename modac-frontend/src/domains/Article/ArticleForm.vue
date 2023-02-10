<template>
  <div>
    <h1 class="my_color my_size">공개 범위 상태변경 및 작성 완료시키기</h1>
    <form @submit.prevent="completeArticle">
      <select v-model="selectValue">
        <option
          v-for="(item, index) in selectOption"
          :key="index"
          :value="item.value"
        >
          {{ item.name }}
        </option>
      </select>
      <input type="submit" />
      <ArticleEditor />
    </form>
  </div>
</template>

<script setup>
import ArticleEditor from "./ArticleEditor.vue";
import { useArticleStore } from "../../stores/article";
import { ref } from "vue";

const store = useArticleStore();

const selectValue = ref(store.publicType);
const selectOption = [
  { name: "전체 공개", value: "0" },
  { name: "내 피드에만 업로드", value: "1" },
  { name: "피드에 업로드하지 않기", value: "2" },
];

const completeArticle = function () {
  console.log(selectValue.value);
};
</script>

<style>
@import "./ArticleForm.module.css";
</style>
