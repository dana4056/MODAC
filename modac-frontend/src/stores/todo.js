import { ref } from "vue";
import { defineStore } from "pinia";
import todo from "../api/todo";

export const useTodoStore = defineStore("todo", () => {
  // const api = todo; // axois 비동기 함수들

  let todos = ref([]);

  const addTodoItem = (inputTodoItem) => {
    // 통신 후에 넣어주는 정보들 임시로 여기서 추가
    console.log(inputTodoItem);

    // Parse Data
    inputTodoItem = {
      seq: Number(todos_seq.value),
      users_seq: Number(inputTodoItem.users_seq),
      categories_seq: Number(inputTodoItem.categories_seq),
      title: inputTodoItem.title,
      status: Number(inputTodoItem.status),
      total_second: "0",
    };

    // todos에 추가
    todos.value.push(inputTodoItem);
  };

  // const access_token = "";
  const access_token = "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";

  return {
    // api,
    todos,
    addTodoItem,
    access_token,
  };
});
