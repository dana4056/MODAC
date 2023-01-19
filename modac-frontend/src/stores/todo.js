import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useTodoStore = defineStore("todo", () => {
  const todos = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");

  let access_token = ref(""); // github access token

  const addTodoItem = (inputTodoItem) => {
    console.log(inputTodoItem);
    todos.push(inputTodoItem);
  };

  const getTodoList = () => {
    console.log(2);
    axios({
      method: "get",
      url: `${backendServerUrl}/todo/list`,
      params: {
        users_seq: userId,
      },
    })
      .then((res) => {
        console.log(res.data);
        todos.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const createTodoListItem = () => {
    console.log("post request 실행");
    axios({
      methods: "post",
      url: `${backendServerUrl}/todo`,
      data: {},
    });
  };

  return {
    todos,
    addTodoItem,
    backendServerUrl,
    getTodoList,
    createTodoListItem,
    access_token
  };
});
