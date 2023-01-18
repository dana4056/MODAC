<template>
    <div id="wrap">
        
        <!-- <h2>하이ㅣㅣ깃헙 로그인 완료 </h2> -->
        <h1>{{userName}}님의 저장소 목록</h1>

        <div @click="writeCommitMSG(userName, el.name)" v-for="el of repoEL" :key="el.id" class="repoEl">
            <h2><strong>{{ el.name }}</strong></h2>
            <p>{{ el.description }}</p>
            <small>last push: {{ el.pushed_at }}</small><br>
            <small class="topic" v-for="topic, index in el.topics" :key="index">#{{ topic }}&nbsp;&nbsp;</small>
            <hr>
        </div>
        
    </div>
</template>
    
<script setup>
import axios from "axios";
import {useTodoStore} from "../stores/todo.js"
import {ref} from "vue";
import router from "../router/index"

// ======================= 변수 모음 =============================
let repoEL = ref([]);
let userName = ref("");

const store = useTodoStore();

//이거는 일단 axios를 모듈화할지 안할지 모르겠어서 그냥 axios 바로 임포트해서 객체 생성함
const http = axios.create({
    headers: {
        "Content-Type": "application/json;charset=utf-8",
        "Accept": "application/json"
    },
});


// 인가코드(code)로 액세스 토큰 요청 후 받아와 저장(access_token)
function fetchAccessToken(){
    const GITHUB_AUTH_TOKEN_SERVER = "https://github.com/login/oauth/access_token";
    const CLIENT_ID = "afaeda1b95f27932a431";
    const CLIENT_SECRETS = "aec761c51a6ac87040a4cf40304b3413809355e9";
    const code = new URL(window.location.href).searchParams.get("code");
    
    const ACCESS_TOKEN_URL = `${GITHUB_AUTH_TOKEN_SERVER}?client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRETS}&code=${code}`;
    
    http.post(ACCESS_TOKEN_URL)
    .then((response) => {
        // *************** DB에도 저장해줘야함 **************
        store.access_token = response.data.access_token
        // console.log(store.access_token)
    })
    .then(()=>{ 
        fetchUser();
        fetchRepoList();  // 액세스 토큰 저장되면 유저 레포 정보 요청
    })
    .catch((err) => console.log(err));
}

// 액세스 토큰(access_token)으로 사용자 정보 요청
function fetchUser(){ 
    const GITHUB_API_SERVER = "https://api.github.com"
    const headers={
        "Authorization" : "Bearer "+ store.access_token,
        "Accept": "application/vnd.github+json"
    }

    http.get(GITHUB_API_SERVER + "/user", {headers})
    .then((response) => {
        userName = response.data.login;
    })
    .catch((err) => console.log(err));
}


// 액세스 토큰(access_token)으로 유저 레포 정보 요청
function fetchRepoList(){ 
    const GITHUB_API_SERVER = "https://api.github.com"
    const headers={
        "Authorization" : "Bearer "+ store.access_token
    }

    http.get(GITHUB_API_SERVER + "/user/repos", {headers})
    .then((response) => {
        console.log(response.data)
        response.data.forEach(element => {
            let el = {
                id: element.id,
                name: element.name,
                description: element.description,
                pushed_at: element.pushed_at, //마지막 푸시?
                topics: element.topics,
            };

            repoEL.value.push(el);
            // repoEL.push(el);
        });
    })
    .catch((err) => console.log(err));
}

function writeCommitMSG(user, repo){
    router.push(`/commit/${user}/${repo}`)
}


// ========================== 여기부터 로직 ==========================
fetchAccessToken();  

</script>

<style scoped>
#wrap{
    text-align: center;
}

.repoEl{
    text-align: left;
}

.topic{
    color: rgb(0, 89, 255);
}
</style>