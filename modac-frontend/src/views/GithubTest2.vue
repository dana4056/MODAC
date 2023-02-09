<template>
  <div id="wrap">
    <h1>{{ userName }}님의 저장소 목록</h1>

    <h2>저장소 새로 생성</h2>
    <RouterLink :to="`/createRepo/${userName}`">create reposiroty</RouterLink>
    <h2>저장소 선택</h2>
    <div
      @click="writeCommitMSG(userName, el.name)"
      v-for="el of repoEL"
      :key="el.id"
      class="repoEl"
    >
      <h2>
        <strong>{{ el.name }}</strong>
      </h2>
      <p>{{ el.description }}</p>
      <small>last push: {{ el.pushed_at }}</small
      ><br />
      <small class="topic" v-for="(topic, index) in el.topics" :key="index"
        >#{{ topic }}&nbsp;&nbsp;</small
      >
      <hr />
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { useTodoStore } from "../stores/todo.js";
import { ref } from "vue";
import router from "../router/index";

// ======================= 변수 모음 =============================
let repoEL = ref([]);
let userName = ref("");
const GITHUB_API_SERVER = "https://api.github.com";
const GITHUB_AUTH_TOKEN_SERVER = "https://github.com/login/oauth/access_token";
const CLIENT_ID = "afaeda1b95f27932a431";
const CLIENT_SECRETS = "e65b2850d9fc7e5de605817950d2b6e1454179ff";

const store = useTodoStore();

const http = axios.create({
  headers: {
    "Content-Type": "application/json;charset=utf-8",
    Accept: "application/json",
  },
});

// 인가코드(code)로 액세스 토큰 요청 후 받아와 저장(access_token)
function fetchAccessToken() {
  const code = new URL(window.location.href).searchParams.get("code");

  const ACCESS_TOKEN_URL = `${GITHUB_AUTH_TOKEN_SERVER}?client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRETS}&code=${code}`;

  http
    .post(ACCESS_TOKEN_URL)
    .then((response) => {
      // *************** DB에도 저장해줘야함 **************
      console.log(response);
      store.access_token = response.data.access_token;
      console.log(store.access_token);
    })
    .then(() => {
      fetchUser();
      fetchRepoList(); // 액세스 토큰 저장되면 유저 레포 정보 요청
    })
    .catch((err) => console.log(err));
}

// 액세스 토큰(access_token)으로 사용자 정보 요청
function fetchUser() {
  const headers = {
    Authorization: "Bearer " + store.access_token,
    Accept: "application/vnd.github+json",
  };

  http
    .get(GITHUB_API_SERVER + "/user", { headers })
    .then((response) => {
      userName = response.data.login;
    })
    .catch((err) => console.log(err));
}

// 액세스 토큰(access_token)으로 유저 레포 정보 요청
function fetchRepoList() {
  const headers = {
    Authorization: "Bearer " + store.access_token,
    Accept: "application/vnd.github+json",
  };

  http
    .get(GITHUB_API_SERVER + "/user/repos", { headers })
    .then((response) => {
      console.log(response.data);
      response.data.forEach((element) => {
        let el = {
          id: element.id,
          name: element.name,
          description: element.description,
          pushed_at: element.pushed_at, //마지막 푸시?
          topics: element.topics,
        };

        repoEL.value.push(el);
      });
    })
    .catch((err) => console.log(err));
}

function writeCommitMSG(user, repo) {
  router.push(`/commit/${user}/${repo}`);
}

// ========================== 여기부터 로직 ==========================
// if(!store.access_token){

// }
fetchAccessToken();
</script>

<style scoped>
#wrap {
  text-align: center;
}

.repoEl {
  text-align: left;
}

.topic {
  color: rgb(0, 89, 255);
}
</style>
