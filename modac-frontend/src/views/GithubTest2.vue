<template>
  <OverflowDiv class="overflow_div">
    <div id="wrap" class="github_div">
      <h1 class="font-semibold"><span class="user_github_id">{{ userName }}</span>&nbsp;님의 저장소 목록</h1>
      <p class="text-sm">저장할 곳을 선택해주세요.</p>
      <!-- <h2>저장소 새로 생성</h2> -->
      <button @click="moveCreateNewRepo(userName)" class="github_new_repo_button">새 저장소 생성하기</button>
      <!-- <RouterLink :to="`/createRepo/${userName}`" class="github_new_repo_button">새 저장소 생성하기</RouterLink> -->
      <!-- <h2>저장소 목록</h2> -->
      <div
        @click="selectRepo(userName, el.name)"
        v-for="el of repoEL"
        :key="el.id"
        class="repoEl"
      >
        <h2 class="font-semibold flex flex-wrap justify-between">
          <div>{{ el.name }}
          <span v-if="el.visibility==='public'" class="text-xs font-semibold text-blue-500">{{ el.visibility }}</span> 
          <span v-else class="text-xs font-semibold text-red-500">{{ el.visibility }}</span> 
          </div>
          <div class="text-gray-400 text-xs self-end">last push: {{ el.pushed_at }}</div>
        </h2>
        
        <p class="text-sm">
          
          {{ el.description }}
        </p>
        <small class="github_topic" v-for="(topic, index) in el.topics" :key="index"
          >#{{ topic }}&nbsp;&nbsp;</small
        >
        <!-- <hr /> -->
      </div>
    </div>
  </OverflowDiv>
</template>

<script setup>
import axios from "axios";
import { useTodoStore } from "../stores/todo.js";
import { ref } from "vue";
import router from "../router/index";
import OverflowDiv from "@/components/OverflowDiv.vue";

// ======================= 변수 모음 =============================
let repoEL = ref([]);
let userName = ref("");
const GITHUB_API_SERVER = "https://api.github.com";
const GITHUB_AUTH_TOKEN_SERVER = "https://github.com/login/oauth/access_token";
const CLIENT_ID = "afaeda1b95f27932a431";
const CLIENT_SECRETS = "e65b2850d9fc7e5de605817950d2b6e1454179ff";

const store = useTodoStore();

defineProps(['moveCreateNewRepo', 'selectRepo']);

// const props = defineProps({
//   createNewRepo : function,
// });

const http = axios.create({
  headers: {
    "Content-Type": "application/json;charset=utf-8",
    Accept: "application/json",
  },
});

// 인가코드(code)로 액세스 토큰 요청 후 받아와 저장(access_token)
function fetchAccessToken() {
  // const code = new URL(window.location.href).searchParams.get("code");
    const code = new URL(window.location.href).searchParams.get("code");
  
    const ACCESS_TOKEN_URL = `${GITHUB_AUTH_TOKEN_SERVER}?client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRETS}&code=${code}`;
  
    http
      .post(ACCESS_TOKEN_URL)
      .then((response) => {
        // *************** DB에도 저장해줘야함 **************
        console.log(response);
        if (store.access_token == "") {
          store.access_token = response.data.access_token;
        }
        console.log("store.access_token", store.access_token);
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
    .get(GITHUB_API_SERVER + "/user/repos?per_page=100&sort=updated", { headers })
    .then((response) => {
      console.log(response.data);
      response.data.forEach((element) => {
        let el = {
          id: element.id,
          name: element.name,
          description: element.description,
          pushed_at: element.pushed_at, //마지막 푸시?
          topics: element.topics,
          visibility : element.visibility,
        };

        repoEL.value.push(el);
      });
    })
    .catch((err) => console.log(err));
}

// function writeCommitMSG(user, repo, createNewRepo) {
  
//   createNewRepo(user, repo);

//   // router.push(`/commit/${user}/${repo}`);
// }

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
  @apply flex flex-col px-5 py-2 bg-gray-100 rounded-xl mx-5 min-w-[50%];
  text-align: left;
}

.topic {
  color: rgb(0, 89, 255);
}

.overflow_div {
  @apply h-[80vh]
}

.github_div {
  @apply flex flex-col gap-3 min-h-[40vh] pt-3 pb-6;
	font-family: 'Pretendard';
}

.user_github_id {
  @apply font-semibold text-yellow-700 bg-yellow-200 border border-yellow-600 px-3 py-1 rounded-lg
}

.github_topic {
  @apply font-semibold text-blue-700 bg-blue-200 border border-blue-600 px-3 py-1 rounded-full
}

.github_new_repo_button {
  @apply bg-black py-2 px-5 rounded-xl text-white text-sm w-fit self-end mr-5
}
</style>
