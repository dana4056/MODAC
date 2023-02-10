import { defineStore } from "pinia";
import { ref } from "vue";
import article from "../api/article";

export const useArticleStore = defineStore("article", () => {
    //status (변수)
    
    //action (API)
    const api = article;   // axois 비동기 함수들

    return {
        api,    
    };
});
