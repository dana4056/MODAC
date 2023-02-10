<script setup>
import ArticleButtonList from "@/domains/Article/ArticleButtonList.vue";
import { ref } from "vue";
import { useArticleStore } from "@/stores/article";
import { storeToRefs } from "pinia";

const store = useArticleStore();
const { tempArticle, getArticles, buttonState } = storeToRefs(store);

const publicType = ref(1);
const nextButtonState = () => {
  buttonState.value = true;
};

const studyFinish = () => {
  console.log("study fin");
  console.log(getArticles);
  console.tempArticle = getArticles;
  console.log(getArticles);
};

const downloadMarkdown = (content) => {
  const encodedContent = encodeURIComponent(content);
  const link = document.createElement("a");
  link.setAttribute(
    "href",
    "data:text/markdown;charset=UTF-8," + encodedContent
  );
  link.setAttribute("download", "document.md"); // document를 todo 이름으로 바꾸기
  link.click();
};

const clickDownloadButtonHandler = () => {
  downloadMarkdown(tempArticle.value);
};
</script>

<template>
  <div :class="$style.flex_row_div">
    <div :class="$style.flex_row">
      <h1 :class="$style.page_title">TIL 작성하기</h1>
    </div>

    <div v-if="!buttonState" :class="$style.buttons">
      <div :class="$style.add_room_div">
        <select
          :class="$style.add_room_input"
          v-model="publicType"
          id="publicType"
        >
          <option value="1" selected="selected">전체 피드에 업로드</option>
          <option value="0">내 피드에만 업로드</option>
          <option value="2">피드에 업로드하지 않기</option>
        </select>
      </div>

      <button
        @click="nextButtonState"
        type="button"
        :class="$style.button_next"
      >
        작성 완료하기 →
      </button>
    </div>

    <ArticleButtonList />
  </div>
</template>

<style lang="css" module>
@import "ArticleHeader.module.css";
</style>
