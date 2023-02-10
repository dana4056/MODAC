<template>
  <div :class="article_item_css ? $style.article_item_active : $style.article_item">
    <div class="flex flex-col justify-between gap-1 w-full" @click="clickArticle">
      <div class="flex">
        <!-- 타이머 -->
        <div id="clock">
          <span class="bg-indigo-100 text-indigo-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-indigo-400 border border-indigo-400">
            {{ time }}
          </span>
        </div>

        <div>
          <span v-if="article.categoriesName === '알고리즘'" class="bg-blue-100 text-blue-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded-full dark:bg-blue-900 dark:text-blue-300">#알고리즘</span>
          <span v-if="article.categoriesName === 'CS'" class="bg-yellow-100 text-yellow-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded-full dark:bg-yellow-900 dark:text-yellow-300">#CS</span>
          <span v-if="article.categoriesName === '개발'" class="bg-red-100 text-red-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded-full dark:bg-red-900 dark:text-red-300">#개발</span>
          <span v-if="article.categoriesName === '면접'" class="bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded-full dark:bg-green-900 dark:text-green-300">#면접</span>
          <span v-if="article.categoriesName === '공통'" class="bg-gray-100 text-gray-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded-full dark:bg-gray-700 dark:text-gray-300">#공통</span>
        </div>
      </div>
      <div class="text-ellipsis whitespace-nowrap overflow-hidden">
        {{ article.title }}
      </div>

    </div>

    <div class="flex">
      <button
      id="delete"
      class="bg-transparent"
      @click="deleteArticleItem(article.seq)"
      >
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-5 h-5">
          <path fill-rule="evenodd" d="M16.5 4.478v.227a48.816 48.816 0 013.878.512.75.75 0 11-.256 1.478l-.209-.035-1.005 13.07a3 3 0 01-2.991 2.77H8.084a3 3 0 01-2.991-2.77L4.087 6.66l-.209.035a.75.75 0 01-.256-1.478A48.567 48.567 0 017.5 4.705v-.227c0-1.564 1.213-2.9 2.816-2.951a52.662 52.662 0 013.369 0c1.603.051 2.815 1.387 2.815 2.951zm-6.136-1.452a51.196 51.196 0 013.273 0C14.39 3.05 15 3.684 15 4.478v.113a49.488 49.488 0 00-6 0v-.113c0-.794.609-1.428 1.364-1.452zm-.355 5.945a.75.75 0 10-1.5.058l.347 9a.75.75 0 101.499-.058l-.346-9zm5.48.058a.75.75 0 10-1.498-.058l-.347 9a.75.75 0 001.5.058l.345-9z" clip-rule="evenodd" />
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useArticleStore } from "../../stores/article";
import ArticleEditor from "./ArticleEditor.vue";

// defineProps(['article'])
const store = useArticleStore()
// 함수 불러오기
const deleteArticleItem = (seq) => {
  console.log("deleteArticleItem");
  store.articles = store.deleteArticleItem(seq);
  console.log(store.articles);
};

const props = defineProps({
  article : Object,
});

const clickArticle = () => {
  store.selectedState = props.article.seq;
  console.log("props.article.title", props.article.title);

  ArticleEditor.editorTemplete = "# " + store.articles[store.selectedState].title;
}

const article_item_css = computed(() => {
  return props.article.seq === store.selectedState;
})

let time = computed(() => {
  let s = Number(props.article.totalSecond);
  let ms = s % 1000;
  s = (s - ms) / 1000;
  let secs = s % 60;
  s = (s - secs) / 60;
  let mins = s % 60;
  let hrs = (s - mins) / 60;

  if (hrs < 10) {
    hrs = "0" + hrs;
  }
  if (mins < 10) {
    mins = "0" + mins;
  }
  if (secs < 10) {
    secs = "0" + secs;
  }

  return hrs + ':' + mins + ':' + secs;
})
</script>

<style lang="css" module>
@import './ArticleListItem.module.css'
</style>