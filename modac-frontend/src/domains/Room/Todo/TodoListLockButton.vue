<script setup>
import { ref } from "vue";

// 현재 하고 있는 일을 공개할지 비공개할지에 대한 상태
let lockState = ref(false);
const changeLockState = () => {
  lockState.value = !lockState.value;
};
</script>

<template>
  <button v-if="lockState" @click="changeLockState" :class="$style.lock_button">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      fill="currentColor"
      class="w-4 h-4"
    >
      <path
        d="M18 1.5c2.9 0 5.25 2.35 5.25 5.25v3.75a.75.75 0 01-1.5 0V6.75a3.75 3.75 0 10-7.5 0v3a3 3 0 013 3v6.75a3 3 0 01-3 3H3.75a3 3 0 01-3-3v-6.75a3 3 0 013-3h9v-3c0-2.9 2.35-5.25 5.25-5.25z"
      />
    </svg>
  </button>
  <button v-else @click="changeLockState" :class="$style.lock_button2">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      fill="currentColor"
      class="w-4 h-4"
    >
      <path
        fill-rule="evenodd"
        d="M12 1.5a5.25 5.25 0 00-5.25 5.25v3a3 3 0 00-3 3v6.75a3 3 0 003 3h10.5a3 3 0 003-3v-6.75a3 3 0 00-3-3v-3c0-2.9-2.35-5.25-5.25-5.25zm3.75 8.25v-3a3.75 3.75 0 10-7.5 0v3h7.5z"
        clip-rule="evenodd"
      />
    </svg>
  </button>
</template>

<style lang="css" module>
@import "TodoListLockButton.module.css";
</style>
