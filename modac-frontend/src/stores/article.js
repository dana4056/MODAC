import { computed, ref } from "vue";
import { defineStore } from "pinia";

export const useArticleStore = defineStore("article", () => {
  const articles = ref([]);
  const deleteArticle = (seq) => {
    articles.value = articles.value.filter((article) => article.seq !== seq);
  };

  const selectedArticleItemSeq = ref(null);

  // 카테고리에 맞게 템플릿을 넣어주는 함수로 바꾸자..
  const categoryOfSelectedArticleItem = computed(() =>
    articles.value.find(
      (article) => article.seq === selectedArticleItemSeq.value
    )
  );

  const publicTypeNumber = ref(0);
  const githubState = ref(1);

  return {
    articles,
    deleteArticle,
    selectedArticleItemSeq,
    categoryOfSelectedArticleItem,
    publicTypeNumber,
    githubState,
  };
});
