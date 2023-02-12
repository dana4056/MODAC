<script setup>
import TodoListTitle from "@/domains/Room/Todo/TodoListTitle.vue";
import TodoListLockButton from "../Todo/TodoListLockButton.vue";
import TodoList from "@/domains/Room/Todo/TodoList.vue";
import TodoCreateForm from "@/domains/Room/Todo/TodoCreateForm.vue";
import RightSideBarHeader from "./RightSideBarHeader.vue";
import RightSideBarBody from "./RightSideBarBody.vue";
</script>

<template>
  <div :class="$style.content">
    <RightSideBarHeader class="w-full flex">
      <TodoListTitle />
      <template v-slot:button>
        <TodoListLockButton />
      </template>
    </RightSideBarHeader>
    <RightSideBarBody>
      <TodoCreateForm />
      <TodoList />
    </RightSideBarBody>
  </div>
</template>

<style lang="css" module>
@import "./LeftSideBarContent.module.css";
</style>
