<script setup>
import ArticleListItem from "./ArticleListItem.vue";
import { useTodoStore } from "@/stores/todo";
import { useArticleStore } from "@/stores/article";
import { ref, toRefs, computed } from "vue";

const todoStore = useTodoStore();
const todayTodos = ref([...todoStore.todos]);

const getCompleteTodos = () => {
  const filterTodos = computed(() => {
    return todayTodos.value.filter((elem) => {
      return elem.status === 2;
    });
  });

  const getTemplateByCategories = (categoriesName) => {
    // "알고리즘"
    // "CS"
    // "개발"
    // "기획"
    // "기타"
    if (categoriesName === "알고리즘") {
      // 템플릿 어떻게 가져오기로 했었지..? 물어보기
    }
  };

  const articlesFromCompleteTodos = computed(() => {
    return filterTodos.value.map((elem) => {
      elem.public_type = 0;
      elem.isSubmit = false;
      // article의 content도 추가해줘야 할 것 같은데?
      elem.content = getTemplateByCategories(elem.categoriesName); // 템플릿 가져오기
      return elem;
    });
  });

  return articlesFromCompleteTodos.value;
};

const articleStore = useArticleStore();

const { articles, selectedArticleItemSeq } = toRefs(articleStore);

articles.value = getCompleteTodos();

selectedArticleItemSeq.value =
  articles.value.length !== 0 ? articles.value[0].seq : null;

const handleClickArticleItem = (seq) => {
  selectedArticleItemSeq.value = seq;
};
</script>

<template>
  <div v-if="articles.length !== 0">
    <ArticleListItem
      :class="$style.article_item"
      v-for="articleItem in articles"
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
