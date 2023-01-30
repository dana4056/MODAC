<template>
    <div id="wrap">
        <p>file 이름을 작성해주세요</p>
        <input v-model="title" type="text">

        <p>commit message를 작성해주세요</p>
        <textarea v-model="commitMSG"></textarea>
        <button @click="commitToRepo">commit to remote repository</button>
    </div>
</template>
    
<script setup>
import axios from "axios";
import {useTodoStore} from "../stores/todo.js"
import {ref} from "vue";
import { useRoute } from 'vue-router'
import router from "../router/index"

let title="";
let commitMSG = "";

const store = useTodoStore();
const route = useRoute();

const http = axios.create({
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/vnd.github+json"
    },
});



function commitToRepo(){
    alert("msg:"+commitMSG);
    const GITHUB_API_SERVER = "https://api.github.com"
    const user = route.params.user;
    const repo = route.params.repo;

    const headers={
        "Authorization" : "Bearer "+ store.access_token
    }

    const body = {
        "message": commitMSG,
        "content":  "bXkgbmV3IGZpbGUgY29udGVudHM="
    }

    http.put(GITHUB_API_SERVER + `/repos/${user}/${repo}/contents/${title}`, body, {headers})
    .then((response) => {
        alert("원격저장소에 정상적으로 Commit 되었습니다 :->")
        router.push(`/repo/${user}/${repo}`)
    })
    .catch((err) => console.log(err));

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