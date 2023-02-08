<template>
  <div>
    <h1>API TEST 화면임둥</h1>
    <button @click="click">API TEST</button>
  </div>
</template>

<script setup>

import { useUserStore } from '../stores/user';

const store = useUserStore();


//============================= API 파라미터 데이터 ==========================
const data1 = {     // 회원가입 데이터
  id: "test4",
  nickname: "nick4",
  email: "vue4@naver.com",
  password: "vue4",
};

const data2 = {   // 회원정보 수정 데이터
  userSeq: 3,
  update: {
    nickname: "nickname33",
    email: "vue3@gmail.com"
  }
};

const data3 = {   // 비밀번호 수정 데이터
  userSeq:3,
  password:"update3"
}

const data4 = {   // 포인트 수정 데이터
  userSeq:3,
  point: "10"
}

const data5 = {   // 로그인 데이터
  id: "test2",
  password: "test2"
}

const user_data6 = {   // 팔로잉 데이터
  fromSeq: 1, 
  toSeq:4
}

const user_data7 = {   // 팔로잉 여부 조회 데이터
  fromSeq: 1,
  toSeq:14
}


//==========================================================================

function click(){

  /********** 파라미터가 객체형식인 것은 위에 정리 / 아닌건 그냥 기입 *************/
  // store.api.postUser(data1);                   // 회원가입
  // store.api.fetchUser(2);                      // 회원정보 조회
  // store.api.updateUser(data2);                 // 회원정보 수정
  // store.api.updatePW(data3);                   // 비밀번호 수정
  // store.api.updatePoint(data4);                // 포인트 수정
  // store.api.deleteUser(6);                     // 회원탈퇴
  // store.api.findUserId("vue3@gmail.com");      // 아이디 찾기 (이메일로)
  // store.api.findUserPW("vue3@gmail.com");      // 비밀번호 찾기 (이메일로)
  // store.api.checkUserNick("nick1");            // 닉네임 중복확인
  // store.api.checkUserId("test1");              // 아이디 중복확인
  // store.api.checkUserEmail("vue1@gmail.com");  // 이메일 중복확인
  // store.login(data5);                          // 로그인
  // store.api.logout();                          // 로그아웃(axios X)
  // store.api.following(data6);                  // 팔로잉
  // store.api.unfollowing(1);                    // 언팔로잉
  // store.api.fetchFollowingUsers(1);            // 팔로잉 회원목록 조회(친구 조회)
  // store.api.fetchFollowerUsers(1);             // 팔로워 회원목록 조회(날 팔로잉하는 사람들)
  // store.api.isFollowing(data7);                // 팔로잉 여부 조회(내가 쟤를 팔로잉하고 있나?)

}


</script>

<style>

</style>