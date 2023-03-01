<script setup>
import axios from "axios";
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useArticleStore } from "@/stores/article";
import { useRoute } from "vue-router";
import router from "../router/index";
import articleAPI from "@/api/article";
import { useUserStore } from "@/stores/user";

const title = ref("");
const commitMSG = ref("");

const articleStore = useArticleStore();
const { activeEditor } = storeToRefs(articleStore);
const route = useRoute();

const http = axios.create({
  headers: {
    "Content-Type": "application/json",
    Accept: "application/vnd.github+json",
  },
});

async function commitToRepo() {
  const userStore = useUserStore();
  const { loginUser } = storeToRefs(userStore);
  console.log(loginUser.value);
  const payload = {
    usersSeq: loginUser.value.seq,
    offset: 1,
    limit: 1,
  };
  const response = await articleAPI.findArticleByUsersSeq(payload);
  const lastestArticleSeq = response.data.articleInfoList[0].seq;

  const res = await articleAPI.findArticle(lastestArticleSeq);
  const content = res.data.filepath; // String

  alert("msg:" + commitMSG.value);
  const GITHUB_API_SERVER = "https://api.github.com";
  const user = route.params.user;
  const repo = route.params.repo;

  const headers = {
    Authorization: "Bearer " + articleStore.access_token,
  };

  // const data = sessionStorage.getItem("editorContent");
  // console.log(data);
  // const currentActiveEditor = activeEditor.value;
  // const content = currentActiveEditor.getMarkdown();
  const encodedContent = btoa(unescape(encodeURIComponent(content)));

  const body = {
    message: commitMSG.value,
    content: encodedContent,
  };

  http
    .put(
      GITHUB_API_SERVER + `/repos/${user}/${repo}/contents/${title.value}.md`,
      body,
      {
        headers,
      }
    )
    .then((response) => {
      alert("원격저장소에 정상적으로 Commit 되었습니다 :->");
      router.push(`/repo/${user}/${repo}`);
    })
    .catch((err) => console.log(err));
}
</script>

<template>
  <div class="flex justify-center items-center">
    <div id="wrap" class="github_div">
      <p class="font-semibold">업로드할 파일 이름을 작성해주세요</p>
      <div class="flex w-full items-end gap-1">
        <input v-model="title" type="text" class="github_input" />
        <span>.md</span>
      </div>

      <p class="font-semibold">Commit message를 작성해주세요</p>
      <textarea v-model="commitMSG" class="github_input"></textarea>
      <button @click="commitToRepo" class="github_new_repo_button">
        원격 저장소에 Commit 하기
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
  border: 1px gray solid;
}
button {
  background-color: rgb(82, 156, 134);
}
.github_div {
  @apply flex flex-col items-start gap-4 min-w-[50vh] min-h-[30vh] justify-center w-fit p-6 pt-2;
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
  @apply block p-2.5 text-sm rounded-lg w-full border resize-none
	bg-gray-50 border-gray-300 text-gray-900 
	focus:ring-blue-500 focus:border-blue-500 
	dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white;
}
</style>
