<script setup>
import CategoryTag from "@/components/CategoryTag.vue";
</script>

<template>
  <h1>멀티룸 리스트</h1>
  <CategoryTag>CS</CategoryTag>
  <button>멀티룸 생성하기 -></button>
  <input type="text" placeholder="원하는 방을 검색해보세요." />
</template>

<style lang="css" module>
@import "./RoomListHeader.module.css";
</style>
