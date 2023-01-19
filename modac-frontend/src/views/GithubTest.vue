<template>
  <div id="wrap">
    <h3>github 계정 연결이 필요합니다.</h3>
    <button @click="authorization">계정 연결하기</button>
  </div>
</template>

<script>

export default { 
    methods:{
        authorization(){
            const CLIENT_ID = "afaeda1b95f27932a431";
            const CLIENT_SECRETS = "aec761c51a6ac87040a4cf40304b3413809355e9";

            window.location.assign(`https://github.com/login/oauth/authorize?client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRETS}`);

        }
    }
}
</script>

<style scoped>
#wrap{
    text-align: center;
}
</style>