<template lang="">
  <div :class="$style.tabs_flex">
    <form @submit.prevent="submitUpdateInfo(updateInfo)" :class="$style.update_info_form">
      <div :class="$style.update_info_div">
        <label for="user_id" :class="$style.update_info_label">아이디</label>
        <input :class="$style.update_info_input_disabled" 
                type="text" 
                v-model="user_id" 
                id="user_id" 
                placeholder="아이디 입력" 
                required
                disabled>
      </div>

      <div :class="$style.update_info_div">
        <label for="user_nickname" :class="$style.update_info_label">닉네임</label>
        <input :class="$style.update_info_input"
                type="text"
                v-model="user_nickname"
                id="user_nickname"
                placeholder="닉네임 입력"
                required>
        <button :class="$style.update_info_nickname_button_div"
                @click="checkPassword">
          중복 확인하기
        </button>
      </div>

      <div :class="$style.update_info_div">
        <label for="user_email" :class="$style.update_info_label">이메일</label>
        <input type="text" 
                v-model="user_email1" 
                id="user_email"
                :class="$style.update_info_input_email"
                placeholder="이메일" 
                required>

        &nbsp;&nbsp;&nbsp;@&nbsp;&nbsp;&nbsp;

        <select id="user_email2" 
                v-model="user_email2"
                :class="$style.update_info_input_email">
          <option value="naver.com" selected="selected">naver.com</option>
          <option value="gmail.com">gmail.com</option>
          <option value="ssafy.com">ssafy.com</option>
        </select>
        <button :class="$style.update_info_nickname_button_div"
                @click="checkEmail">
          중복 확인하기
        </button>
      </div>

      <!-- 추가하기 버튼 -->
      <div :class="$style.update_info_save_div">
        <!-- <button type="submit" class="w-full text-sm text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg px-5 py-2.5 m-1 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
          저장하기
        </button> -->
        <button :class="$style.update_info_save"
                @click="updateUserInfo">
          저장하기
        </button>
      </div>
    </form>
  </div>

</template>

<script setup>

import { ref } from "vue";

let user_id = "kimssafy";
let user_nickname = "김싸피";
let user_email1 = "kim";
let user_email2 = "ssafy.com";


let updateInfo = () => ref({
  "user_nickname" : user_nickname,
  "user_email" : updateInfo.user_email1 + "@" + updateInfo.user_email2
})


const checkPassword = () => {
  // 닉네임 중복 검사 요청 보내기

  alert("해당 닉네임은 사용 가능합니다.");
}

const checkEmail = () => {
  // 이메일 중복 검사 요청 보내기
  // 나중에 이메일 인증 요청 하는 걸로 변경할 것

  alert("해당 이메일은 사용 가능합니다.");
}

const updateUserInfo = () => {
  // 닉네임 중복 검사 ok && 이메일 중복 검사 완료 시 

  if (confirm("정말 수정하시겠습니까?") == true) {
    // 회원정보 수정 함수 실행!!
    alert("수정 완료");
  }
}

</script>

<style lang="css" module>
  @import "./TabUserInfoUpdate.module.css";

  
</style>


