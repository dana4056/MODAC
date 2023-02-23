<script setup>
import { ref } from "vue";
import { useRoomStore } from "@/stores/room";

const roomStore = useRoomStore();

const inputChatMessage = ref("");

defineExpose({
  inputChatMessage
})

</script>

<template>
  <div>
    <form action="" @submit.prevent="roomStore.enterChat(inputChatMessage)">
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
</style>
