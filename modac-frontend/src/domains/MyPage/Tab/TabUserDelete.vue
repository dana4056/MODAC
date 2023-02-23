<template lang="">
  <div :class="$style.tabs_flex">
    <p :class="$style.delete_user_title">탈퇴 안내 사항</p>
    <p :class="$style.delete_user_info">
      <br>
      서비스에 만족하지 못하셨나요? 탈퇴하기 전에 먼저 개선 요청을 해보시는 건 어떨까요?<br>
      그래도 탈퇴하시겠다면 탈퇴 전에 아래 유의사항을 꼭 읽어주세요!<br>
      🙇🏻‍♂️ 감사합니다 🙇🏻‍♀️<br>
      <br>
      1. 계정 탈퇴 시, MODAC 서비스에서 계정이 탈퇴됩니다.<br>
      2. 탈퇴 시 계정과 관련된 모든 권한이 사라지며 복구할 수 없습니다.<br>
      3. 직접 작성한 콘텐츠(피드, 댓글 등)는 자동으로 삭제되지 않으며,<br> 만일 삭제를 원하시면 탈퇴 이전에 삭제가 필요합니다.<br>
      4. 현재 비밀번호를 입력하고 탈퇴하기를 누르시면 위 내용에 동의하는 것으로 간주됩니다.<br>
    </p>

    <div :class="$style.delete_user_password">
      <label for="delete_user_password" :class="$style.delete_user_password_label">현재 비밀번호</label>
      <input :class="$style.delete_user_password_input" 
              type="text" 
              v-model="delete_user_password" 
              id="delete_user_password" 
              placeholder="현재 비밀번호" 
              required>
    </div>
    <div :class="$style.delete_user_password_button_div">
      <button v-if="user_password === delete_user_password" 
              :class="$style.delete_user_password_button"
              @click="deleteUser">
        탈퇴하기
      </button>
      <button v-else :class="$style.delete_user_password_button_disabled" disabled>
        탈퇴하기
      </button>
    </div>
  </div>

</template>

<script setup>

import { ref } from "vue";

let delete_user_password = ref("");
let user_password = "password";

const deleteUser = () => {
  if (confirm("정말 탈퇴하시겠습니까?") == true) {
    // 회원 탈퇴 함수 실행!!

  }
}

</script>

<style lang="css" module>
  @import "./TabUserDelete.module.css";
</style>