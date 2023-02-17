<script setup>
import todoAPI from "@/api/todo";
import { ref } from "vue";
import { useTodoStore } from "../../../stores/todo";
import { useUserStore } from "@/stores/user";

const todoStore = useTodoStore();
const userStore = useUserStore();

const title = ref("");
const categoriesSeq = ref("알고리즘");
const setTitle = () => {
  title.value = "";
};
const setCategoriesSeq = () => {
  categoriesSeq.value = "알고리즘";
};

const handleSubmitTodoItem = async () => {
  const responseData = await todoAPI.postTodo(
    userStore.loginUser.seq,
    categoriesSeq.value,
    title.value
  );

  setTitle();
  setCategoriesSeq();

  console.log(responseData);
  todoStore.addTodoItem(responseData);

  toggleTodoCreateForm();
};

let openState = ref(false);
const toggleTodoCreateForm = () => {
  openState.value = !openState.value;
};
</script>
<template lang="">
  <div class="w-full">
    <button @click="toggleTodoCreateForm" :class="$style.show_form_button">
      할 일 추가
    </button>

    <div v-if="openState">
      <form @submit.prevent="handleSubmitTodoItem">
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
              :class="$style.input_style"
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
              v-model="categoriesSeq"
              :class="$style.select"
            >
              <option value="알고리즘" selected="selected">알고리즘</option>
              <option value="CS">CS</option>
              <option value="개발">개발</option>
              <option value="면접">면접</option>
              <option value="공통">공통</option>
            </select>
          </div>

          <!-- 추가하기 버튼 -->
          <button type="submit" :class="$style.add_button">+ 추가하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style lang="css" module>
@import "./TodoCreateForm.module.css";
</style>
