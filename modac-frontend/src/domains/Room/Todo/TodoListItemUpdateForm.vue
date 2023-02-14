<script setup>
import { defineProps, ref } from "vue";

const props = defineProps({
  toggleUpdateFormState: Function,
  isOpenUpdateFormState: Boolean,
  handleUpdateTodoItem: Function,
});

const title = ref("");
const category = ref("");

const handleCancleButton = () => {
  props.toggleUpdateFormState();
  title.value = "";
  category.value = "";
};
</script>

<template>
  <div v-if="props.isOpenUpdateFormState">
    <!-- <hr
          class="bg-white p-0 my-5 border-black border-0 border-t border-dashed"
        /> -->
    <div class="my-5">
      <form @submit.prevent="props.handleUpdateTodoItem(title, category)">
        <div
          class="w-full gap-7 max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
        >
          <!-- 할 일 작성 -->
          <div class="pb-3 m-0.5 w-full">
            <label
              class="block mb-2 text-sm font-semibold text-gray-900 dark:text-white"
              >할 일</label
            >
            <input
              type="text"
              v-model="title"
              id="title"
              autofocus
              :class="$style.title_input"
              placeholder="오늘의 공부는?"
              required
            />
          </div>

          <!-- 카테고리 선택 -->
          <div class="pb-3 m-0.5 w-full">
            <label
              class="block mb-2 text-sm font-semibold text-gray-900 dark:text-white"
              >카테고리 선택</label
            >
            <select
              id="categories_seq"
              v-model="category"
              :class="$style.select"
            >
              <option value="알고리즘" selected="selected">알고리즘</option>
              <option value="CS">CS</option>
              <option value="개발">개발</option>
              <option value="기획">기획</option>
              <option value="기타">기타</option>
            </select>
          </div>

          <div class="inline-flex w-full">
            <div class="flex items-center justify-between w-full">
              <button @click="handleCancleButton" :class="$style.cancel_button">
                ❌ 취소
              </button>
            </div>
            <div class="flex items-center justify-between w-full">
              <button type="submit" :class="$style.update_button">
                ✔ 수정
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<style lang="css" module>
@import "TodoListItemUpdateForm.module.css";
</style>
