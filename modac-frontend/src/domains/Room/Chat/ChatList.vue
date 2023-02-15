<script setup>
import { storeToRefs } from "pinia";
import ChatForm from "./ChatForm.vue";
import ChatListItem from "./ChatListItem.vue";
import { useChatStore } from "@/stores/chat";
import { useUserStore } from "@/stores/user";
import { useRoomStore } from "@/stores/room";
import { ref, onMounted } from "vue";
import OverflowDiv from "@/components/OverflowDiv.vue";

const chatStore = useChatStore();
const userStore = useUserStore();
const roomStore = useRoomStore();

const { groupChatLogs } = storeToRefs(chatStore);
const { loginUser } = storeToRefs(userStore);
const { chatListElement, chatFormElement } = storeToRefs(roomStore);

onMounted(() => {
  chatListElement.value = document.getElementById("chatbox_body");
  roomStore.liftMessage();
});

</script>

<template>
  <OverflowDiv :class="$style.chatbox_body_size" id="chatbox_body">
    <ChatListItem
      v-for="chatLog in groupChatLogs"
      :key="chatLog.seq"
      :chatLog="chatLog"
      :loginUser="loginUser"
    />
  </OverflowDiv>
  <ChatForm ref="chatFormElement" />
</template>

<style lang="css" module>
@import "ChatList.module.css";
</style>
