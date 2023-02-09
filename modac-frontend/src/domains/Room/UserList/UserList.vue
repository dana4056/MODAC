<script setup>
import UserListItem from "./UserListItem.vue";
import { useCssModule, computed } from "vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";
const $style = useCssModule();

// const columnStyle = computed(
//   () => `${$style.user_list_first_flex_item} ${$style.user_list_second_flex}`
// );
// const userItemStyle = computed(
//   () => `${$style.user_list_second_flex_item} ${$style.test_style}`
// );

const store = useRoomStore();
const { participants } = storeToRefs(store);
// console.log(participants.value);
</script>

<template>
  <UserListItem
    :class="`${$style.first_position} ${$style.item_box}`"
    :userItem="participants[0]"
  />
  <UserListItem
    :class="`${$style.second_position} ${$style.item_box}`"
    :userItem="participants[1]"
  />
  <UserListItem
    :class="`${$style.third_position} ${$style.item_box}`"
    :userItem="participants[2]"
  />
  <UserListItem
    :class="`${$style.fouth_position} ${$style.item_box}`"
    :userItem="participants[3]"
  />
  <UserListItem
    :class="`${$style.fifth_position} ${$style.item_box}`"
    :userItem="participants[4]"
  />
  <UserListItem
    :class="`${$style.sixth_position} ${$style.item_box}`"
    :userItem="participants[5]"
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
