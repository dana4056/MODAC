<script setup>
import { storeToRefs } from "pinia";
import { useFriendStore } from "@/stores/friend";
import FriendListItem from "@/domains/Room/Friend/FriendListItem.vue";
import RightSideBarHeader from "../Sidebar/RightSideBarHeader.vue";
import RightSideBarBody from "../Sidebar/RightSideBarBody.vue";

const store = useFriendStore();

const { friendList } = storeToRefs(store);
</script>

<template>
  <div :class="$style.friend_list_style">
    <RightSideBarHeader>친구 목록</RightSideBarHeader>
    <RightSideBarBody>
      <FriendListItem
        v-for="friendItem in friendList"
        :key="friendItem.id"
        :friend-item="friendItem"
      ></FriendListItem>
    </RightSideBarBody>
  </div>
</template>

<style lang="css" module>
@import "./FriendList.module.css";
</style>
