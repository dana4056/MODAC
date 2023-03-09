<script setup>
import GithubButton from "@/domains/Article/GithubButton.vue";
import { useArticleStore } from "@/stores/article";
import { storeToRefs } from "pinia";
import Message from "vue-m-message";

const articleStore = useArticleStore();
const { activeEditor } = storeToRefs(articleStore);

const copyText = () => {
  const currentActiveEditor = activeEditor.value;
  const content = currentActiveEditor.getMarkdown();
  navigator.clipboard.writeText(content);
  Message.info("복사되었습니다 :-)", { closable: true });
};

const downloadMarkdown = () => {
  const currentActiveEditor = activeEditor.value;
  const content = currentActiveEditor.getMarkdown();
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
  downloadMarkdown();
};
</script>

<template>
  <div :class="$style.buttons">
    <button @click="copyText" :class="$style.button_next">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="w-5 h-5"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M15.75 17.25v3.375c0 .621-.504 1.125-1.125 1.125h-9.75a1.125 1.125 0 01-1.125-1.125V7.875c0-.621.504-1.125 1.125-1.125H6.75a9.06 9.06 0 011.5.124m7.5 10.376h3.375c.621 0 1.125-.504 1.125-1.125V11.25c0-4.46-3.243-8.161-7.5-8.876a9.06 9.06 0 00-1.5-.124H9.375c-.621 0-1.125.504-1.125 1.125v3.5m7.5 10.375H9.375a1.125 1.125 0 01-1.125-1.125v-9.25m12 6.625v-1.875a3.375 3.375 0 00-3.375-3.375h-1.5a1.125 1.125 0 01-1.125-1.125v-1.5a3.375 3.375 0 00-3.375-3.375H9.75"
        />
      </svg>
      &nbsp;복사하기
    </button>
    <GithubButton />
    <button @click="clickDownloadButtonHandler" :class="$style.button_next">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="w-5 h-5"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3"
        />
      </svg>
      &nbsp;다운로드
    </button>
  </div>
</template>

<style lang="css" module>
@import "ArticleButtonList.module.css";
</style>
