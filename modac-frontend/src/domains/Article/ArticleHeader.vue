<script setup>
import ArticleButtonList from "@/domains/Article/ArticleButtonList.vue";
import { ref, toRefs } from "vue";
import { useArticleStore } from "@/stores/article";
import { useUserStore } from "@/stores/user";
import { useTodoStore } from "@/stores/todo";
import articleAPI from "@/api/article";
import todoAPI from "@/api/todo";

const articleStore = useArticleStore();
const { completeWriteArticleState, changeCompleteWriteArticleState } =
  toRefs(articleStore);

const requestCreateArticle = async () => {
  const userStore = useUserStore();
  const { loginUser } = toRefs(userStore);
  const usersSeq = loginUser.value.seq;
  const articleStore = useArticleStore();
  const { selectedArticleItemSeq, activeEditor } = toRefs(articleStore, "activeEditor");
  const todosSeq = selectedArticleItemSeq.value;
  const publicType = publicTypeSelectedValue.value;

  const currentActiveEditor = activeEditor.value;
  const content = currentActiveEditor.getMarkdown();
  console.log("content:",content)
  await articleAPI.postArticle({
    usersSeq,
    todosSeq,
    publicType,
    content,
  });
};

const deleteTodoAndArticle = async () => {
  const articleStore = useArticleStore();
  const { selectedArticleItemSeq, deleteArticle, completeWriteArticleState } = toRefs(articleStore);
  const todoStore = useTodoStore();
  const { deleteTodoItem } = toRefs(todoStore);
  deleteTodoItem.value(selectedArticleItemSeq.value);
  deleteArticle.value(selectedArticleItemSeq.value);

  await todoAPI.deleteTodo(selectedArticleItemSeq.value);
};

const handleClickCompleteWriteButton = async () => {
  const articleStore = useArticleStore();
  const { selectedArticleItemSeq, completeWriteArticleState } = toRefs(articleStore);
  
  await requestCreateArticle();
  await deleteTodoAndArticle();
  completeWriteArticleState.value = true;
};

const publicTypeSelectedValue = ref(1);
</script>

<template>
  <div :class="$style.flex_row_div">
    <div :class="$style.flex_row">
      <h1 :class="$style.page_title">TIL 작성하기</h1>
    </div>

    <div v-if="!completeWriteArticleState" :class="$style.buttons">
      <div :class="$style.add_room_div">
        <select
          :class="$style.add_room_input"
          v-model="publicTypeSelectedValue"
          id="publicType"
        >
          <option value="1" selected="selected">공개</option>
          <option value="0">비공개</option>
        </select>
      </div>

      <button
        @click="handleClickCompleteWriteButton"
        type="button"
        :class="$style.button_next"
      >
        작성 완료
      </button>
    </div>

    <ArticleButtonList v-else />
  </div>
</template>

<style lang="css" module>
@import "ArticleHeader.module.css";
</style>
