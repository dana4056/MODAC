import { ref } from "vue";
import { defineStore } from "pinia";

export const useArticleStore = defineStore("article", () => {
  const articles = ref([]);
  const deleteArticle = (seq) => {
    articles.value = articles.value.filter((article) => article.seq !== seq);
  };

  const selectedArticleItemSeq = ref(null);

  const articleContent = ref("");
  // const setArticleContent = (value) => {
  //   articleContent.value = value;
  // };

  const publicTypeNumber = ref(0);

  return {
    articles,
    deleteArticle,
    selectedArticleItemSeq,
    articleContent,
    // setArticleContent,
    publicTypeNumber,
  };
});
