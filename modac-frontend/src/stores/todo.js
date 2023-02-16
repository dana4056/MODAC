import { ref, isRef, isReactive } from "vue";
import { defineStore } from "pinia";
// import todo from "../api/todo";

export const useTodoStore = defineStore(
  "todo",
  () => {
    const todos = ref([]);

    const addTodoItem = (inputTodoItem) => {
      todos.value.push(inputTodoItem);
    };

    const deleteTodoItem = (seq) => {
      console.log("deleteTodoItem ");
      todos.value = todos.value.filter((todo) => todo.seq !== seq);
    };

    const updateTodoItem = (seq, inputTodoItem) => {
      todos.value = todos.value.map((todo) => {
        console.log(todo);
        if (todo.seq === seq) {
          return inputTodoItem;
        } else {
          return todo;
        }
      });
      console.log(todos, isRef(todos), isReactive(todos));
    };

    const access_token = "";

    return {
      // api,
      todos,
      addTodoItem,
      deleteTodoItem,
      updateTodoItem,
      access_token,
    };
  }
  // {
  //   persist: true,
  // }
);
