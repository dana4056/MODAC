<script setup>
import { defineProps, computed, useCssModule, ref } from "vue";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";

const props = defineProps({
  chatLog: Object,
  loginUser: Object,
  // catskin: Number
});

const $style = useCssModule();

const chatHostReader = computed(() => {
  if (props.chatLog.user.seq === props.loginUser.seq) {
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

const dateStyle = computed(() => {
  const h = props.chatLog.sendTime.slice(11, 13);
  const m = props.chatLog.sendTime.slice(14, 16);
  const s = props.chatLog.sendTime.slice(17);

  return h + ":" + m;
});

const profileImgUrl = ref(
  BASE_ASSETS_URL + "user_cats/cat" + props.loginUser.catSkin + ".png"
);
</script>

<template>
  <div :class="sizeStyle">
    <small :class="$style.date_time">{{ dateStyle }}</small>
    <div :class="contentStyle">{{ props.chatLog.message }}</div>
    <div>
      <small v-if="props.chatLog.user.nickname != props.loginUser.nickname">{{
        props.chatLog.user.nickname
      }}</small>
      <img
        v-if="props.chatLog.user.nickname != props.loginUser.nickname"
        :src="profileImgUrl"
        :class="$style.chat_profile"
        alt=""
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "ChatListItem.module.css";
</style>
