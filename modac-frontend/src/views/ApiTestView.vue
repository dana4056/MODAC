<template>
  <div>
    <h1>API TEST 화면임둥</h1>
    <button @click="click">API TEST</button>
  </div>
</template>

<script setup>
import { useUserStore } from "../stores/user";
import { useArticleStore } from "../stores/article";
import { useCommentStore } from "../stores/comment";
import { useTodoStore } from "../stores/todo";
import { useRoomStore } from "../stores/room";

const store = useUserStore();
const articleStore = useArticleStore();
const commentStore = useCommentStore();
const todoStore = useTodoStore();
const roomStore = useRoomStore();

//============================= API 파라미터 데이터 ==========================
const data1 = {
  // 회원가입 데이터
  id: "test1",
  nickname: "nick1",
  email: "test1@naver.com",
  password: "test1",
};

const data2 = {
  // 회원정보 수정 데이터
  userSeq: 3,
  update: {
    nickname: "nickname33",
    email: "vue3@gmail.com",
  },
};

const data3 = {
  // 비밀번호 수정 데이터
  userSeq: 3,
  password: "update3",
};

const data4 = {
  // 포인트 수정 데이터
  userSeq: 3,
  point: "10",
};

const data5 = {
  // 로그인 데이터
  id: "test2",
  password: "test2",
};

const user_data6 = {
  // 팔로잉 데이터
  fromSeq: 1,
  toSeq: 4,
};

const user_data7 = {
  // 팔로잉 여부 조회 데이터
  fromSeq: 421,
  toSeq: 43,
};

// ================================

const data6 = {
  // 게시글 작성 데이터
  usersSeq: 1,
  todosSeq: 1,
  publicType: 1,
  filepath: "# 이거는 게시글 내용이다",
};

const data7 = {
  // 사용자 게시글, 팔로잉 기반 게시글 조회 데이터
  usersSeq: 1,
  offset: 1,
  limit: 10,
};

const data8 = {
  // 게시글 좋아요 관련 데이터
  usersSeq: 2,
  articlesSeq: 2,
};

// ======================================

const data9 = {
  // 댓글 저장 데이터
  articlesSeq: 2,
  usersSeq: 1,
  content: "소통해요~~~1",
};

// ======================================

const data10 = {
  // Todo 저장 데이터
  usersSeq: 1,
  categoriesName: "기타",
  title: "실버4알고리즘문제를풀고말테야",
};

const data11 = {
  // Todo 수정 데이터
  todosSeq: 3,
  categoriesName: "기타",
  title: "플래1알고리즘문제를풀고말테야",
  status: 1,
  totalSecond: "999",
};

// ======================================

const data12 = {
  // 스터디룸 생성 데이터
  usersSeq: 1,
  title: "알고리즘 공부하실분~!!",
  description: "백준 골드 이하만 들어오삼. 다른공부 하면 강퇴",
  maxSize: 6,
  multiTheme: "기타",
  publicType: 0,
};

const data13 = {
  // 스터디룸 수정 데이터
  seq: 3,
  title: "CS 공부방입니당",
  description: "운영체제 관련 질문 환영이에요~!!",
  multiTheme: "Computer Science",
};

const data14 = {
  // 스터디룸 참가, 나가기, 참가여부확인 데이터
  seq: 2,
  usersSeq: 1,
};

const data15 = {
  // 비공개 스터디룸 접속 시 접속 상태변경 데이터
  seq: 2,
  usersSeq: 1,
  attend: true,
};

const data16 = {
  // 특정 비공개 스터디룸 입장 권한 확인(인증) 데이터
  seq: 2,
  roomCode: 333106,
};

const data17 = {
  // 스터디룸 검색 데이터
  usersSeq: 2,
  keyword: "멀티룸",
};

//==========================================================================

function click() {
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
  // store.api.following(user_data6);                  // 팔로잉
  // store.api.unfollowing(1);                    // 언팔로잉
  // store.api.fetchFollowingUsers(1);            // 팔로잉 회원목록 조회(친구 조회)
  // store.api.fetchFollowerUsers(1);             // 팔로워 회원목록 조회(날 팔로잉하는 사람들)
  // store.api.isFollowing(user_data7);                // 팔로잉 여부 조회(내가 쟤를 팔로잉하고 있나?)
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
  // todoStore.api.postTodo(data10);              // todo 작성
  // todoStore.api.findTodo(3);                   // todo 조회
  // todoStore.api.updateTodo(data11);            // todo 수정
  // todoStore.api.deleteTodo(3);                 // todo 삭제
  // todoStore.api.findTodoList(1);               // todo 목록조회
  // roomStore.api.postRoom(data12);              // 스터디룸 생성
  // roomStore.api.findRoomList(1);               // 스터디룸 목록조회
  // roomStore.api.findRoom(2);                   // 스터디룸 조회
  // roomStore.api.updateRoom(data13);            // 스터디룸 수정
  // roomStore.api.deleteRoom(3);                 // 스터디룸 삭제
  // roomStore.api.joinRoom(data14);              // 스터디룸 참가
  // roomStore.api.exitRoom(data14)               // 스터디룸 나가기
  // roomStore.api.updateCurrentRoom(data15);     // 비공개 스터디룸 접속 시 접속 상태변경
  // roomStore.api.findPrivateRoomList(1);        // 참가중인 비공개 스터디룸 목록조회(즐찾)
  // roomStore.api.checkRoomMember(data14);       // 특정 비공개 스터디룸 멤버 확인 (인가)
  // roomStore.api.checkRoomCode(data16)          // 특정 비공개 스터디룸 입장 권한 확인(인증)
  // roomStore.api.searchRoom(data17)             // 스터디룸 검색
}
</script>

<style></style>
