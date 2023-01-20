<template lang="">
  <div>
    <br>
    <div style="text-align:right;"><br>
      <button @click="sendArticle" style="">[TIL 작성하러 가기 ➡]</button> 
    </div>

    <div v-for="(todo, todos_seq) in todos" :key="todos_seq">
    {{todo}}
    </div>

    <p>======</p>

    <div v-for="(todo, todos_seq) in todos" :key="todos_seq">
      <TodoListItem :todo="todo" :seq="todos_seq"/>
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


const sendArticle = () => {

  const completedTodos = todos.value.filter((elem) => {
      // console.log(`${index} 번째 : ${elem.status}`)
      return elem.status === 2
    })

    console.log("completedTodos : ")
    // console.log(completedTodos) // 이건 완료된 녀석들
    
    for (const completed in completedTodos) {
      console.log(completedTodos[completed]);
    }

  const notCompletedTodos = todos.value.filter((elem) => {
      // console.log(`${index} 번째 : ${elem.status}`)
      return elem.status !== 2
    })

    console.log("notCompletedTodos : ")
    // console.log(notCompletedTodos) // 이건 미완료된 녀석들
    
    for (const notCompleted in notCompletedTodos) {
      console.log(notCompletedTodos[notCompleted]);
    }

  }
</script>

<style lang=""></style>