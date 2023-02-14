import { ref } from "vue";
import { defineStore } from "pinia";

export const useArticleStore = defineStore("article", () => {
  const articles = ref([]); // 게시글 목록

  const publicType = ref(0);
  const selectedTodoSeq = ref(null);
  const completeWriteArticleState = ref(false);
  const githubState = ref(1);

  return {
    articles,
    publicType,
    selectedTodoSeq,
    completeWriteArticleState,
    githubState,
  };
});
