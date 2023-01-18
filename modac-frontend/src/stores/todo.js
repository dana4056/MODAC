import { reactive, ref } from "vue";
import { defineStore } from "pinia";

export const useTodoStore = defineStore("todo", () => {
  const todos = reactive([]);

  const addTodoItem = (inputTodoItem) => {
    console.log(inputTodoItem);
    todos.push(inputTodoItem);
  };

  return { todos, addTodoItem };
});
