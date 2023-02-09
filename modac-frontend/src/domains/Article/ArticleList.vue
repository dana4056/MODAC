<template>
  <ArticleListItem
      :class="$style.article_item"
      v-for="article in computedArticleList"
      :key="article.seq"
      :article="article"
      />
      <!-- @click="setTempleteClick" -->
      <!-- @click="setNewTemplete" -->
  <!-- <div :class="$style.editor">
    <ArticleEditor ref="setTemplete" />
  </div> -->
</template>

<script setup>
import ArticleListItem from "./ArticleListItem.vue"
// import ArticleEditor from "./ArticleEditor.vue"
import { useArticleStore } from "../../stores/article";
import { computed, ref } from "vue"

const store = useArticleStore()

// const setTemplete = ref("");
// function settingTemplete() {
//   // 내보낸 자식 컴포넌트 함수를 호출한다.
//   setTemplete.value.setTemplete();
// }

// 화면 생성 시 실행되어야 할 함수
let getArticleListState = ref(false);

if (!getArticleListState.value) {
  getArticleListState.value = true;
  store.getArticles()
  store.getArticleList()
}


// store에서 불러 와야 할 아이들
const computedArticleList = computed(() => {
  return store.articles
})

</script>

<style lang="css" module>
@import "./ArticleList.module.css"

</style>