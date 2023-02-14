import { computed, ref } from "vue";
import { defineStore } from "pinia";

export const useArticleStore = defineStore("article", () => {
  const articles = ref([]); // 게시글 목록
  const selectedArticleItemSeq = ref(null);
  const categoryOfSelectedArticleItem = computed(() =>
    articles.value.find(
      (article) => article.seq === selectedArticleItemSeq.value
    )
  );

  const publicType = ref(0);
  const completeWriteArticleState = ref(false);
  const githubState = ref(1);

  return {
    articles,
    selectedArticleItemSeq,
    categoryOfSelectedArticleItem,
    publicType,
    completeWriteArticleState,
    githubState,
  };
});
