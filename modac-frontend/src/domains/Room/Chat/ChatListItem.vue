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
  <!-- <div :class="$style.yourchat_box_size">
        <img
          src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
          :class="$style.chat_profile"
          alt=""
        />
        <div :class="$style.yourchat_box_content">동현아 아프지마</div>
      </div>
      <div :class="$style.mychat_box_size">
        <div :class="$style.mychat_box_content">
          형 그래도 가기전에 이거 채팅 소스 드리고 갈게요
          https://tailwindcomponents.com/component/chat-messages/landing
        </div>
        <img
          src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
          :class="$style.chat_profile"
          alt=""
        />
      </div> -->
</template>

<style lang="css" module>
@import "ChatListItem.module.css";
</style>
