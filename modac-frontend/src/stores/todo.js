import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useTodoStore = defineStore("todo", () => {
  const todos = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");

  let access_token = ref(""); // github access token

  const todos_seq = ref(0);

  const addTodoItem = (inputTodoItem) => {
    console.log("inputTodoItem");
    console.log(inputTodoItem);
    
    // 통신 후에 넣어주는 정보들 임시로 여기서 추가
    inputTodoItem = {
      seq: Number(todos_seq.value),
      users_seq: Number(userId),
      categories_seq: Number(inputTodoItem.categories_seq),
      title: inputTodoItem.title,
      status: Number(inputTodoItem.status),
      total_second: "0"
    }
    
    todos.push(inputTodoItem);
    
    todos_seq.value = todos_seq.value + 1;
  };

  const getTodoList = () => {
    console.log("getTodoList 실행");
    axios({
      method: "get",
      url: `${backendServerUrl}/todo/list`,
      params: {
        users_seq: userId,
      },
    })
      .then((res) => {
        console.log("res.data : ");
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
      methods: 'post',
      url: `${backendServerUrl}/todo`,
      data: {},
    });
  };

  const deleteTodoItem = (deleteTodoItemSeq) => {
    console.log("delete deleteTodoItemSeq");
    console.log(deleteTodoItemSeq);
    todos.splice(deleteTodoItemSeq, 1);
  };

  const updateTodoItem = (changeTodoItem) => {
    console.log("update changeTodoItem");
    console.log(changeTodoItem);
    todos.splice(
      changeTodoItem.seq,
      1,
      {
        seq: Number(changeTodoItem.seq),
        users_seq: Number(userId),
        categories_seq: Number(changeTodoItem.categories_seq),
        title: changeTodoItem.title,
        status: Number(changeTodoItem.status),
        // completed: changeTodoItem.completed,
        total_second: changeTodoItem.total_second,
      }
    );
  };

  return {
    todos,
    addTodoItem,
    backendServerUrl,
    getTodoList,
    createTodoListItem,
    access_token,
    deleteTodoItem,
    updateTodoItem,
    todos_seq,
  };
});
