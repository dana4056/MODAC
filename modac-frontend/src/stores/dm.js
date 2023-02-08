import { ref } from "vue";
import { defineStore } from "pinia";

export const useDmStore = defineStore("dm", () => {
  const directMessageRoomList = ref([
    { seq: 1, toUser: "민수", logs: [] },
    { seq: 2, toUser: "다은", logs: [] },
    { seq: 3, toUser: "동현", logs: [] },
    { seq: 4, toUser: "지영", logs: [] },
    { seq: 5, toUser: "용재", logs: [] },
  ]);

  const liveDirectMessageRoomNumber = ref(0);

  return { directMessageRoomList, liveDirectMessageRoomNumber };
});
