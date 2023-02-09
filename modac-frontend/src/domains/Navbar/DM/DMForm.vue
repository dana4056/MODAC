<!-- <script setup>
import { defineProps, ref } from "vue";
const props = defineProps({
  enterChat: Function,
});

const inputChatMessage = ref("");

defineExpose({
  inputChatMessage
})
</script>

<template>
  <div>
    <form action="" @submit.prevent="props.enterChat(inputChatMessage)">
      <input
        :class="$style.chat_input_box"
        v-model="inputChatMessage"
        type="text"
        placeholder="채팅을 입력하세요."
      />
    </form>
  </div>
</template>

<style lang="css" module>
@import "ChatForm.module.css";
/* @import "DMForm.module.css"; */
</style> -->
