<template lang="">
  <div class="w-full h-full flex flex-col-reverse justify-between">

    <div style="display:flex;
                flex-direction: column;
                align-items: flex-start;
                padding: 0px;
                gap: 7px;

                flex: none;
                order: 1;
                align-self: stretch;
                flex-grow: 1;
                white-space: nowrap;
                overflow: auto;
                word-break:break-all;
                height: 60vh;"
                :class="$style.scrollBar">
      <div v-for="todo in todos" :key="todo.seq" class="w-full">
        <TodoListItem :todo="todo" @nowPlay="nowPlay" :id="`todo_` + todo.seq" ref="TodoListItemComponent" />
      </div>

    </div>
      <div style="text-align:right;"><br>
        <button @click="sendArticle" 
        class="text-white bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2">
          오늘 공부 정리하기
        </button>
      </div>

  </div>
</template>

<script setup>
import { useTodoStore } from "../../../stores/todo";
import { ref } from "vue";
import TodoListItem from "./TodoListItem.vue";

const store = useTodoStore();
const todos = ref([]);
todos.value = store.todos;

const TodoListItemComponent = ref();
// onMounted(() => {
//   TodoListItemComponent.value.stop();
// })

let nowPlayState = -1;
const nowPlay = (index) => {
  // console.log(todos);
  if (nowPlayState != -1) {
    // 기존에 재생중이던 seq를 가진 props로 stop 명령 보내기
    // todos[nowPlayState].stop();
    // const prevPlay = document.getElementById(`todo_` + nowPlayState);
    // stop.value.stop();
    console.log("===========");
    // console.log(todos.value);
    // console.log(nowPlayState);
    // console.log(todos.value[nowPlayState]);
    // // console.log(todos.value[nowPlayState]);
    // TodoListItemComponent.value.stop();
    console.log("===========");
    // todos.value[nowPlayState].stop();
  }

  nowPlayState = index;
  console.log("nowPlayState");
  console.log(nowPlayState);
};

const deleteTodoItem = (deleteTodoItem) => {
  store.deleteTodoItem(deleteTodoItem);
}


const sendArticle = () => {
  // 완료된 투두 목록
  const completedTodos = todos.value.filter((elem) => {
    // console.log(`${elem} 번째 : ${elem.status}`)
    return elem.status === 2
  })

  // console.log("completedTodos : ")
  
  // for (const completed in completedTodos) {
  //   console.log(completedTodos[completed]);
  // }



  // 미완료된 투두 목록, 당장은 활용 X
  // const notCompletedTodos = todos.value.filter((elem) => {
  //   // console.log(`${index} 번째 : ${elem.status}`)
  //   return elem.status !== 2
  // })

  // console.log("notCompletedTodos : ")
  
  // for (const notCompleted in notCompletedTodos) {
  //   console.log(notCompletedTodos[notCompleted]);
  // }

  if (confirm("TIL을 작성하러 가시겠습니까?") == true) {
    // 확인
    console.log(completedTodos);
    for (const completed in completedTodos) {
      deleteTodoItem(completedTodos[completed]);
    }
  }
}
</script>

<style module>
  @import "./TodoList.css";
</style>