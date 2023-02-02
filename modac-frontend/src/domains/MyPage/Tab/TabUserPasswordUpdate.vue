<template lang="">
<div :class="$style.tabs_flex">
    <form @submit.prevent="submitUpdatePassword(updatePassword)" :class="$style.update_password_form">
      <!-- 입력 전 -->
      <!-- <div v-if="user_new_password === ''" :class="$style.update_password_div"> -->
      <div :class="$style.update_password_div">
        <label for="user_new_password_input" 
              :class="$style.update_password_label">
          새 비밀번호
        </label>
        <div>
          <input :class="$style[update_password_input_css]"
                  pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$"
                  title="최소 8자리에서 최대 16자리까지 숫자, 영문, 특수문자 각 1개 이상 포함"
                  type="text" 
                  v-model="user_new_password" 
                  id="user_new_password_input" 
                  placeholder="새 비밀번호 입력" 
                  required
                  autocomplete='off'
                  autofocus>
          <p :class="$style[checkMessage_css]">{{ checkMessage }}</p>
          <!-- <p :class="$style.update_password_label_fail">
            length : {{ length }} / lowercase : {{ lowercase }} / uppercase : {{ uppercase }} / number : {{ number }} / specialCharacters : {{ specialCharacters }}
          </p> -->
          <p :class="$style.update_password_p">
            최소 8자리에서 최대 16자리까지 숫자, 영문 소문자, 대문자, 특수문자 각 1개 이상 포함하여 작성하세요.
          </p>
        </div>
      </div>

      <!-- 비밀번호 일치하는지 확인하는 칸 -->
      <div :class="$style.update_password_div">
        <label for="user_new_password_check_input" :class="$style.update_password_label">새 비밀번호 확인</label>
        <div>
          <input :class="$style[update_password_check_input_css]" 
                  type="text" 
                  v-model="user_new_password_check" 
                  id="user_new_password_check_input" 
                  placeholder="새 비밀번호 확인 입력" 
                  autocomplete='off'
                  required>
          <p :class="$style[checkMessage2_css]">
            {{ checkMessage2 }}
          </p>
        </div>
      </div>

      <!-- 추가하기 버튼 -->
      <div :class="$style.update_password_save_div">
        <button v-if="checkCode === 0 && user_new_password === user_new_password_check" 
                type="submit"
                class="w-full text-sm text-white bg-blue-600 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg px-5 py-2.5 m-1 text-center"
                >
          비밀번호 변경하기
        </button>
        <button v-else :class="$style.update_password_save_disabled" disabled>
          비밀번호 변경하기
        </button>
      </div>
    </form>
  </div>

</template>

<script setup>

import { ref, computed } from "vue";

// let reg_pass = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
let reg_pass1 = /^(?=.*[a-z]).*$/; // 영문 소문자
let reg_pass2 = /^(?=.*[A-Z]).*$/; // 영문 대문자
let reg_pass3 = /^(?=.*[0-9]).*$/; // 슷자
let reg_pass4 = /^(?=.*[@$!%*#?&]).*$/; // 특수문자

let user_new_password = ref("");
let user_new_password_check = ref("");
// let checkCode = ref("0");


const updatePassword = () => {
  if (confirm("비밀번호를 변경하시겠습니까?") == true) {
    // 비밀번호 변경 함수 실행!!
    alert("비밀번호 변경 완료");
  }
}

let length = computed(() => {
  return (8 <= user_new_password.value.length 
          && user_new_password.value.length <= 16) ? true : false
})

let lowercase = computed(() => {
  return (reg_pass1.test(user_new_password.value)) ? true : false
})

let uppercase = computed(() => {
  return (reg_pass2.test(user_new_password.value)) ? true : false
})

let number = computed(() => {
  return (reg_pass3.test(user_new_password.value)) ? true : false
})

let specialCharacters = computed(() => {
  return (reg_pass4.test(user_new_password.value)) ? true : false
})


let checkCode = computed(() => {
  if (user_new_password.value == "") return 6
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

const update_password_check_input_css = computed(() => {
  if (user_new_password_check.value === "") return "update_password_input";
  if (user_new_password_check.value === user_new_password.value) return "update_password_input_success";
  return "update_password_input_fail";
})

let checkMessage2 = computed(() => {
  if (user_new_password_check.value === "") return ""
  if (user_new_password_check.value !== user_new_password.value) return "비밀번호 확인은 새 비밀번호와 동일하게 입력해주세요."
  return "확인이 완료되었습니다."
})

const checkMessage2_css = computed(() => {
  if (user_new_password_check.value === "") return "update_password_p";
  if (user_new_password_check.value === user_new_password.value) return "update_password_p_success";
  return "update_password_p_fail";
})


</script>

<style lang="css" module>
  @import "./TabUserPasswordUpdate.module.css";
</style>