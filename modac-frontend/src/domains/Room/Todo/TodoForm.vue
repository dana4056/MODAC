<template lang="">
  <div class="w-full">
    <button @click="showFullForm" :class="$style.show_form_button">
      할 일 추가
    </button>

    <div v-if="openState">
      <form @submit.prevent="submitTodoHandler">
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
              <option value="기획">기획</option>
              <option value="기타">기타</option>
            </select>
          </div>

          <!-- 추가하기 버튼 -->
          <button type="submit" :class="$style.add_button">+ 추가하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import todo from "@/api/todo";
import { ref } from "vue";
import { useTodoStore } from "../../../stores/todo";
import { useUserStore } from "@/stores/user";

const todoStore = useTodoStore();
const userStore = useUserStore();

const title = ref("");
const categoriesSeq = ref("알고리즘");
const submitTodoHandler = () => {
  const response = todo.postTodo(
    userStore.loginUser.seq,
    categoriesSeq.value,
    title.value
  );

  console.log("response: ", response);
  // store.addTodoItem({
  //   users_seq: 1,
  //   title: title.value,
  //   categories_seq: Number(newTodo.categories_seq),
  //   status: 0,
  // });

  // // todo 입력 후 입력칸을 초기화해주기 위함
  // newTodo.title = "";
  // newTodo.categories_seq = 0;

  // if (openState.value == true) {
  //   showFullForm();
  // }
};

let openState = ref(false);
const showFullForm = () => {
  openState.value = !openState.value;
};
</script>

<style lang="css" module>
@import "./TodoForm.module.css";
</style>
