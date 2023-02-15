import http from "@/api/http";
import { useNotiStore } from '@/stores/notification';
import { storeToRefs } from "pinia";

export default {
  
  // 노티 조회
  getNoti(payload) {
    http.get(`/notifications/list`, {
      params:{
        user: payload.usersSeq,
      }})
      .then((response) => {
          console.log("알람 받기 성공")
          const notiStore = useNotiStore();
          const { notiList } = storeToRefs(notiStore);
          console.log(notiList.value)
          notiList.value = response.data;
      })
      .then(() => {
        this.readNoti(payload)
      })
      .catch((error) => {
        console.log(error);
      });
  },
  // 노티 읽기
  readNoti(payload) {
    http
      .put(`/notifications`, payload.usersSeq, {
        headers: {
            'Content-Type': 'text/plain'
        }})
      .then((response) => {
        const code = response.status;
        console.log(code)
      })
      .catch((error) => {
        console.log(error);
      });
  },
};