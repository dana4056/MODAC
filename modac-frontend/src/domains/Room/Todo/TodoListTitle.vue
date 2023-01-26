<template lang="">
  <div :class="$style.list_title">
    <!-- <h1 class="text-3xl font-bold underline">TodoList Title</h1> -->
    <h1 style="
      font-weight: 700;
      font-size: 23px;
      line-height: 20px;
      color: black;
      padding: 5px;"
      class="flex justify-between items-center"
      >
      To-do List 
      <!-- <h1><font-awesome-icon icon="fa-solid fa-lock" /></h1> -->
      <!-- <button type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"> -->

      <button v-if="lockState"
      type="button" @click="changeLockState"
      class="center h-8 max-h-[32px] w-8 max-w-[32px] rounded-lg bg-pink-500 text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
      style="display:flex; align-items:center; justify-content:center;">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4">
          <path d="M18 1.5c2.9 0 5.25 2.35 5.25 5.25v3.75a.75.75 0 01-1.5 0V6.75a3.75 3.75 0 10-7.5 0v3a3 3 0 013 3v6.75a3 3 0 01-3 3H3.75a3 3 0 01-3-3v-6.75a3 3 0 013-3h9v-3c0-2.9 2.35-5.25 5.25-5.25z" />
        </svg>
      </button>
      <button v-else
      type="button" @click="changeLockState"
      class="center h-8 max-h-[32px] w-8 max-w-[32px] rounded-lg bg-pink-500 text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
      style="display:flex; align-items:center; justify-content:center;">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4">
          <path fill-rule="evenodd" d="M12 1.5a5.25 5.25 0 00-5.25 5.25v3a3 3 0 00-3 3v6.75a3 3 0 003 3h10.5a3 3 0 003-3v-6.75a3 3 0 00-3-3v-3c0-2.9-2.35-5.25-5.25-5.25zm3.75 8.25v-3a3.75 3.75 0 10-7.5 0v3h7.5z" clip-rule="evenodd" />
        </svg>
      </button>



    </h1>
    <!-- <h1 class="custom_title">TITLE2</h1>
    <h1 class="my_title">TITLE3</h1>
    <h1 :class="$style.modules_title">TITLE 4</h1>
    <h1 class="custom_title">TITLE 5</h1> -->
  </div>
</template>
<script setup>
  import { ref } from "vue";

  let lockState = ref(false);
  const changeLockState = () => {
    lockState.value = !lockState.value;
  }
</script>
<style module>
@import "./TodoListTitle.css";
</style>
