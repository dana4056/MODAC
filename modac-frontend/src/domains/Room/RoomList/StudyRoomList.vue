<script setup>
import Wrapper from "@/components/Wrapper.vue";
import StudyRoomListItem from "@/domains/Room/RoomList/StudyRoomListItem.vue";
import RoomListHeader from "@/domains/Room/RoomList/RoomListHeader.vue";
import { ref } from "vue";

// seq:"Integer",
// usersSeq:"Integer",
// title:"String",
// description:"String",
// maxSize:"Integer",
// multiTheme:"String",
// publicType:"Integer"
// invitationCode:"String"
const roomList = ref([
  { seq: 1, title: "알고리즘 공부방" },
  { seq: 2, title: "아무나 들어오세요." },
  { seq: 3, title: "Flight A608" },
  { seq: 4, title: "역삼초3학년모여" },
]);
</script>

<template>
  <Wrapper :class="$style.wrapper_flex">
    <aside :class="$style.side_bar_flex_item"></aside>
    <Wrapper :class="$style.section_flex_item">
      <RoomListHeader />
      <section :class="$style.wrapper_flex">
        <StudyRoomListItem
          :class="$style.study_room_flex_item"
          v-for="roomItem in roomList"
          :key="roomItem.seq"
          :roomItem="roomItem"
        />
      </section>
    </Wrapper>
    <aside :class="$style.side_bar_flex_item"></aside>
  </Wrapper>
</template>

<style lang="css" module>
@import "./StudyRoomList.module.css";
</style>
