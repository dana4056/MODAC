<template>
  <div :class="$style.login_all_flex">
    <div :class="$style.login_left_flex">
      <div :class="$style.login_div_flex">
        <div :class="$style.login_description">
          <img :src="emojiFire" class="w-32 h-32 inline">
          <h1 :class="$style.login_title"><span :class="$style.text_green"> { 모닥 }</span>이 처음이신가요?</h1>
          <p :class="$style.login_p">지금도 늦지 않았어요! <span :class="$style.text_green"> { MODAC }</span>에서 공부와 기록을 도와드릴게요.</p>
        </div>
        <form id="loginform" :class="$style.login_form">
          <div>
            <input type="text" placeholder="아이디를 입력하세요" :class="$style.login_form_input">
          </div>

          <div>
            <input type="text" placeholder="닉네임을 입력하세요" :class="$style.login_form_input">
          </div>

          <!-- <div>
            <input type="password" placeholder="비밀번호를 입력하세요" :class="$style.login_form_input">
          </div> -->

          <div>
            <input :class="$style[update_password_input_css]"
                    pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$"
                    title="비밀번호는 최소 8자리에서 최대 16자리까지 숫자, 영문, 특수문자 각 1개 이상 포함"
                    type="password" 
                    v-model="user_password" 
                    id="user_new_password_input" 
                    placeholder="비밀번호를 입력하세요" 
                    required
                    autocomplete='off'
                    autofocus>
            <p :class="$style[checkMessage_css]">{{ checkMessage }}</p>
            <p :class="$style.update_password_p">
              비밀번호는 최소 8자리에서 최대 16자리까지 숫자, 영문 소문자, 대문자, 특수문자 각 1개 이상 포함하여 작성하세요.
            </p>
          </div>
<!-- 
          <div>
            <input type="text" placeholder="이메일을 입력하세요" :class="$style.login_form_input">
          </div> -->

          <div :class="$style.update_info_div">
            <input type="text" 
                    v-model="user_email1" 
                    id="user_email"
                    :class="$style.update_info_input_email"
                    placeholder="이메일" 
                    required>

            &nbsp;@&nbsp;

            <select id="user_email2" 
                    v-model="user_email2"
                    :class="$style.update_info_input_email">
              <option value="naver.com" selected="selected">naver.com</option>
              <option value="gmail.com">gmail.com</option>
              <option value="ssafy.com">ssafy.com</option>
              </select>
          </div>

          <button type="submit" id="login-button" :class="$style.login_form_login_button" @click="auth.value.isLoggedin = true">회원가입</button>
        </form>
      </div>

      <div :class="$style.login_sns_flex">
        <div :class="$style.login_options">
          <RouterLink id="routerlink" to="/login">로그인</RouterLink>
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
  // import emojiPawPrints from '../../assets/emojis/emoji-paw-prints.png'
  // import emojiGrinningCatWithSmilingEyes from '../../assets/emojis/emoji-grinning-cat-with-smiling-eyes.png'
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


  
import { ref, computed } from "vue";

// let reg_pass = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
let reg_pass1 = /^(?=.*[a-z]).*$/; // 영문 소문자
let reg_pass2 = /^(?=.*[A-Z]).*$/; // 영문 대문자
let reg_pass3 = /^(?=.*[0-9]).*$/; // 슷자
let reg_pass4 = /^(?=.*[@$!%*#?&]).*$/; // 특수문자

let user_password = ref("");

// const Password = () => {
//   if (confirm("비밀번호를 변경하시겠습니까?") == true) {
//     // 비밀번호 변경 함수 실행!!
//     alert("비밀번호 변경 완료");
//   }
// }

let length = computed(() => {
  return (8 <= user_password.value.length 
          && user_password.value.length <= 16) ? true : false
})

let lowercase = computed(() => {
  return reg_pass1.test(user_password.value)
})

let uppercase = computed(() => {
  return reg_pass2.test(user_password.value)
})

let number = computed(() => {
  return reg_pass3.test(user_password.value)
})

let specialCharacters = computed(() => {
  return reg_pass4.test(user_password.value)
})

let checkCode = computed(() => {
  if (user_password.value == "") return 6
  if (!length.value) return 1
  if (!lowercase.value) return 2
  if (!uppercase.value) return 3
  if (!number.value) return 4
  if (!specialCharacters.value) return 5
  return 0
})

let checkMessage = computed(() => {
  if (checkCode.value == 1) return "길이는 최소 8자 ~ 최대 16자여야 합니다."
  if (checkCode.value == 2) return "비밀번호에 소문자가 포함되어야 합니다."
  if (checkCode.value == 3) return "비밀번호에 대문자가 포함되어야 합니다."
  if (checkCode.value == 4) return "비밀번호에 숫자가 포함되어야 합니다."
  if (checkCode.value == 5) return "비밀번호에 특수문자(@$!%*#?&)가 포함되어야 합니다."
  if (checkCode.value == 0) return "비밀번호 조건을 만족하였습니다."
  return ""
})

const update_password_input_css = computed(() => {
  if (checkCode.value === 6) return "update_password_input";
  if (checkCode.value === 0) return "update_password_input_success";
  return "update_password_input_fail";
})

const checkMessage_css = computed(() => {
  if (checkCode.value === 6) return "update_password_p";
  if (checkCode.value === 0) return "update_password_p_success";
  return "update_password_p_fail";
})


let user_email1 = "";
let user_email2 = "naver.com";


// let updateInfo = () => ref({
//   "user_nickname" : user_nickname,
//   "user_email" : updateInfo.user_email1 + "@" + updateInfo.user_email2
// })

</script>

<style lang="css" module>
  @import "./UserSignUp.module.css";
</style>