<script setup>
import { defineProps, computed, useCssModule } from "vue";

const props = defineProps({
  chatLog: Object,
  loginUser: Object,
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

  const h = props.chatLog.sendTime.slice(11,13);
  const m = props.chatLog.sendTime.slice(14,16);
  const s = props.chatLog.sendTime.slice(17);

  return h+":"+m;
})

</script>

<template>
  <div :class="sizeStyle">
    <small :class="$style.date_time">{{ dateStyle }}</small>
    <div :class="contentStyle">{{ props.chatLog.message }}</div>
    <div>
      <small v-if="props.chatLog.user.nickname != props.loginUser.nickname">{{props.chatLog.user.nickname}}</small>
      <img
        src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
        :class="$style.chat_profile"
        alt=""
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "ChatListItem.module.css";
</style>
