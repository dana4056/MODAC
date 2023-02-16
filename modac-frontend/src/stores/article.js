import { ref } from "vue";
import { defineStore } from "pinia";

export const useArticleStore = defineStore(
  "article",
  () => {
    const articles = ref([]);
    const deleteArticle = (seq) => {
      articles.value = articles.value.filter((article) => article.seq !== seq);
    };

    const selectedArticleItemSeq = ref(null);

    // const articleContent = ref("");
    // const setArticleContent = (value) => {
    //   articleContent.value = value;
    // };

    const activeEditor = ref(undefined);

    const completeWriteArticleState = ref(false);
    const changeCompleteWriteArticleState = (state) => {
      completeWriteArticleState.value = state;
    };

    return {
      articles,
      deleteArticle,
      selectedArticleItemSeq,
      // articleContent,
      // setArticleContent,
      activeEditor,
      completeWriteArticleState,
      changeCompleteWriteArticleState,
    };
  },
  {
    persist: true,
  }
);
