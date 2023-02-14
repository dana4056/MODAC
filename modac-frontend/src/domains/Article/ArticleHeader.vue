<script setup>
import ArticleButtonList from "@/domains/Article/ArticleButtonList.vue";
import { ref } from "vue";
import { useArticleStore } from "@/stores/article";
import { storeToRefs } from "pinia";

const articleStore = useArticleStore();
const { buttonState } = storeToRefs(articleStore);

const publicTypeSelectedValue = ref(1);
const nextButtonState = () => {
  buttonState.value = true;
};
</script>

<template>
  <div :class="$style.flex_row_div">
    <div :class="$style.flex_row">
      <h1 :class="$style.page_title">TIL 작성하기</h1>
    </div>

    <div v-if="!buttonState" :class="$style.buttons">
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
        @click="nextButtonState"
        type="button"
        :class="$style.button_next"
      >
        작성 완료
      </button>
    </div>

    <ArticleButtonList />
  </div>
</template>

<style lang="css" module>
@import "ArticleHeader.module.css";
</style>
