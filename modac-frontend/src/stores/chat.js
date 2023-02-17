import { defineStore } from "pinia";
import { ref } from "vue";

export const useChatStore = defineStore("chat", () => {
  // status (변수)
  const groupChatLogs = ref([]);    // 단체 채팅 로그(한 방에 대하여)

  // action (API)
  // 단체 채팅방에서는 DB에 저장하지 않아서 API 사용X

  return {
    groupChatLogs,
  };
},
// {
//   persist: true,
// }
);
