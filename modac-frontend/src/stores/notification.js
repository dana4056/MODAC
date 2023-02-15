import { defineStore } from "pinia";
import { ref } from "vue";
import notification from "../api/notification";

export const useNotiStore = defineStore("noti", () => {
  const notiList = ref([])
  const api = notification;

  return {
    api,
    notiList,
  };
});
