import { ref } from "vue";
import { defineStore } from "pinia";

export const useDmStore = defineStore("dm", () => {
  const directMessageRoomList = ref([
    { seq: 1, toUser: "민수" },
    { seq: 2, toUser: "다은" },
    { seq: 3, toUser: "동현" },
    { seq: 4, toUser: "지영" },
    { seq: 5, toUser: "용재" },
  ]);

  return { directMessageRoomList };
});
