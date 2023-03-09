<script setup>
import axios from "axios";
import { useArticleStore } from "@/stores/article";
import { ref } from "vue";
import { useRoute } from "vue-router";

let files = ref([]);

const articleStore = useArticleStore();
const route = useRoute();

const http = axios.create({
  headers: {
    "Content-Type": "application/json",
    Accept: "application/vnd.github+json",
  },
});

function fetchRepo() {
  const GITHUB_API_SERVER = "https://api.github.com";
  const owner = route.params.user;
  const repo = route.params.repo;

  const headers = {
    Authorization: "Bearer " + articleStore.access_token,
  };

  http
    .get(GITHUB_API_SERVER + `/repos/${owner}/${repo}/contents`, { headers })
    .then((response) => {
      response.data.forEach((element) => {
        let el = {
          name: element.name,
          html_url: element.html_url,
        };

        files.value.push(el);
      });

      // console.log(files.value);
    })
    .catch((err) => console.log(err));
}

fetchRepo();
</script>

<template>
  <OverflowDiv class="flex_div4">
    <div id="wrap" class="github_div4">
      <h1 class="font-semibold">
        저장소 내용
        <!-- <span class="user_github_id">{{ props.user_info }}</span
        >&nbsp;님의 저장소 {{ props.repo_info }} -->
      </h1>
      <p class="text-sm">파일 이름을 클릭 시 github로 이동됩니다.</p>
      <div v-for="(file, index) in files" :key="index" class="repoEl4">
        <h2 class="flex flex-wrap justify-between">
          <a :href="file.html_url" target="_blank">{{ file.name }}</a>
        </h2>
      </div>
    </div>
  </OverflowDiv>
  <!-- <div id="wrap">
    <div v-for="(file, index) in files" :key="index">
      <a :href="file.html_url">{{ file.name }}</a>
    </div>
  </div> -->
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

.flex_div4 {
  @apply flex flex-col content-center items-center;
}
.github_div4 {
  @apply flex flex-col gap-3 min-w-[30vw] max-w-[50vw] min-h-[40vh] pt-10 pb-6;
  font-family: "Pretendard";
}

.user_github_id {
  @apply font-semibold text-yellow-700 bg-yellow-200 border border-yellow-600 px-3 pb-1 rounded-lg;
}

.repoEl4 {
  @apply flex flex-col px-5 py-2 bg-gray-100 rounded-xl mx-5 min-w-[50%];
  text-align: left;
}

/* .overflow_div {
  @apply h-[80vh];
} */
</style>
