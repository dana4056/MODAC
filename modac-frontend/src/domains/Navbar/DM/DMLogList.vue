<script setup>
import { useDmStore } from "@/stores/dm";
import { useUserStore } from "@/stores/user"
import { ref , onMounted} from "vue";
import { storeToRefs } from "pinia";
import DMLogListItem from "@/domains/Navbar/DM/DMLogListItem.vue"
import DMForm from "@/domains/Navbar/DM/DMForm.vue"
// import Stomp from "webstomp-client";
// import SockJS from "sockjs-client/dist/sockjs.min.js";
import OverflowDiv from "@/components/OverflowDiv.vue";

const DMstore = useDmStore();
const userStore = useUserStore();

const { loginUser } = storeToRefs(userStore);
const { directChatLogs, chatListElement, chatFormElement } = storeToRefs(DMstore);


onMounted(() => {
  chatListElement.value = document.getElementById("chatbox_body");
  DMstore.liftMessage();
});



</script>

<template>
  <overflow-div id="chatbox_body" :class="$style.chatbox_content">
    <div :class="$style.chatbox_content" v-if="directChatLogs.length > 0">
      <DMLogListItem
        v-for="chatLog in directChatLogs"
        :key="chatLog.seq"
        :chatLog="chatLog"
        :loginUser="loginUser"
      />
    </div>
    <div v-else :class="$style.chatbox_none_text">채팅방을 클릭해주세요 =^._.^=</div>
  </overflow-div>
  <DMForm :enterChat="enterChat" ref="chatFormElement" :class="$style.form_box"/>
</template>


<style lang="css" module>
@import "DMLogList.module.css";
</style>
