import { ref } from "vue";
import { defineStore } from "pinia";
import todo from "../api/todo";

export const useTodoStore = defineStore("todo", () => {
  const api = todo; // axois 비동기 함수들

  let todos = ref([]);

  const todos_seq = ref(0);

  const addTodoItem = (inputTodoItem) => {
    // 통신 후에 넣어주는 정보들 임시로 여기서 추가
    inputTodoItem = {
      seq: Number(todos_seq.value),
      users_seq: Number(inputTodoItem.users_seq),
      categories_seq: Number(inputTodoItem.categories_seq),
      title: inputTodoItem.title,
      status: Number(inputTodoItem.status),
      total_second: "0",
    };

    todos.value.push(inputTodoItem);

    todos_seq.value = todos_seq.value + 1;
  };

  return {
    api,
    todos,
    addTodoItem,
    todos_seq,
  };
});
