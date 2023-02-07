<script setup>
import ChatList from "./ChatList.vue";
import RightSideBarHeader from "../Sidebar/RightSideBarHeader.vue";
import RightSideBarBody from "../Sidebar/RightSideBarBody.vue";
</script>

<template>
  <RightSideBarHeader>스터디룸 채팅</RightSideBarHeader>
  <RightSideBarBody>
    <ChatList />
  </RightSideBarBody>
</template>

<style lang="css" module>
@import "./Chat.module.css";
</style>
