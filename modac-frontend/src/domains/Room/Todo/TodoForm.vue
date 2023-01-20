<template lang="">
  <div>

    <form @submit.prevent="submitTodoHandler(newTodo)">
      <!-- Todo title input -->
      <div>
        <label for="input1">Todo 제목 :</label>
        <input
          id="title"
          v-model="newTodo.title"
          class="w-full border px-4 py-2 rounded focus:border-blue-500 focus:shadow-outline outline-none"
          type="text"
          placeholder="TODO..."
          autofocus
        />
      </div>

      <!-- Todo categories_seq dropdown -->
      <div>
        <label for="select-item">Todo 카테고리 :</label>

        <div class="relative">
          <select
            class="w-full border px-4 pr-8 py-2 rounded focus:border-blue-500 focus:shadow-outline outline-none appearance-none"
            id="categories_seq"
            v-model="newTodo.categories_seq"
          >
            <option value=0 selected="selected">알고리즘</option>
            <option value=1>CS</option>
            <option value=2>개발</option>
            <option value=3>기획</option>
            <option value=4>기타</option>
          </select>

          <div
            class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700"
          >
            <svg
              class="fill-current h-4 w-4"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
            >
              <path
                d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
              />
            </svg>
          </div>
        </div>
      </div>

      <!-- Submit button -->
      <div class="flex items-center justify-between">
        <button
          class="bg-blue-600 hover:bg-black text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
          type="submit"
        >
          +
        </button>
      </div>
    </form>
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

const submitTodoHandler = (newTodo) => {
  store.addTodoItem({
    "users_seq" : 1,
    "title" : newTodo.title, 
    "categories_seq" : Number(newTodo.categories_seq),
    "status" : 0
    // "completed" : false,
    });

  // todo 입력 후 입력칸을 초기화해주기 위함 
  newTodo.title = "";
  newTodo.categories_seq = 0;
}
// const buttonClickHandler = () => {
//   const document.querySelector("#dropdownMenuButton1")
// }
</script>

<style lang=""></style>
