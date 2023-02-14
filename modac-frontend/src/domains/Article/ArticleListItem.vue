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
  articleItem: Object,
  handleClickArticleItem: Function,
});

const articleItem = toRef(props, "articleItem");

const article_item_css = computed(() => {
  return articleItem.value.seq === store.selectedState;
});

// let time = computed(() => {
//   let s = Number(articleItem.value.totalSecond);
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
      @click="handleClickArticleItem(articleItem.seq)"
    >
      <div class="flex">
        <!-- <div id="clock">
          <span :class="$style.time">
            {{ time }}
          </span>
        </div> -->
        <CategoryTag :categoriesName="articleItem.categoriesName" />
      </div>
      <div :class="$style.title">
        {{ articleItem.title }}
      </div>
    </div>

    <div class="flex">
      <DeleteButton
        :handleClickDeleteArticleButton="handleClickDeleteArticleButton"
        :seq="articleItem.seq"
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "./ArticleListItem.module.css";
</style>
