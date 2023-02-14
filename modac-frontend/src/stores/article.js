import { ref } from "vue";
import { defineStore } from "pinia";

export const useArticleStore = defineStore("article", () => {
  const articles = ref([]); // 게시글 목록

  const publicType = ref(0);
  let selectedState = ref(0);
  const buttonState = ref(false);
  let githubState = ref(1);

  return {
    articles,
    publicType,
    selectedState,
    buttonState,
    githubState,
  };
});
