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
          <input :class="$style.update_password_input" 
                  pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$"
                  title="최소 8자리에서 최대 16자리까지 숫자, 영문, 특수문자 각 1개 이상 포함"
                  type="text" 
                  v-model="user_new_password" 
                  id="user_new_password_input" 
                  placeholder="새 비밀번호 입력" 
                  required
                  autofocus>
          <p :class="$style.update_password_p">
            최소 8자리에서 최대 16자리까지 숫자, 영문, 특수문자 각 1개 이상 포함하여 작성하세요.
          </p>
        </div>
        
        
<!-- 
        <div v-if="user_new_password===''">입력 전</div>
        <div v-if="user_new_password!=='' && password_check">비밀번호 일치 O</div>
        <div v-if="user_new_password!=='' && !password_check">비밀번호 일치 X</div> -->

        <!-- <p id="letter" v-if="">A <b>lowercase</b> letter</p>
        <p id="capital" v-if="">A <b>capital (uppercase)</b> letter</p>
        <p id="number" v-if="">A <b>number</b></p>
        <p id="length" v-if="">Minimum <b>8 characters</b></p> -->
      </div>
      <!-- 입력 후 비밀번호 조건 맞을 때 -->
      <!-- <div v-if="user_new_password !== '' && password_check" :class="$style.update_password_div">
        <label for="user_new_password_label" :class="$style.update_password_label_success">Your name</label>
        <input autofocus 
                type="text" 
                id="user_new_password" 
                v-model="user_new_password" 
                :class="$style.update_password_input_success" 
                placeholder="Success input">
        <p :class="$style.update_password_p_success"><span class="font-medium">Well done!</span> Some success message.</p>
      </div> -->

      <!-- 입력 후 비밀번호 조건 안 맞을 때 -->
      <!-- <div v-if="user_new_password !== '' && !password_check" :class="$style.update_password_div">
        <label for="user_new_password" :class="$style.update_password_label_fail">Your name</label>
        <input autofocus 
                type="text" 
                id="user_new_password" 
                v-model="user_new_password" 
                :class="$style.update_password_input_fail"
                placeholder="Error input">
        <p :class="$style.update_password_p_fail"><span class="font-medium">Oh, snapp!</span> Some error message.</p>
      </div> -->

      <!-- 비밀번호 일치하는지 확인하는 칸 -->
      <div :class="$style.update_password_div">
        <label for="user_new_password_check_input" :class="$style.update_password_label">새 비밀번호 확인</label>
        <div>
          <input :class="$style.update_password_input" 
                  type="text" 
                  v-model="user_new_password_check" 
                  id="user_new_password_check_input" 
                  placeholder="새 비밀번호 확인 입력" 
                  required>
          <p :class="$style.update_password_p">
            비밀번호와 동일하게 입력해주세요.
          </p>
        </div>
      </div>

      <!-- 추가하기 버튼 -->
      <div :class="$style.update_password_save_div">
        <!-- <button type="submit" class="w-full text-sm text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg px-5 py-2.5 m-1 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
          비밀번호 변경하기
        </button> -->
        <!-- <button :class="$style.update_password_save">
          비밀번호 변경하기
        </button> -->
        <!-- <p :class="$style.update_password_p_fail"><span class="font-medium">Oh, snapp!</span> Some error message.</p> -->

      <button v-if="user_new_password != '' && user_new_password === user_new_password_check" 
              :class="$style.update_password_save"
              @click="updatePassword">
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

import { ref } from "vue";

let user_new_password = ref("");
let user_new_password_check = ref("");


const updatePassword = () => {
  if (confirm("비밀번호를 변경하시겠습니까?") == true) {
    // 비밀번호 변경 함수 실행!!
    alert("비밀번호 변경 완료");
  }
}

</script>

<style lang="css" module>
  @import "./TabUserPasswordUpdate.module.css";
</style>