<template>
  <div>
    <h1>API TEST 화면임둥</h1>
    <button @click="click">API TEST</button>
  </div>
</template>

<script setup>

import { useUserStore } from '../stores/user';

const store = useUserStore();

const user = {
    nickname: "nickname1",
    email: "test1@gmail.com",
};


function click(){
    // store.api.postUser();    
    // store.api.fetchUser(3);
    store.api.updateUser({userSeq:1, user});
}



</script>

<style>

</style>