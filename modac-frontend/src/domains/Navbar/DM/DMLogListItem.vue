<script setup>
import { defineProps, computed, useCssModule, ref } from "vue";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";

const props = defineProps({
  chatLog:Object,
  loginUser: Object,
});



const $style = useCssModule();

const dateStyle = computed(() => {

    const h = props.chatLog.sendTime.slice(11,13);
    const m = props.chatLog.sendTime.slice(14,16);
    const s = props.chatLog.sendTime.slice(17);

    return h+":"+m;
})


const chatHostReader = computed(() => {
  if (props.chatLog.userNickName === props.loginUser.nickname) {
    console.log("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁmystyle")
    return "myStyle";
  } else {
    console.log("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁyourChat")
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

const profileImgUrl = ref(
  BASE_ASSETS_URL + "user_cats/cat" + loginUser.value.catSkin + ".png"
);

</script>

<template>
  <div :class="sizeStyle">
    <small>{{ dateStyle }}</small>
    <div :class="contentStyle">{{ props.chatLog.message }}</div>
    <div>
      <small v-if="props.chatLog.userNickName !== props.loginUser.nickname">{{props.chatLog.userNickName}}</small>
      <img
        v-if="props.chatLog.userNickName !== props.loginUser.nickname"
        :src="profileImgUrl"
        :class="$style.chat_profile"
        alt=""
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "DMLogList.module.css";
</style>
