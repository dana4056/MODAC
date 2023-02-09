import { defineStore } from "pinia";
import { ref } from "vue";
import todo from "../api/todo";

export const useTodoStore = defineStore("todo", () => {
    //status (변수)
    
    //action (API)
    const api = todo;   // axois 비동기 함수들

    return {
        api,    
    };
});
