<template>
  <div :class="$style.login_all_flex">
    <div :class="$style.login_left_flex">
      <div :class="$style.login_div_flex">
        <div :class="$style.login_description">
          <img :src="emojiFire" class="w-40 h-40 inline">
          <h1 :class="$style.login_title"><img :src="emojiGrinningCatWithSmilingEyes" class="w-11 h-11 inline"> 반가워요. <span :class="$style.text_green"> { 모닥 }</span>이에요!<img :src="emojiPawPrints" class="w-8 h-8 inline"></h1>
          <p :class="$style.login_p">모닥불 앞에 모여 <span :class="$style.text_orange"> 함께 온기를 느끼며 공부</span>하고,
          <br><span :class="$style.text_orange"> 기록하며 성장</span>할 수 있는 <span :class="$style.text_orange">개발자 친화 서비스</span>, <span :class="$style.text_green"> { MODAC }</span>과 함께하세요!</p>
        </div>
        <form id="loginform" :class="$style.login_form">
          <div>
            <input type="text" placeholder="아이디를 입력하세요" :class="$style.login_form_input">
          </div>
          <div>
            <input type="password" placeholder="비밀번호를 입력하세요" :class="$style.login_form_input">
          </div>
          <button type="submit" id="login-button" :class="$style.login_form_login_button" @click="auth.value.isLoggedin = true">로그인</button>
        </form>
      </div>

      <div :class="$style.login_sns_flex">
        <div :class="$style.login_options">
          <RouterLink id="routerlink" to="/signup">회원가입</RouterLink>
          <p>|</p>
          <p>아이디 찾기</p>
          <p>|</p>
          <p>비밀번호찾기</p>
        </div>
      
        <div :class="$style.login_sns_list">
          <!-- <p>SNS 간편 로그인</p>   -->
          <div class="bar"></div>
          <div id="social-login" :class="$style.login_options">
            <button type="button" :class="$style.login_sns_button_github">
              <svg class="w-4 h-4 mr-2 -ml-1" aria-hidden="true" focusable="false" data-prefix="fab" data-icon="github" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 496 512"><path fill="currentColor" d="M165.9 397.4c0 2-2.3 3.6-5.2 3.6-3.3 .3-5.6-1.3-5.6-3.6 0-2 2.3-3.6 5.2-3.6 3-.3 5.6 1.3 5.6 3.6zm-31.1-4.5c-.7 2 1.3 4.3 4.3 4.9 2.6 1 5.6 0 6.2-2s-1.3-4.3-4.3-5.2c-2.6-.7-5.5 .3-6.2 2.3zm44.2-1.7c-2.9 .7-4.9 2.6-4.6 4.9 .3 2 2.9 3.3 5.9 2.6 2.9-.7 4.9-2.6 4.6-4.6-.3-1.9-3-3.2-5.9-2.9zM244.8 8C106.1 8 0 113.3 0 252c0 110.9 69.8 205.8 169.5 239.2 12.8 2.3 17.3-5.6 17.3-12.1 0-6.2-.3-40.4-.3-61.4 0 0-70 15-84.7-29.8 0 0-11.4-29.1-27.8-36.6 0 0-22.9-15.7 1.6-15.4 0 0 24.9 2 38.6 25.8 21.9 38.6 58.6 27.5 72.9 20.9 2.3-16 8.8-27.1 16-33.7-55.9-6.2-112.3-14.3-112.3-110.5 0-27.5 7.6-41.3 23.6-58.9-2.6-6.5-11.1-33.3 2.6-67.9 20.9-6.5 69 27 69 27 20-5.6 41.5-8.5 62.8-8.5s42.8 2.9 62.8 8.5c0 0 48.1-33.6 69-27 13.7 34.7 5.2 61.4 2.6 67.9 16 17.7 25.8 31.5 25.8 58.9 0 96.5-58.9 104.2-114.8 110.5 9.2 7.9 17 22.9 17 46.4 0 33.7-.3 75.4-.3 83.6 0 6.5 4.6 14.4 17.3 12.1C428.2 457.8 496 362.9 496 252 496 113.3 383.5 8 244.8 8zM97.2 352.9c-1.3 1-1 3.3 .7 5.2 1.6 1.6 3.9 2.3 5.2 1 1.3-1 1-3.3-.7-5.2-1.6-1.6-3.9-2.3-5.2-1zm-10.8-8.1c-.7 1.3 .3 2.9 2.3 3.9 1.6 1 3.6 .7 4.3-.7 .7-1.3-.3-2.9-2.3-3.9-2-.6-3.6-.3-4.3 .7zm32.4 35.6c-1.6 1.3-1 4.3 1.3 6.2 2.3 2.3 5.2 2.6 6.5 1 1.3-1.3 .7-4.3-1.3-6.2-2.2-2.3-5.2-2.6-6.5-1zm-11.4-14.7c-1.6 1-1.6 3.6 0 5.9 1.6 2.3 4.3 3.3 5.6 2.3 1.6-1.3 1.6-3.9 0-6.2-1.4-2.3-4-3.3-5.6-2z"></path></svg>
              Github로 로그인하기
              <!-- Sign in with Github -->
            </button>
          </div>
        </div>

      
      </div>
    </div>
    <div :class="$style.login_right_flex">  <img src="https://tailwindui.com/img/component-images/dark-project-app-screenshot.png" alt="Product screenshot" class=" rounded-xl shadow-xl ring-1 ring-gray-400/10 self-center fixed -right-24 top-48 h-3/5 w-1/2" /></div>
    <img :src="emojiChartIncreasing" class="w-40 h-40 inline fixed top-24 right-12">
    <img :src="emojiWritingHand" class="w-32 h-32 inline fixed top-96 " style="right:38%">
    <img :src="emojiCat" class="w-32 h-32 inline fixed bottom-10 right-12">
  </div>
</template>

<script setup>
  import emojiFire from '../../assets/emojis/emoji-fire.png'
  import emojiPawPrints from '../../assets/emojis/emoji-paw-prints.png'
  import emojiGrinningCatWithSmilingEyes from '../../assets/emojis/emoji-grinning-cat-with-smiling-eyes.png'
  import emojiChartIncreasing from '../../assets/emojis/emoji-chart-increasing.png'
  import emojiWritingHand from '../../assets/emojis/emoji-writing-hand.png'
  import emojiCat from '../../assets/emojis/emoji-cat.png'

  // import { useAuthStore } from '../../stores/auth.js'
  // import { ref } from 'vue';
  // const store = useAuthStore();
  // const auths = ref([]);
  // auths.value = store;

  // const login = () => {
  //   auths.value.login();
  // }
  // const logout = () => {
  //   auths.value.logout();
  // }

  // loginstate.value = store.
</script>

<style lang="css" module>
  @import "./UserLogin.module.css";
</style>