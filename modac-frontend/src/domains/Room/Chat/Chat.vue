<script setup>
import ChatHeader from "./ChatHeader.vue";
import ChatList from "./ChatList.vue";
</script>

<template>
  <!-- This is an example component -->
  <div :class="$style.chatbox">
    <ChatHeader />
    <ChatList />
  </div>
</template>

<style lang="css" module>
@import "./Chat.module.css";
</style>
