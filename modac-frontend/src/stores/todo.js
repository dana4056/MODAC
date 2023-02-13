import { ref } from "vue";
import { defineStore } from "pinia";
// import todo from "../api/todo";

export const useTodoStore = defineStore("todo", () => {
  const todos = ref([]);

  const addTodoItem = (inputTodoItem) => {
    todos.value.push(inputTodoItem);
  };

  const deleteTodoItem = (seq) => {
    todos.value = todos.value.filter((todo) => todo.seq !== seq);
  };

  const updateTodoItem = (seq, inputTodoItem) => {
    todos.value = todos.value.map((todo) => {
      if (todo.seq === seq) {
        return inputTodoItem;
      } else {
        return todo;
      }
    });
    console.log(todos.value);
  };

  // const access_token = "";
  const access_token = "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";

  return {
    // api,
    todos,
    addTodoItem,
    deleteTodoItem,
    updateTodoItem,
    access_token,
  };
});
