<template>
  <div>
    <h1>API TEST 화면임둥</h1>
    <button @click="click">API TEST</button>
  </div>
</template>

<script setup>

import { useUserStore } from '../stores/user';
import { useArticleStore } from '../stores/article2';
import { useCommentStore } from '../stores/comment';

const store = useUserStore();
const articleStore = useArticleStore();
const commentStore = useCommentStore();

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
  password: "vue2"
}

// ================================

const data6 = {   // 게시글 작성 데이터
  usersSeq: 1,
  todosSeq: 1,
  publicType: 1,
  filepath: "# 이거는 게시글 내용이다"
};

const data7 = {   // 사용자 게시글, 팔로잉 기반 게시글 조회 데이터
  usersSeq: 1,
  offset: 1,
  limit: 10
};

const data8 = {   // 게시글 좋아요 관련 데이터
  usersSeq: 2,
  articlesSeq: 2
};

// ======================================

const data9  = {  // 댓글
  articlesSeq: 2,
  usersSeq: 1,
  content: "소통해요~~~1"
}


// ======================================



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
  // store.api.login(data5);                      // 로그인
  
  // articleStore.api.postArticle(data1);         // 게시글 작성
  // articleStore.api.findArticleByUsersSeq(data2);  // 사용자 게시글 전체 조회
  // articleStore.api.findArticle(1);             // 게시글 조회
  // articleStore.api.updateViewCount(1);         // 게시글 조회수 올리기
  // articleStore.api.deleteArticle(1);           // 게시글 삭제
  // articleStore.api.findArticleByFollowing(data3); // 팔로잉 기반 게시글 조회
  // articleStore.api.likeArticle(data4);         // 게시글 좋아요
  // articleStore.api.unlikeArticle(data4);       // 게시글 좋아요 취소
  // articleStore.api.isLikedArticle(data4);      // 게시글 좋아요 관계정보 조회

  // commentStore.api.postComment(data9);         // 댓글 작성
  // commentStore.api.findCommentList(2);         // 게시글별 댓글 목록 조회
  // commentStore.api.deleteComment(2);           // 댓글 삭제


}


</script>

<style>

</style>