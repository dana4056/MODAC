<template>    
  <OverflowDiv class="overflow_div">
    <div id="wrap" class="github_div">
      <h1 class="font-semibold"><span class="user_github_id">{{ props.user_info }}</span>&nbsp;님의 저장소 {{ props.repo_info }}</h1>
      <p class="text-sm">파일 이름을 클릭 시 github로 이동됩니다.</p>
      <div
        v-for="file, index in files" :key="index"
        class="repoEl"
      >
        <h2 class="flex flex-wrap justify-between">
          <a :href="file.html_url" target='_blank'>{{ file.name }}</a>
        </h2>
      </div>

      
    </div>
  </OverflowDiv>
</template>
    
<script setup>
import axios from "axios";
import OverflowDiv from "@/components/OverflowDiv.vue";
import { useTodoStore } from "../stores/todo.js"
import { useRoute } from 'vue-router'
import {ref} from "vue";

let files = ref([]);

// defineProps(['user_info', 'repo_info']);
const props = defineProps({
  "user_info" : String,
  "repo_info" : String,
});

const store = useTodoStore();
const route = useRoute();

const http = axios.create({
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/vnd.github+json"
    },
});


function fetchRepo(user_info, repo_info){
    const GITHUB_API_SERVER = "https://api.github.com"
    const owner = user_info;
    const repo = repo_info;

    const headers={
        "Authorization" : "Bearer "+ store.access_token
    }

    http.get(GITHUB_API_SERVER + `/repos/${owner}/${repo}/contents`, {headers})
    .then((response) => {
      console.log(response);
        response.data.forEach(element => {
            let el = {
                name: element.name,
                html_url: element.html_url
            };

            files.value.push(el);
        });

        console.log(files.value)
    })
    .catch((err) => console.log(err));
}

fetchRepo(props.user_info, props.repo_info);

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

.github_div {
  @apply flex flex-col gap-3 min-w-[60vw] min-h-[40vh] pt-3 pb-6;
	font-family: 'Pretendard';
}

.user_github_id {
  @apply font-semibold text-yellow-700 bg-yellow-200 border border-yellow-600 px-3 pb-1 rounded-lg
}

.repoEl {
  @apply flex flex-col px-5 py-2 bg-gray-100 rounded-xl mx-5 min-w-[50%];
  text-align: left;
}

.overflow_div {
  @apply h-[80vh]
}
</style>