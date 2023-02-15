<script setup>
import ArticleListItem from "./ArticleListItem.vue";
import ArticleEditor2 from "./ArticleEditor2.vue";
import { useTodoStore } from "@/stores/todo";
import { useArticleStore } from "@/stores/article";
import { ref, toRefs, computed } from "vue";
import articleAPI from "@/api/article";
import article from "@/api/article";
import todoAPI from "@/api/todo";

const todoStore = useTodoStore();
const todayTodos = ref([...todoStore.todos]);

const getCompleteTodos = () => {
  const filterTodos = computed(() => {
    return todayTodos.value.filter((elem) => {
      return elem.status === 2;
    });
  });

  const getTemplateByCategories = async (todosSeq) => {
    const todoItem = await todoAPI.findTodo(todosSeq);
    return todoItem.templateContent;
  };

  const articlesFromCompleteTodos = computed(() => {
    return filterTodos.value.map((elem) => {
      elem.public_type = 0;
      elem.isSubmit = false;
      // article의 content도 추가해줘야 할 것 같은데?
      elem.content = getTemplateByCategories(elem.seq); // 템플릿 가져오기
      return elem;
    });
  });

  return articlesFromCompleteTodos.value;
};

const articleStore = useArticleStore();

const { articles, selectedArticleItemSeq, articleContent } =
  toRefs(articleStore);

articles.value = getCompleteTodos();

selectedArticleItemSeq.value =
  articles.value.length !== 0 ? articles.value[0].seq : null;

const handleClickArticleItem = async (seq) => {
  selectedArticleItemSeq.value = seq;
  // const template = await
  const template = await articles.value.find((elem) => elem.seq === seq)
    .content;
  articleContent.value = template;
};
</script>

<template>
  <!-- <div v-if="articles.length !== 0"> -->
  <ArticleListItem
    :class="$style.article_item"
    v-for="articleItem in articles"
    :key="articleItem.seq"
    :articleItem="articleItem"
    :handleClickArticleItem="handleClickArticleItem"
  />
  <ArticleEditor2
    v-for="articleItem in articles"
    :key="articleItem.seq"
    :articleItem="articleItem"
  />
  <!-- </div> -->
  <!-- <div v-else>완료한 할 일이 없습니다!</div> -->
</template>

<style lang="css" module>
@import "./ArticleList.module.css";
</style>
