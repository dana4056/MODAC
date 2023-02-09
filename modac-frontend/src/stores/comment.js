import { defineStore } from "pinia";
import { ref } from "vue";
import comment from "../api/comment";

export const useCommentStore = defineStore("comment", () => {
    //status (변수)
    
    //action (API)
    const api = comment;   // axois 비동기 함수들

    return {
        api,    
    };
});
