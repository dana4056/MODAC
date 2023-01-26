<template>
    <div id="wrap">
        <p>저장소 이름*</p>
        <input v-model="repoName" type="text">

        <p>설명</p>
        <input v-model="repoName" type="text">

        <span>비공개 설정 </span>
        <input type="checkbox" v-model="isPrivate">

        <button @click="createRepo">생성하기</button>
    </div>
</template>
    
<script setup>
import axios from "axios";
import {useTodoStore} from "../stores/todo.js"
import {ref} from "vue";
import { useRoute } from 'vue-router'
import router from "../router/index"

const GITHUB_API_SERVER = "https://api.github.com"

let repoName="";
let repoDes = "";
let isPrivate = false;

const store = useTodoStore();
const route = useRoute();

const http = axios.create({
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/vnd.github+json"
    },
});



function createRepo(){

    const body = {
        name:repoName,
        description:repoDes,
        private: isPrivate,
    }
    
    const headers={
        "Authorization" : "Bearer "+ store.access_token
    }

    http.post(GITHUB_API_SERVER + "/user/repos", body, {headers})
    .then((response) => {
        alert("저장소가 생성되었습니다 :->");
        writeCommitMSG(body.name);
    })
    .catch((err) => console.log(err));
}

function writeCommitMSG(repo){
    const user = route.params.user;

    router.push(`/commit/${user}/${repo}`)
}

</script>

<style scoped>
#wrap{
    text-align: center;
}
input, textarea{
    margin: 0 auto;
    /* display: block; */
    border: 1px gray solid;
}
button{
    background-color: rgb(82, 156, 134);
}
</style>