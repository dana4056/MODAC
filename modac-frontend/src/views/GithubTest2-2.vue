<script setup>
import axios from "axios";
import { useArticleStore } from "@/stores/article";
import { useRoute } from "vue-router";
import router from "../router/index";
import { ref } from "vue";

const GITHUB_API_SERVER = "https://api.github.com";

const repoName = ref("");
const repoDes = ref("");
const isPrivate = ref(false);

const articleStore = useArticleStore();
const route = useRoute();

const http = axios.create({
  headers: {
    "Content-Type": "application/json",
    Accept: "application/vnd.github+json",
  },
});

function createRepo() {
  const body = {
    name: repoName.value,
    description: repoDes.value,
    private: isPrivate.value,
  };

  const headers = {
    Authorization: "Bearer " + articleStore.access_token,
  };

  http
    .post(GITHUB_API_SERVER + "/user/repos", body, { headers })
    .then((response) => {
      console.log(response);
      alert("저장소가 생성되었습니다 :->");
      writeCommitMSG(body.name);
    })
    .catch((err) => console.log(err));
}

function writeCommitMSG(repo) {
  const user = route.params.user;

  router.push(`/commit/${user}/${repo}`);
}
</script>

<template>
  <div class="flex justify-center items-center">
    <div id="wrap" class="github_div">
      <h1 class="font-semibold text-xl">원격 저장소 생성하기</h1>
      <div class="github_repo_input_div">
        <p class="github_repo_input_label font-semibold">
          저장소 이름 <span class="text-red-600">*</span>
        </p>
        <input v-model="repoName" type="text" class="github_input" />
      </div>

      <div class="github_repo_input_div">
        <p class="github_repo_input_label">설명</p>
        <textarea
          v-model="repoDes"
          type="text"
          class="github_input h-32 resize-none"
        ></textarea>
      </div>

      <div class="github_repo_input_div_row">
        <span class="github_repo_check_label">비공개 설정 </span>
        <input type="checkbox" class="w-4 h-4" v-model="isPrivate" />
      </div>

      <button @click="createRepo" class="github_new_repo_button">
        생성하기
      </button>
    </div>
  </div>
</template>

<style lang="css">
#wrap {
  text-align: center;
}
input,
textarea {
  margin: 0 auto;
  /* display: block; */
  border: 1px gray solid;
}
button {
  background-color: rgb(82, 156, 134);
}
.github_div {
  @apply flex flex-col items-start gap-4 min-w-[50vh] min-h-[30vh] justify-center w-fit p-6 pt-0;
  font-family: "Pretendard";
}
.github_repo_input_div {
  @apply flex gap-3 flex-col w-full;
}
.github_repo_input_div_row {
  @apply flex gap-5 items-center;
}
.github_repo_input_label {
  @apply w-28 text-left;
}
.github_repo_check_label {
  @apply w-fit text-left;
}
.github_new_repo_button {
  @apply bg-black py-2 px-5 rounded-xl text-white text-sm self-end w-full;
}
.github_input {
  @apply block p-2.5 text-sm rounded-lg w-full border
	bg-gray-50 border-gray-300 text-gray-900 
	focus:ring-blue-500 focus:border-blue-500 
	dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white;
}
</style>
