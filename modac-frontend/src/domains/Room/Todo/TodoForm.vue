<template lang="">
  <div class="w-full">
    <button
      @click="showFullForm"
      class="text-white bg-gradient-to-r text-sm from-blue-900 to-pink-200 hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-purple-200 dark:focus:ring-purple-800 font-medium rounded-lg px-5 py-2 text-center mr-1 mb-1 w-full"
    >
      할 일 추가
    </button>

    <div v-if="openState">
      <form @submit.prevent="submitTodoHandler(newTodo)">
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
              v-model="newTodo.title"
              id="title"
              autofocus
              class="bg-gray-50 text-sm border border-gray-300 text-gray-900 rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
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
              v-model="newTodo.categories_seq"
              class="bg-gray-50 text-sm border border-gray-300 text-gray-900 rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            >
              <option value="0" selected="selected">알고리즘</option>
              <option value="1">CS</option>
              <option value="2">개발</option>
              <option value="3">기획</option>
              <option value="4">기타</option>
            </select>
          </div>

          <!-- 추가하기 버튼 -->
          <button
            type="submit"
            class="w-full text-sm text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg px-5 py-2.5 m-1 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          >
            + 추가하기
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useTodoStore } from "../../../stores/todo";
const newTodo = ref({
  title: "",
  categories_seq: 0,
});
const store = useTodoStore();

let openState = ref(false);
const showFullForm = () => {
  openState.value = !openState.value;
};

const submitTodoHandler = (newTodo) => {
  store.addTodoItem({
    users_seq: 1,
    title: newTodo.title,
    categories_seq: Number(newTodo.categories_seq),
    status: 0,
  });

  // todo 입력 후 입력칸을 초기화해주기 위함
  newTodo.title = "";
  newTodo.categories_seq = 0;

  if (openState.value == true) {
    showFullForm();
  }
};
</script>

<style lang=""></style>
