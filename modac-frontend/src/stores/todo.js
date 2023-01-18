import { reactive, ref } from "vue";
import { defineStore } from "pinia";

export const useTodoStore = defineStore("todo", () => {
  const todos = reactive([]);

  let access_token = ref(""); // github access token

  const addTodoItem = (inputTodoItem) => {
    console.log(inputTodoItem);
    todos.push(inputTodoItem);
  };

  return { todos, addTodoItem, access_token };
});
