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

    const activeEditor = ref({});

    const completeWriteArticleState = ref(true);
    const changeCompleteWriteArticleState = (state) => {
      completeWriteArticleState.value = state;
    };

    const access_token = ref("");

    return {
      articles,
      deleteArticle,
      selectedArticleItemSeq,
      // articleContent,
      // setArticleContent,
      activeEditor,
      completeWriteArticleState,
      changeCompleteWriteArticleState,
      access_token,
    };
  },
  {
    persist: true,
  }
);
