<template>
  <div>
    <h1 class="my_color my_size">Hi my Name is</h1>
    {{ computedArticleList }}
    <ArticleListItem
        class="article-item"
        v-for="(article, index) in computedArticleList"
        :key="index"
        :article="article"
        />
    <form @submit.prevent="store.addArticleItem(test)">
      <input v-model="test" type="text">
      <button>추가하기</button>
    </form>
  </div>
  <div class="article-editor">

  </div>
</template>

<script setup>
import "./ArticleList.css"
import ArticleListItem from "./ArticleListItem.vue"
import { useArticleStore } from "../../stores/article";
import { useTodoStore } from "../../stores/todo";
import { ref, computed } from "vue"
const todostore = useTodoStore();
const articlestore = useArticleStore()
const test = ref("")
const payload = ref([])
const todos = todostore.todos.filter(todo => todo.status === 2);




const originalTodos = [{todos_seq: 1, status: 1}, {todos_seq: 1, status: 2}]

// 1. filter 사용해서 각 객체의 status가 2인 것인 새로운 배열 만들기
const filterTodos1 = todostore.todos.filter(todo => originalTodos.status === 2);

const filterTodos2 = originalTodos.filter((elem) => { 
    console.log(elem)
    return elem.status === 2
})
console.log('filterTodos1', filterTodos1)

// 2. 내가 추가로 넣고 싶은 데이터를 넣기

const newTodos = filterTodos2.map((elem) => {
  elem.isSubmit = false
  elem.public_type = 0
  return elem
})
console.log('newTodos', newTodos)





// const testarr = [
//   {users_seq : 1, todos_seq: 1, public_type: 0, filepath: "abc", isSubmit: true},
//   {users_seq : 1, todos_seq: 2, public_type: 1, filepath: "abcd", isSubmit: false},
//   {users_seq : 1, todos_seq: 3, public_type: 2, filepath: "abcde", isSubmit: true},
// ]

const createPayload = (arr) => {
  arr.forEach(function(elem) {
    const data = {
    users_seq: elem.users_seq,
    todos_seq: elem.seq,
    public_type:"int",
    filepath: "String",
    }
    payload.push(data)
  })
}

createPayload(temp)

seq:"Integer"
users_seq:"Integer"
categories_seq:"Integer",
title:"String",
status:"Integer",
total_second:"String"

// store에서 불러 와야 할 아이들
const computedArticleList = computed(() => {
  return store.articles
})

// 화면 생성 시 실행되어야 할 함수
store.getArticleList()

</script>

<style>


</style>