<script setup>
import ArticleEditor from "./ArticleEditor.vue";
import DeleteButton from "../Room/Todo/DeleteButton.vue";
import CategoryTag from "./CategoryTag.vue";
import { computed, toRef } from "vue";
import { useArticleStore } from "../../stores/article";
import todoAPI from "@/api/todo";

const store = useArticleStore();

const handleClickDeleteArticleButton = (seq) => {
  todoAPI.deleteTodo(seq);
  store.articles = store.deleteArticleItem(seq);
};

const props = defineProps({
  completeTodo: Object,
});

const completeTodo = toRef(props, "completeTodo");

const clickArticle = () => {
  store.selectedState = completeTodo.value.seq;

  ArticleEditor.editorTemplete =
    "# " + store.articles[store.selectedState].title;
};

const article_item_css = computed(() => {
  return completeTodo.value.seq === store.selectedState;
});

// let time = computed(() => {
//   let s = Number(completeTodo.value.totalSecond);
//   let ms = s % 1000;
//   s = (s - ms) / 1000;
//   let secs = s % 60;
//   s = (s - secs) / 60;
//   let mins = s % 60;
//   let hrs = (s - mins) / 60;

//   if (hrs < 10) {
//     hrs = "0" + hrs;
//   }
//   if (mins < 10) {
//     mins = "0" + mins;
//   }
//   if (secs <script 10) {
//     secs = "0" + secs;
//   }

//   return hrs + ":" + mins + ":" + secs;
// });
</script>

<template>
  <div
    :class="article_item_css ? $style.article_item_active : $style.article_item"
  >
    <div
      class="flex flex-col justify-between gap-1 w-full"
      @click="clickArticle"
    >
      <div class="flex">
        <!-- <div id="clock">
          <span :class="$style.time">
            {{ time }}
          </span>
        </div> -->
        <CategoryTag :categoriesName="completeTodo.categoriesName" />
      </div>
      <div :class="$style.title">
        {{ completeTodo.value.title }}
      </div>
    </div>

    <div class="flex">
      <DeleteButton
        :handleClickDeleteArticleButton="handleClickDeleteArticleButton"
        :seq="article.seq"
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "./ArticleListItem.module.css";
</style>
