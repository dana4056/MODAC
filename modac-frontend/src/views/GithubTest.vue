<script setup>
import GithubTestIcon from "@/views/GithubTestIcon.vue";
import { useArticleStore } from "@/stores/article";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";

const articleStore = useArticleStore();
const isAccessToken = ref(true);

// window.open(githubOAuthURL);
console.log("before start");
const initCheckToken = () => {
  const code = new URL(window.location.href).searchParams.get("code");
  console.log(">>>>>>>>>>", code);
};

initCheckToken();

function authorization() {
  const { activeEditor } = storeToRefs(articleStore);
  const editorContent = activeEditor.value.getMarkdown();
  sessionStorage.setItem("editorContent", editorContent);
  const data = sessionStorage.getItem("editorContent");
  console.log(">>>", data);

  const CLIENT_ID = "afaeda1b95f27932a431";
  const CLIENT_SECRETS = "aec761c51a6ac87040a4cf40304b3413809355e9";
  const githubOAuthURL = `https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRETS}&scope=repo`;

  window.location.assign(githubOAuthURL);
  // const popup = window.open(githubOAuthURL, "_blank", "width=600,height=600");

  // var data = {
  //   message: "Hello from the parent window!",
  //   value: 42,
  // };
  // popup.postMessage(data, "*");

  // // const parsedData = JSON.parse(JSON.stringify(data));

  // popup.postMessage(data, "*");

  // router.push({ name: "after" });
}

function checkHaveAccessToken() {
  const access_token = articleStore.access_token;
  if (access_token === "" || access_token === undefined) {
    isAccessToken.value = false;
  }
}

const router = useRouter();

function handleClickSelectRepo() {
  console.log("원격 저장소 선택");
  router.push({ name: "after" });
}

checkHaveAccessToken();
</script>

<template>
  <div id="wrap">
    <div class="github_div_1">
      <GithubTestIcon />
      <h3>github 계정 연결이 필요합니다.</h3>
      <div class="flex gap-3">
        <span @click="authorization" class="github_auth_button">
          계정 연결하기
        </span>
      </div>
    </div>
    <!-- <div v-else>
      <div @click="handleClickSelectRepo">원격 저장소 선택</div>
    </div> -->
  </div>
</template>

<style lang="css">
/* @import "GithubTest.module.css"; */
#wrap {
  text-align: center;
}
.github_div_1 {
  @apply flex flex-col justify-center items-center gap-4 min-w-[50vh] min-h-[20vh];
  align-items: center;
  font-family: "Pretendard";
}
.github_auth_button {
  @apply bg-black py-2 px-5 rounded-xl text-white text-sm;
}
</style>
