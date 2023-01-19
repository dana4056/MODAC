<template lang="">
  <div>
    <!-- <p>할 일을 입력하세요 :</p> -->
    <form @submit.prevent="submitTodoHandler(newTodo)">
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

      <div>
        <label for="select-item">Todo 카테고리 :</label>

        <div class="relative">
          <select
            class="w-full border px-4 pr-8 py-2 rounded focus:border-blue-500 focus:shadow-outline outline-none appearance-none"
            id="category"
            v-model="newTodo.category"
          >
            <option value="algorithm" selected="selected">알고리즘</option>
            <option value="CS">CS</option>
            <option value="programming">개발</option>
            <option value="product management">기획</option>
            <option value="ect">기타</option>
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
  category: "algorithm",
});
const store = useTodoStore();

const submitTodoHandler = (newTodo) => {
  store.addTodoItem(newTodo);
  newTodo.value.title = ''
}
// const buttonClickHandler = () => {
//   const document.querySelector("#dropdownMenuButton1")
// }
</script>

<style lang=""></style>
