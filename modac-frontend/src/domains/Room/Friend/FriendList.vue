<script setup>
import { ref, computed } from "vue";
import { useFriendStore } from "@/stores/friend";
import FriendListItem from "@/domains/Room/Friend/FriendListItem.vue";

const store = useFriendStore();

// 방법 1. store에 저장된 비동기 요청 함수를 실행시켜서 리턴값을 가져오는 방식
const friendList = ref([]);
friendList.value = store.getFriendList();

// 방법 2. 친구목록을 computed를 사용해서 store의 데이터를 변환시켜서 확인해보자.
const computedFriendList = computed(() => {
  return store.friendList;
});
</script>

<template>
  <div>
    친구 목록
    <!-- <p>여기는 FriendList입니다. 컴포넌트를 테스트합니다.</p>
    <div>{{ friendList }} 이 목록을 하위 컴포넌트로 Props 로 보내겠습니다.</div>
    <FriendListItem
      v-for="friendItem in friendList"
      :key="friendItem.id"
      :friend-item="friendItem"
    ></FriendListItem>
    <div>{{ computedFriendList }}</div> -->
  </div>
</template>

<style lang="css" module></style>
