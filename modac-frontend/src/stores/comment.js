// import { reactive, ref } from "vue";
import { ref } from "vue";
import { defineStore } from "pinia";
// import axios from "axios";
import comment from "../api/comment";

export const useCommentStore = defineStore("comment", () => {
  //status (변수)
  // let comments = reactive([]);
  // const userId = ref(null);
  // const backendServerUrl = ref("localhost");
  let commentTotalCnt = ref(0);

  //action (API)
  const api = comment;   // axois 비동기 함수들
  
  const comments = ref([]);

  // const getComments = (feedSeq) => {
    

  //   return comments;
  // }

    return {
      comments,
      commentTotalCnt,
      // getComments,
      api,
    };
  }
);
