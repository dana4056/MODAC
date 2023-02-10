import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useTodoStore = defineStore("todo", () => {
  let todos = ref([]);
  const userId = ref(-1);
  const backendServerUrl = ref("localhost");

  let access_token = ref(""); // github access token

  const todos_seq = ref(0);

  const addTodoItem = (inputTodoItem) => {
    // console.log("inputTodoItem");
    // console.log(inputTodoItem);

    // 통신 후에 넣어주는 정보들 임시로 여기서 추가
    inputTodoItem = {
      seq: Number(todos_seq.value),
      users_seq: Number(userId),
      categories_seq: Number(inputTodoItem.categories_seq),
      title: inputTodoItem.title,
      status: Number(inputTodoItem.status),
      total_second: "0",
    };

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
      methods: "post",
      url: `${backendServerUrl}/todo`,
      data: {},
    });
  };

  const deleteTodoItem = (deleteTodoItem) => {
    // console.log("delete deleteTodoItemSeq");
    // console.log(deleteTodoItem);

    // 방법 1. 몇 번째 인덱스인지 직접 보내서 삭제
    // todos.splice(deleteTodoItemSeq, 1);

    // 다른 방법 시도하던 흔적
    // const newTodoList = todos.filter((elem) => {
    //   return elem.seq != deleteTodoItemSeq
    // })

    // let newTodoList = todos.filter((elem) => {
    //   return elem.seq !== deleteTodoItemSeq
    // })

    // console.log("before");
    // console.log(todos);
    // console.log("newTodoList");
    // console.log(newTodoList);

    // todos = newTodoList;

    // console.log("after");
    // console.log(newTodoList);
    // console.log(todos);

    // 방법 2. 원하는 아이템과 일치하는 인덱스를 찾아서 그 값으로 삭제
    todos.splice(todos.indexOf(deleteTodoItem), 1);
    // updateTodoItem(todos);

    // 방법 3. todo.seq가 원하는 값인 인덱스를 찾아서 그 값으로 삭제
  };

  const updateTodoItem = (updateTodoItem) => {
    // console.log("update updateTodoItem");
    // console.log(updateTodoItem);

    // const index = (TodoList, seq) => {
    //   return TodoList.filter((elem) => {
    //     return elem.seq == seq;
    //   })
    // }

    todos.splice(updateTodoItem.index, 1, {
      seq: Number(updateTodoItem.seq),
      users_seq: Number(userId),
      categories_seq: Number(updateTodoItem.categories_seq),
      title: updateTodoItem.title,
      status: Number(updateTodoItem.status),
      total_second: updateTodoItem.total_second,
    });
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
