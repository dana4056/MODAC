<template>
  <div :class="$style.login_all_flex">
    <div :class="$style.login_left_flex">
      <div :class="$style.login_div_flex">
        <div :class="$style.login_description">
          <img :src="emojiFire" class="w-40 h-40 inline">
          <h1 :class="$style.login_title"><span :class="$style.text_green">비밀번호</span>를 변경하세요!</h1>
          <p :class="$style.login_p">최소 8자리에서 최대 16자리까지 <span :class="$style.text_orange"> 숫자, 영문 소문자, 대문자, 특수문자</span> 각 1개 이상 포함하여 작성하세요.</p>
        </div>
        <form id="loginform" :class="$style.login_form">
          <div>
            <input v-model="loginPW1" type="password" placeholder="새 비밀번호를 입력하세요" :class="$style.login_form_input">
          </div>
          <div>
            <input v-model="loginPW2" type="password" placeholder="새 비밀번호를 입력하세요" :class="$style.login_form_input">
          </div>
          <button type="submit" @click.prevent="changePassword" id="login-button" :class="$style.login_form_login_button">비밀번호 변경</button>
        </form>
      </div>

      <div :class="$style.login_sns_flex">
        <div :class="$style.login_options">
          <RouterLink id="routerlink" to="/">로그인</RouterLink>
          <p>|</p>
          <RouterLink id="routerlink" to="/signup">회원가입</RouterLink>
          <p>|</p>
          <RouterLink id="routerlink" to="/findid">아이디 찾기</RouterLink>
        </div>


      
      </div>
    </div>
    <div :class="$style.login_right_flex">  <img src="@/assets/img/img.png" alt="Product screenshot" class=" rounded-xl shadow-xl ring-1 ring-gray-400/10 self-center fixed -right-16 top-52 h-3/5 w-1/2" /></div>

  </div>
</template>

<script setup>
  import emojiFire from '../../assets/emojis/emoji-fire.png';
  import { useUserStore } from '@/stores/user';
  import Message from "vue-m-message";
  import { ref } from "vue";
  import { useRouter } from "vue-router";
  import { storeToRefs } from "pinia";

  const router = useRouter();
  const userStore = useUserStore();
  const { authUser } = storeToRefs(userStore);

  const loginPW1 = ref("");
  const loginPW2 = ref("");


  function changePassword(){

    if(loginPW1.value === loginPW2.value){
      console.log(authUser.value)
      const payload = {
        userSeq: authUser.value.seq,
        password: loginPW1.value,
      };
      userStore.api.updatePW(payload);  // 비밀번호 변경
    }else{
      Message.error("비밀번호를 확인해주세요 :-(",{closable:true});
    }
  }


</script>

<style lang="css" module>
  @import "./UserChangePassword.module.css";
</style>