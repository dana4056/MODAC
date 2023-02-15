<script setup>
import ArticleButtonList from "@/domains/Article/ArticleButtonList.vue";
import { ref, toRefs } from "vue";
import articleAPI from "@/api/article";
import { useArticleStore } from "@/stores/article";
import { useUserStore } from "@/stores/user";
import { useTodoStore } from "@/stores/todo";

const completeWriteArticleState = ref(false);
const changeCompleteWriteArticleState = (state) => {
  completeWriteArticleState.value = state;
};

const requestCreateArticle = () => {
  const userStore = useUserStore();
  const { usersSeq } = toRefs(userStore);
  const articleStore = useArticleStore();
  const { selectedArticleItemSeq, publicTypeNumber } = toRefs(articleStore);
  const todosSeq = selectedArticleItemSeq.value;
  const publicType = publicTypeNumber.value;
  // const filepath = `/articles/${usersSeq.value}/${todosSeq.value}.md`;
  // 어떻게 각 seq에 맞는 content를 가져와서 filepath에 넣어줄 것인가?
  articleAPI.postArticle({ usersSeq, todosSeq, publicType, filepath });
};

const deleteTodoAndArticle = () => {
  const articleStore = useArticleStore();
  const { selectedArticleItemSeq, deleteArticle } = toRefs(articleStore);
  const todoStore = useTodoStore();
  const { deleteTodoItem } = toRefs(todoStore);

  deleteTodoItem(selectedArticleItemSeq); // todo만 지우면, article도 지워지는지 확인해보기
  // deleteArticle(selectedArticleItemSeq);
};

const handleClickCompleteWriteButton = () => {
  changeCompleteWriteArticleState(true);
  requestCreateArticle();
  // store 정리해주기
  deleteTodoAndArticle();
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
