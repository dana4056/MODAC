<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const userStore = useUserStore()
const { loginUser } = storeToRefs(userStore)


const user_id = ref(loginUser.value.id);
const user_nickname = ref(loginUser.value.nickname);
const user_email = ref(loginUser.value.email);

const user_email1 = user_email.value.split("@")[0]
const user_email2 = user_email.value.split("@")[1]

const email_input1 = ref("")
const email_input2 = ref(user_email2)


const checkPassword = () => {
  // 닉네임 중복 검사 요청 보내기

  Message.info("해당 닉네임은 사용 가능합니다 :-)",{closable:true});
}

const checkEmail = () => {
  // 이메일 중복 검사 요청 보내기
  // 나중에 이메일 인증 요청 하는 걸로 변경할 것

  Message.info("해당 이메일은 사용 가능합니다 :-)",{closable:true});
}

const updateUserInfo = () => {
  // 닉네임 중복 검사 ok && 이메일 중복 검사 완료 시 
  if (confirm("정말 수정하시겠습니까?") == true) {
    // 회원정보 수정 함수 실행!!
    const payload = {
      userSeq: loginUser.value.seq,
      update:{
        nickname: user_nickname.value,
        email: email_input1.value + "@" + email_input2.value
      }
    }
    console.log("콘솔",payload)
    userStore.api.updateUser(payload)
  }
}

</script>

<template lang="">
  <div :class="$style.tabs_flex">
    <form @submit.prevent="updateUserInfo" :class="$style.update_info_form">
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
                v-model="email_input1" 
                id="user_email"
                :class="$style.update_info_input_email"
                :placeholder="user_email1"
                required>

        &nbsp;&nbsp;&nbsp;@&nbsp;&nbsp;&nbsp;

        <select id="user_email2" 
                v-model="email_input2"
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
        <button :class="$style.update_info_save"
                type="submit">
          저장하기
        </button>
      </div>
    </form>
  </div>

</template>

<style lang="css" module>
  @import "./TabUserInfoUpdate.module.css";

  
</style>


