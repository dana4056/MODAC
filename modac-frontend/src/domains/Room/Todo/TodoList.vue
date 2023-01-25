<template lang="">
  <div>
    <br>
    <div style="text-align:right;"><br>
      <button @click="sendArticle" style="">[TIL 작성하러 가기 ➡]</button>
    </div>

    <div v-for="todo in todos" :key="todo.seq">
      {{ todo }}
    </div>

    <p>======</p>

    <div v-for="todo in todos" :key="todo.seq">
      <TodoListItem :todo="todo" @nowPlay="nowPlay"/>
      <!-- <TodoListItem :todo="todo"/> -->
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

let nowPlayState = -1;
const nowPlay = (index) => {
  // console.log(todos);
  if (nowPlayState != -1) {
    // 기존에 재생중이던 seq를 가진 props로 stop 명령 보내기
    // todos[nowPlayState].stop();
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
    for (const completed in completedTodos) {
      deleteTodoItem(completedTodos[completed]);
    }
  }
}
</script>

<style lang=""></style>