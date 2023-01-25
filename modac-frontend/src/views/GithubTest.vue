<template>
  <div id="wrap">
    <div v-if="!has_token">
      <h3>github 계정 연결이 필요합니다.</h3>
      <button @click="authorization">계정 연결하기</button>
    </div>
    <div v-else>
      <RouterLink to="/after">원격 저장소 선택</RouterLink>
    </div>
  </div>
</template>

<script setup>

import {useTodoStore} from "../stores/todo.js"

const store = useTodoStore();
let has_token = true;

function authorization(){
  const CLIENT_ID = "afaeda1b95f27932a431";
  const CLIENT_SECRETS = "aec761c51a6ac87040a4cf40304b3413809355e9";

  window.location.assign(`https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRETS}&scope=repo`);

}

function fetchAccessToken(){
  const access_token = store.access_token;

  if(!access_token){
    has_token = false;
  }
}

fetchAccessToken();

</script>

<style scoped>
#wrap{
    text-align: center;
}
</style>