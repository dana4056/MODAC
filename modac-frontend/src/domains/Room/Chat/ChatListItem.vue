<script setup>
import { defineProps, computed, useCssModule } from "vue";

const props = defineProps({
  chatLog: Object,
  loginUser: Object,
});

const $style = useCssModule();

const chatHostReader = computed(() => {
  if (props.chatLog.usersSeq === props.loginUser.seq) {
    return "myStyle";
  } else {
    return "yourChat";
  }
});

const sizeStyle = computed(() => {
  return chatHostReader.value === "myStyle"
    ? $style.mychat_box_size
    : $style.yourchat_box_size;
});

const contentStyle = computed(() => {
  return chatHostReader.value === "myStyle"
    ? $style.mychat_box_content
    : $style.yourchat_box_content;
});
</script>

<template>
  <div :class="sizeStyle">
    <div :class="contentStyle">{{ props.chatLog.message }}</div>
    <img
      src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
      :class="$style.chat_profile"
      alt=""
    />
  </div>
</template>

<style lang="css" module>
@import "ChatListItem.module.css";
</style>
