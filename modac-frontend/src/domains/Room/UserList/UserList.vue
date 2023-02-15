<script setup>
import UserListItem from "./UserListItem.vue";
import { useCssModule, computed, ref } from "vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";
const $style = useCssModule();

// const columnStyle = computed(
//   () => `${$style.user_list_first_flex_item} ${$style.user_list_second_flex}`
// );
// const userItemStyle = computed(
//   () => `${$style.user_list_second_flex_item} ${$style.test_style}`
// );

const roomStore = useRoomStore();
const { room_info } = storeToRefs(roomStore);

</script>

<template>
  <UserListItem
    v-if="room_info.participants[0]"
    :class="`${$style.first_position} ${$style.item_box}`"
    :userItem="room_info.participants[0]"
  />
  <UserListItem
  v-if="room_info.participants[1]"
    :class="`${$style.second_position} ${$style.item_box}`"
    :userItem="room_info.participants[1]"
  />
  <UserListItem
  v-if="room_info.participants[2]"
    :class="`${$style.third_position} ${$style.item_box}`"
    :userItem="room_info.participants[2]"
  />
  <UserListItem
  v-if="room_info.participants[3]"
    :class="`${$style.fouth_position} ${$style.item_box}`"
    :userItem="room_info.participants[3]"
  />
  <UserListItem
  v-if="room_info.participants[4]"
    :class="`${$style.fifth_position} ${$style.item_box}`"
    :userItem="room_info.participants[4]"
  />
  <UserListItem
  v-if="room_info.participants[5]"
    :class="`${$style.sixth_position} ${$style.item_box}`"
    :userItem="room_info.participants[5]"
  />

  <!-- <div :class="$style.user_list_first_flex">
    <div :class="columnStyle">
      <UserListItem :class="userItemStyle" :userItem="participants[0]" />
      <UserListItem :class="userItemStyle" :userItem="participants[1]" />
      <UserListItem :class="userItemStyle" :userItem="participants[2]" />
    </div>
    <div :class="columnStyle"></div>
    <div :class="columnStyle">
      <UserListItem :class="userItemStyle" :userItem="participants[3]" />
      <UserListItem :class="userItemStyle" :userItem="participants[4]" />
      <UserListItem :class="userItemStyle" :userItem="participants[5]" />
    </div>
  </div> -->
</template>

<style lang="css" module>
@import "UserList.module.css";
</style>
